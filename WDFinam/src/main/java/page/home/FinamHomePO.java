package page.home;

import model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;
import page.alert.FinamAlertPO;
import page.alert.FinamAlertTabPO;
import page.breifcase.FinamBriefcasePO;
import page.market.FinamMarketsTabPO;
import page.request.FinamRequestPO;

public class FinamHomePO extends BasePO {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsTab;

    @FindBy(id = "alerts-tab-button")
    private WebElement alertsTab;

    @FindBy(id = "order-button-open-form")
    private WebElement requestButton;

    @FindBy(id = "alert-form-open-button")
    private WebElement alertButton;

    private FinamBriefcasePO briefcasePO;

    public FinamHomePO(WebDriver driver)
    {
        super(driver);
        briefcasePO = new FinamBriefcasePO(driver);
    }

    private FinamMarketsTabPO openMarketsTab()
    {
        waitForElementToBeClickable(driver, marketsTab);
        marketsTab.click();

        return new FinamMarketsTabPO(driver);
    }

    private FinamAlertTabPO openAlertsTab()
    {
        waitForElementToBeClickable(driver, alertsTab);
        alertsTab.click();

        return new FinamAlertTabPO(driver);
    }

    private FinamRequestPO openRequest()
    {
        waitForElementToBeClickable(driver, requestButton);
        requestButton.click();
        return new FinamRequestPO(driver);
    }

    private FinamAlertPO openAlertWindow()
    {
        waitForElementToBeClickable(driver, alertButton);
        alertButton.click();

        return new FinamAlertPO(driver);
    }

    public FinamBriefcasePO getBriefcasePO() { return briefcasePO; }

    public FinamHomePO marketBuyStock(Stock stock)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToMarketPanel()
                           .sendKeysToStockAmountInput(String.valueOf(stock.getAmount()))
                           .submitRequest();

        logger.info("Bought stocks " + stock.getCompany() + "(" + stock.getAmount() + ")");

        return this;
    }

    public FinamHomePO marketSellStock(Stock stock)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToMarketPanel()
                           .switchToRequestSellOption()
                           .sendKeysToStockAmountInput(String.valueOf(stock.getAmount()))
                           .submitRequest();

        logger.info("Sold stocks " + stock.getCompany() + "(" + stock.getAmount() + ")");

        return this;
    }

    public FinamHomePO conditionalBuyStock(Stock stock, ConditionalDeal deal)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToConditionalRequestPanel()
                           .selectRequestCondition(deal)
                           .sendKeysToStockAmountInput(String.valueOf(stock.getAmount()))
                           .submitRequest();

        logger.info("Conditional request for buy created");

        return this;
    }

    public FinamHomePO conditionalSellStock(Stock stock, ConditionalDeal deal)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToRequestSellOption()
                 .switchToConditionalRequestPanel()
                 .selectRequestCondition(deal)
                 .sendKeysToStockAmountInput(String.valueOf(stock.getAmount()))
                 .submitRequest();

        logger.info("Conditional request for sell created");

        return this;
    }

    public FinamHomePO createAlert(Stock stock, Alert alert)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openAlertWindow().selectAlertCondition(alert)
                         .sendKeysToAlertArgument(alert.getAlertArgument())
                         .submitAlert();

        return this;
    }

    public FinamHomePO limitBuyStock(Limit limit)
    {
        openMarketsTab().selectStock(limit.getStock()).closeMarketsTab();

        openRequest().switchToLimitPanel()
                     .sendKeysToStockAmountInput(String.valueOf(limit.getStock().getAmount()))
                     .sendKeysToLimitPriceInput(String.valueOf(limit.getPrice()))
                     .submitRequest();

        return this;
    }

    public FinamHomePO limitSellStock(Limit limit)
    {
        openMarketsTab().selectStock(limit.getStock()).closeMarketsTab();

        openRequest().switchToRequestSellOption()
                     .switchToLimitPanel()
                     .sendKeysToStockAmountInput(String.valueOf(limit.getStock().getAmount()))
                     .sendKeysToLimitPriceInput(String.valueOf(limit.getPrice()))
                     .submitRequest();

        return this;
    }

    public FinamHomePO stopBuyStock(Stock stock, Stop stop)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToStopPanel()
                     .selectStopOptions(stop)
                     .sendKeysToStopInputs(stop, stock)
                     .submitRequest();

        return this;
    }

    public FinamHomePO stopSellStock(Stock stock, Stop stop)
    {
        openMarketsTab().selectStock(stock).closeMarketsTab();

        openRequest().switchToRequestSellOption()
                .switchToStopPanel()
                .selectStopOptions(stop)
                .sendKeysToStopInputs(stop, stock)
                .submitRequest();

        return this;
    }


    public boolean isAlertExist(Stock stock)
    {
       FinamAlertTabPO alertTabPO = openAlertsTab();
       boolean isExists = alertTabPO.isAlertExists(stock);
       alertTabPO.closeAlertTab();

       return isExists;
    }
}

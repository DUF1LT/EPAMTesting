package page;

import model.ConditionalDeal;
import model.Stock;
import model.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamHomePO extends BasePO {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsTab;

    @FindBy(id = "alerts-tab-button")
    private WebElement alertsTab;

    @FindBy(id = "market-group-dropdown")
    private WebElement marketsGroupDropdown;

    @FindBy(xpath = "//li[@value='E2']")
    private WebElement salesAndFondGroup;

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='positions']")
    private WebElement briefcasePanel;

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='orders']")
    private WebElement requestPanel;

    @FindBy(id = "order-button-open-form")
    private WebElement requestButton;

    @FindBy(id = "alert-form-open-button")
    private WebElement alertButton;

    @FindBy(xpath = "//div[@id='order-form-side']//button[@value='2']")
    private WebElement sellOptionButton;

    @FindBy(name = "quantity")
    private WebElement stocksAmountInput;

    @FindBy(id = "order-form-submit")
    private WebElement orderButton;

    @FindBy(id = "order-confirm-submit")
    private WebElement submitButton;

    @FindBy(id = "order-form-close")
    private WebElement closeSubmitButton;

    @FindBy(xpath = "//button[@data-test='newOrder']")
    private WebElement newOrderButton;

    @FindBy(id = "order-type-market")
    private WebElement marketPanel;

    @FindBy(id = "order-type-cond")
    private WebElement conditionalRequestPanel;

    @FindBy(xpath = "//span[contains(text(), 'Условие')]/../..")
    private WebElement conditionButton;

    @FindBy(xpath = "//label[contains(text(),'Время активации')]/../div[2]//input")
    private WebElement conditionTimeArgumentButton;

    @FindBy(xpath = "//label[contains(text(),'Цена активации')]/..//input")
    private WebElement conditionArgumentButton;

    private final String companyStockElementXpathTemplate = "//p/span[contains(text(),'%s')]";
    private final String stockBriefcaseCellXpathTemplate = "//div[@role='gridcell']/p[contains(text(), '%s')]";
    private final String stockBriefcaseAmountXpathTemplate = "//div[@data-row-id='%s']//div[@data-test='quantity']//p";
    private final String conditionXpathTemplate = "//div[@role='presentation']//li[@value='%s']";
    private final String conditionTimeArgumentTemplate = "//div[@role='presentation']//li[contains(text(), '%s')]";
    private final String stockRequestCell = "//p[@title='Условная']//..//preceding-sibling::div//p[contains(text(),'%s')]";

    public FinamHomePO(WebDriver driver) {
        super(driver);
    }

    private void sendKeysToStockAmountInput(String input)
    {
        waitForElementToBeClickable(driver, stocksAmountInput);
        stocksAmountInput.click();
        stocksAmountInput.sendKeys(Keys.BACK_SPACE);
        stocksAmountInput.sendKeys(String.valueOf(input));
    }

    private void switchToMarketPanel()
    {
        waitForElementToBeClickable(driver, marketPanel);
        marketPanel.click();
    }

    private void switchToConditionalRequestPanel()
    {
        waitForElementToBeClickable(driver, conditionalRequestPanel);
        conditionalRequestPanel.click();
    }

    private void switchToRequestSellOption()
    {
        waitForElementToBeClickable(driver, sellOptionButton);
        sellOptionButton.click();
    }

    public FinamHomePO openMarkets()
    {
        waitForElementToBeClickable(driver, marketsTab);
        marketsTab.click();

        return this;
    }

    public FinamHomePO openAlerts()
    {
        waitForElementToBeClickable(driver, alertsTab);
        alertsTab.click();

        return this;
    }

    public void switchToStocksAndFondsPanel()
    {
        waitForElementToBeClickable(driver, marketsGroupDropdown);
        marketsGroupDropdown.click();

        waitForElementToBeClickable(driver, salesAndFondGroup);
        salesAndFondGroup.click();
        logger.info("Menu switched to Stocks and Fonds");

    }

    public FinamHomePO switchToRequestPanel()
    {
        waitForElementToBeClickable(driver, requestPanel);
        requestPanel.click();

        return this;
    }

    public FinamHomePO switchToBriefcasePanel()
    {
        waitForElementToBeClickable(driver, briefcasePanel);
        briefcasePanel.click();

        return this;
    }

    public void openRequestWindow()
    {
        waitForElementToBeClickable(driver, requestButton);
        requestButton.click();

    }

    public void selectStock(Stock stock)
    {
        WebElement companyDiv = waitForPresenceOfElementLocated(driver,
                String.format(companyStockElementXpathTemplate, stock.getCompany()));

        companyDiv.click();

    }

    public void selectCondition(ConditionalDeal deal)
    {
        waitForElementToBeClickable(driver, conditionButton);
        conditionButton.click();

        WebElement conditionalElement;

        switch(deal.getCondition())
        {
            case UpperOrEquals:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "8"));
                break;
            case LowerOrEquals:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "9"));
                break;
            default:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "5"));
        }

        conditionalElement.click();

        if(deal.getCondition() == Condition.CompletionTime)
        {
            waitForElementToBeClickable(driver, conditionTimeArgumentButton);
            conditionTimeArgumentButton.click();

            WebElement timeElement = waitForPresenceOfElementLocated(driver,
                    String.format(conditionTimeArgumentTemplate, deal.getConditionArgument()));

            timeElement.click();
        }
        else
        {
            waitForElementToBeClickable(driver, conditionArgumentButton);
            conditionArgumentButton.click();
            conditionalElement.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            conditionalElement.sendKeys(deal.getConditionArgument());
        }


    }

    public int getStockCurrentAmount(Stock stock)
    {
        switchToBriefcasePanel();
        if(driver.findElements(By.xpath(String.format(stockBriefcaseAmountXpathTemplate, stock.getCompany()))).size() == 0)
            return 0;
        else
        {
            WebElement stockAmountLabel = driver.findElement(By.xpath(
                    String.format(stockBriefcaseAmountXpathTemplate,stock.getCompany())));

            waitForVisibilityOfElement(driver, stockAmountLabel);

            return Integer.parseInt(stockAmountLabel.getText());
        }
    }

    public FinamHomePO buyStock(Stock stock)
    {
        selectStock(stock);
        openRequestWindow();
        switchToMarketPanel();
        sendKeysToStockAmountInput(String.valueOf(stock.getAmount()));

        logger.info("Bought stocks " + stock.getCompany() + "(" + stock.getAmount() + ")");

        return this;
    }

    public FinamHomePO sellStock(Stock stock)
    {
        selectStock(stock);
        openRequestWindow();
        switchToMarketPanel();
        switchToRequestSellOption();
        sendKeysToStockAmountInput(String.valueOf(stock.getAmount()));

        logger.info("Sold stocks " + stock.getCompany() + "(" + stock.getAmount() + ")");

        return this;
    }

    public FinamHomePO conditionalBuyStock(Stock stock, ConditionalDeal deal)
    {
        selectStock(stock);
        openRequestWindow();
        switchToConditionalRequestPanel();
        selectCondition(deal);
        sendKeysToStockAmountInput(String.valueOf(stock.getAmount()));

        logger.info("Conditional request for buy created");

        return this;
    }

    public FinamHomePO conditionalSellStock(Stock stock, ConditionalDeal deal)
    {
        selectStock(stock);
        openRequestWindow();
        switchToRequestSellOption();
        switchToConditionalRequestPanel();
        selectCondition(deal);
        sendKeysToStockAmountInput(String.valueOf(stock.getAmount()));

        logger.info("Conditional request for sell created");

        return this;
    }
    
    public FinamHomePO submitRequest()
    {
        waitForElementToBeClickable(driver, orderButton);
        orderButton.click();

        waitForElementToBeClickable(driver, submitButton);
        submitButton.click();

        waitForVisibilityOfElement(driver, newOrderButton);
        newOrderButton.click();

        closeSubmitButton.click();

        logger.info("Request submitted");

        return this;
    }

    public boolean isStockExist(Stock stock)
    {
        if(driver.findElements(By.xpath(String.format(stockBriefcaseCellXpathTemplate, stock.getCompany()))).size() == 0) {
            logger.error("Stocks wasn't found");
            return false;
        }
        logger.info("Stocks was found");

        return true;
    }

    public boolean isRequestExist(Stock stock)
    {
        if(driver.findElements(By.xpath(String.format(stockRequestCell, stock.getCompany()))).size() == 0) {
            logger.error("Request wasn't found");
            return false;
        }
        logger.info("Request was found");

        return true;
    }
}

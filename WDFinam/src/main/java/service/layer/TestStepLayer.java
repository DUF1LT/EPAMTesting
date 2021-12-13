package service.layer;

import model.alert.Alert;
import model.conditional.ConditionalRequest;
import model.limit.Limit;
import model.market.MarketRequest;
import model.stop.Stop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.alert.FinamAlertTabPage;
import page.breifcase.FinamBriefcasePage;
import page.home.FinamHomePage;

public class TestStepLayer {
    protected final Logger logger = LogManager.getRootLogger();
    private FinamHomePage homePage;

    public TestStepLayer(FinamHomePage homePage){
        this.homePage = homePage;
    }

    public TestStepLayer marketBuyStock(MarketRequest marketRequest) {
        homePage.openMarketsTab().selectCompany(marketRequest.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToMarketPanel()
                .sendKeysToStockAmountInput(String.valueOf(marketRequest.getAmount()))
                .submitRequest();

        logger.info("Bought stocks " + marketRequest.getCompany() + "(" + marketRequest.getAmount() + ")");

        return this;
    }

    public TestStepLayer marketSellStock(MarketRequest marketRequest) {
        homePage.openMarketsTab().selectCompany(marketRequest.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToMarketPanel()
                .switchToRequestSellOption()
                .sendKeysToStockAmountInput(String.valueOf(marketRequest.getAmount()))
                .submitRequest();

        logger.info("Sold stocks " + marketRequest.getCompany() + "(" + marketRequest.getAmount() + ")");

        return this;
    }

    public TestStepLayer conditionalBuyStock(ConditionalRequest conditionalRequest) {
        homePage.openMarketsTab().selectCompany(conditionalRequest.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToConditionalRequestPanel()
                .selectRequestCondition(conditionalRequest)
                .sendKeysToStockAmountInput(String.valueOf(conditionalRequest.getAmount()))
                .submitRequest();

        logger.info("Conditional request for buy created");

        return this;
    }

    public TestStepLayer conditionalSellStock(ConditionalRequest conditionalRequest) {
        homePage.openMarketsTab().selectCompany(conditionalRequest.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToRequestSellOption()
                .switchToConditionalRequestPanel()
                .selectRequestCondition(conditionalRequest)
                .sendKeysToStockAmountInput(String.valueOf(conditionalRequest.getAmount()))
                .submitRequest();

        logger.info("Conditional request for sell created");

        return this;
    }

    public TestStepLayer createAlert(Alert alert) {
        homePage.openMarketsTab().selectCompany(alert.getCompany()).closeMarketsTab();

        homePage.openAlertWindow().selectAlertCondition(alert)
                .sendKeysToAlertArgument(alert.getAlertArgument())
                .submitAlert();

        logger.info("Alert created");

        return this;
    }

    public TestStepLayer limitBuyStock(Limit limit) {
        homePage.openMarketsTab().selectCompany(limit.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToLimitPanel()
                .sendKeysToStockAmountInput(String.valueOf(limit.getAmount()))
                .sendKeysToLimitPriceInput(String.valueOf(limit.getPrice()))
                .submitRequest();

        logger.info("Limit request for buy created");

        return this;
    }

    public TestStepLayer limitSellStock(Limit limit) {
        homePage.openMarketsTab().selectCompany(limit.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToRequestSellOption()
                .switchToLimitPanel()
                .sendKeysToStockAmountInput(String.valueOf(limit.getAmount()))
                .sendKeysToLimitPriceInput(String.valueOf(limit.getPrice()))
                .submitRequest();

        logger.info("Limit request for sell created");

        return this;
    }

    public TestStepLayer stopBuyStock(Stop stop) {
        homePage.openMarketsTab().selectCompany(stop.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToStopPanel()
                .selectStopOptions(stop)
                .sendKeysToStopInputs(stop)
                .submitRequest();

        logger.info("Stop request for buy created");

        return this;
    }

    public TestStepLayer stopSellStock(Stop stop) {
        homePage.openMarketsTab().selectCompany(stop.getCompany()).closeMarketsTab();

        homePage.openRequest().switchToRequestSellOption()
                .switchToStopPanel()
                .selectStopOptions(stop)
                .sendKeysToStopInputs(stop)
                .submitRequest();

        logger.info("Stop request for sell created");

        return this;
    }

    public FinamBriefcasePage getBriefcasePage() {
        return homePage.getBriefcasePO();
    }

    public boolean isAlertExists(Alert alert) {
        FinamAlertTabPage alertTabPO = homePage.openAlertsTab();
        boolean isExists = alertTabPO.isAlertExists(alert);
        alertTabPO.closeAlertTab();

        return isExists;
    }

    public boolean isLimitExists(Limit limit){
        FinamBriefcasePage briefcasePage = homePage.getBriefcasePO();

        return briefcasePage.isLimitExists(limit);
    }

    public boolean isConditionalRequestExists(ConditionalRequest conditionalRequest){
        FinamBriefcasePage briefcasePage = homePage.getBriefcasePO();

        return briefcasePage.isConditionalRequestExists(conditionalRequest);
    }

    public boolean isStopExists(Stop stop){
        FinamBriefcasePage briefcasePage = homePage.getBriefcasePO();

        return briefcasePage.isStopRequestExists(stop);
    }
}

import model.TestRequestOption;
import model.alert.Alert;
import model.conditional.ConditionalRequest;
import model.limit.Limit;
import model.market.MarketRequest;
import model.stop.Stop;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.creator.*;

public class FinamTest extends BaseFinamTest{
    @Test(description = "Create alert for special company")
    public void createAlertTest() {
        Alert alert = AlertCreator.withCredentialsFromProperty();
        boolean isExists = testStep.createAlert(alert).isAlertExists(alert);
        Assert.assertTrue(isExists);
    }

    @Test(dataProvider = "buy-sell-dp", description = "Test buy/sell conditional request")
    public void conditionalRequestTest(TestRequestOption option) {
        ConditionalRequest conditionalRequest = ConditionalDealCreator.withCredentialsFromProperty();
        boolean isExists;

        if(option == TestRequestOption.BUY){
            isExists = testStep.conditionalBuyStock(conditionalRequest)
                    .isConditionalRequestExists(conditionalRequest);
        }
        else{
            isExists = testStep.conditionalSellStock(conditionalRequest)
                    .isConditionalRequestExists(conditionalRequest);
        }

        Assert.assertTrue(isExists);
    }

    @Test(dataProvider = "buy-sell-dp", description = "Test limit buy/sell operation")
    public void limitRequestTest(TestRequestOption testRequestOption){
        Limit limit = LimitCreator.withCredentialsFromProperty();
        boolean isExists;

        if(testRequestOption == TestRequestOption.BUY){
            isExists = testStep.limitBuyStock(limit)
                    .isLimitExists(limit);
        }
        else{
            isExists = testStep.limitSellStock(limit)
                    .isLimitExists(limit);
        }
        Assert.assertTrue(isExists);
    }

    @Test(dataProvider = "buy-sell-dp", description = "Test market sell/buy option")
    public void marketRequestTest(TestRequestOption option) {
        MarketRequest marketRequest = StockCreator.withCredentialsFromProperty();
        int stockAmountBeforeOperation = finamHomePO.getBriefcasePO().getStockCurrentAmount(marketRequest);
        int stockAmountAfterOperation = 0;
        int expectedAmount = 0;

        if(option == TestRequestOption.BUY){
            stockAmountAfterOperation = testStep.marketBuyStock(marketRequest)
                    .getBriefcasePage()
                    .getStockCurrentAmount(marketRequest);
            expectedAmount = stockAmountBeforeOperation + Integer.parseInt(marketRequest.getAmount());
        }
        else{
            stockAmountAfterOperation = testStep.marketSellStock(marketRequest)
                    .getBriefcasePage()
                    .getStockCurrentAmount(marketRequest);
            expectedAmount = stockAmountBeforeOperation - Integer.parseInt(marketRequest.getAmount());
        }
        Assert.assertEquals(stockAmountAfterOperation, expectedAmount);
    }

    @Test(dataProvider = "buy-sell-dp", description = "Test buy/sell stop")
    public void stopRequestTest(TestRequestOption option){
        Stop stop = StopCreator.withCredentialsFromProperty();
        boolean isExists;

        if(option == TestRequestOption.BUY){
            isExists = testStep.stopBuyStock(stop)
                    .isStopExists(stop);
        }
        else{
            stop.setEnableStopLoss(false);
            stop.setEnableTakeProfit(true);
            isExists = testStep.stopSellStock(stop)
                    .isStopExists(stop);
        }

        Assert.assertTrue(isExists);
    }
}

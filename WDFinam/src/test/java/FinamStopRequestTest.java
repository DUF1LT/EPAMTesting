import model.Stock;
import model.Stop;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.StockCreator;
import service.StopCreator;

public class FinamStopRequestTest extends BaseFinamTest
{
    @Test(description = "Buy by limit company stock of specific amount with specific limit price\"")
    public void StopBuyStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        Stop stop = StopCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO
                .stopBuyStock(stock, stop)
                .getBriefcasePO()
                .isStopRequestExist(stock));
    }

    @Test(description = "Sell by limit company stock of specific amount with specific limit price")
    public void StopSellStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        Stop stop = StopCreator.withCredentialsFromProperty();
        stop.setEnableStopLoss(false);
        stop.setEnableTakeProfit(true);

        Assert.assertTrue(finamHomePO
                .stopSellStock(stock, stop)
                .getBriefcasePO()
                .isStopRequestExist(stock));
    }
}

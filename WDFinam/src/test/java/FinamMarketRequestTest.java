import model.Stock;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.StockCreator;

public class FinamMarketRequestTest extends BaseFinamTest
{
    @Test(description = "Buy by market company stock of specific amount")
    public void MarketBuyStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        int stockAmountBeforeBuy = finamHomePO.getBriefcasePO().getStockCurrentAmount(stock);

        Assert.assertEquals(finamHomePO
                .marketBuyStock(stock)
                .getBriefcasePO()
                .getStockCurrentAmount(stock), stockAmountBeforeBuy + stock.getAmount());
    }

    @Test(description = "Sell by market company stock of specific amount")
    public void MarketSellStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        int stockAmountBeforeSale = finamHomePO.getBriefcasePO().getStockCurrentAmount(stock);

        Assert.assertEquals(finamHomePO
                .marketSellStock(stock)
                .getBriefcasePO()
                .getStockCurrentAmount(stock), stockAmountBeforeSale - stock.getAmount());
    }
}

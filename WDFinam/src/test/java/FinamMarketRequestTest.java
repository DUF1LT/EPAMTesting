import model.Stock;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.StockCreator;

public class FinamMarketRequestTest extends BaseFinamTest {

    @Test(description = "Buy by market company stock of specific amount")
    public void MarketBuyStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        int stockAmountBeforeBuy = finamHomePO.getStockCurrentAmount(stock);

        Assert.assertEquals(finamHomePO
                .buyStock(stock)
                .submitRequest()
                .switchToBriefcasePanel()
                .getStockCurrentAmount(stock), stockAmountBeforeBuy + stock.getAmount());
    }

    @Test(description = "Sell by market company stock of specific amount")
    public void MarketSellStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        int stockAmountBeforeSale = finamHomePO.getStockCurrentAmount(stock);

        Assert.assertEquals(finamHomePO
                .sellStock(stock)
                .submitRequest()
                .switchToBriefcasePanel()
                .getStockCurrentAmount(stock), stockAmountBeforeSale - stock.getAmount());
    }
}

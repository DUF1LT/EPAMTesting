import model.ConditionalDeal;
import model.Stock;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.ConditionalDealCreator;
import service.StockCreator;

public class FinamConditionalRequestTest extends BaseFinamTest
{

    @Test(description = "Buy by condition company stock of specific amount")
    public void ConditionalBuyStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        ConditionalDeal deal = ConditionalDealCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO
                .conditionalBuyStock(stock, deal)
                .getBriefcasePO()
                .isConditionalRequestExist(stock));
    }

    @Test(description = "Sell by condition company stock of specific amount")
    public void ConditionalSellStockTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        ConditionalDeal deal = ConditionalDealCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO
                .conditionalSellStock(stock, deal)
                .getBriefcasePO()
                .isConditionalRequestExist(stock));
    }

}

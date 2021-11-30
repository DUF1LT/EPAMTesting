import model.Limit;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.LimitCreator;

public class FinamLimitRequestTest extends BaseFinamTest
{
    @Test(description = "Buy by limit company stock of specific amount with specific limit price\"")
    public void LimitBuyStockTest()
    {
        Limit limit = LimitCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO
                .limitBuyStock(limit)
                .getBriefcasePO()
                .isLimitRequestExist(limit.getStock()));
    }

    @Test(description = "Sell by limit company stock of specific amount with specific limit price")
    public void LimitSellStockTest()
    {
        Limit limit = LimitCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO
                .limitSellStock(limit)
                .getBriefcasePO()
                .isLimitRequestExist(limit.getStock()));
    }
}

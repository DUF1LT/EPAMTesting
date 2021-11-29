import model.Alert;
import model.Stock;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.AlertCreator;
import service.StockCreator;

public class FinamAlertTest extends BaseFinamTest
{
    @Test(description = "Create alert for special stock")
    public void CreateAlertForStocks()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        Alert alert = AlertCreator.withCredentialsFromProperty();

        Assert.assertTrue(finamHomePO.createAlert(stock, alert)
                   .isAlertExist(stock));
    }
}

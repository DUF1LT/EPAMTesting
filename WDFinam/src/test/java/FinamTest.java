import driver.DriverSingleton;
import model.Stock;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.FinamHomePO;
import page.FinamLoginPO;
import service.StockCreator;
import service.UserCreator;
import utils.TestListener;

@Listeners({TestListener.class})
public class FinamTest {

    private WebDriver driver;
    private FinamLoginPO finamLoginPO;
    private FinamHomePO finamHomePO;

    @BeforeTest(alwaysRun = true)
    public void StartupAndLoginTest()
    {
        driver = DriverSingleton.getDriver();
        finamLoginPO = new FinamLoginPO(driver);
        finamLoginPO.openPage();
        finamHomePO = finamLoginPO.loginToFinam(UserCreator.withCredentialsFromProperty());
    }

    @Test(description = "Buy company stock of specific amount")
    public void BoughtStockAddToBriefcaseTest()
    {
        Stock stock = StockCreator.withCredentialsFromProperty();
        finamHomePO.buyStocks(stock);
        finamHomePO.closeSubmitWindows();
        Assert.assertTrue(finamHomePO.isNewStocksExist(stock.getCompany()));
    }

    @AfterTest(alwaysRun = true)
    public void CloseWebDriver()
    {
        DriverSingleton.closeDriver();
    }
}

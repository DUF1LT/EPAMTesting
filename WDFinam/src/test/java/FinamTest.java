import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.FinamHomePO;
import page.FinamLoginPO;

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
        finamHomePO = finamLoginPO.loginToFinam("2892", "5299373753");
    }

    @Test
    public void BoughtStockAddToBriefcaseTest()
    {
        finamHomePO.buyCompanyNSales("GMKN", 2);
        finamHomePO.closeSubmitWindows();
        Assert.assertTrue(finamHomePO.isNewSalesExists("GMKN"));
    }

    @AfterTest(alwaysRun = true)
    public void CloseWebDriver()
    {
        DriverSingleton.closeDriver();
    }
}

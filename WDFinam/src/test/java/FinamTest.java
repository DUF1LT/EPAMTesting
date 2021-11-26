import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FinamTest {

    private WebDriver driver;
    private FinamLoginPO finamLoginPO;
    private FinamHomePO finamHomePO;

    @BeforeTest(alwaysRun = true)
    public void StartupAndLoginTest()
    {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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
        driver.close();
        driver = null;
    }
}

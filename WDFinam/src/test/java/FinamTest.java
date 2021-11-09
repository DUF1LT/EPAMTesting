import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FinamTest {

    private WebDriver driver;
    private FinamPO finamPO;

    @BeforeTest
    public void StartupAndLoginTest()
    {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        finamPO = new FinamPO(driver);
        finamPO.openPage();
        finamPO.loginToFinam("7646", "5447456733");
    }

    @Test
    public void BoughtStockAddToBriefcaseTest()
    {
        finamPO.buyCompanyNSales("GMKN", 2);
        finamPO.closeSubmitWindows();
        Assert.assertTrue(finamPO.isNewSalesExists("GMKN"));
    }
}

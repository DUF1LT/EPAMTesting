import driver.DriverSingleton;
import model.TestRequestOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import page.home.FinamHomePage;
import page.login.FinamLoginPage;
import service.creator.UserCreator;
import service.layer.TestStepLayer;
import utils.TestListener;

@Listeners({TestListener.class})
public abstract class BaseFinamTest {
    public WebDriver driver;
    public TestStepLayer testStep;
    public FinamLoginPage finamLoginPO;
    public FinamHomePage finamHomePO;

    @DataProvider(name = "buy-sell-dp")
    public Object[][] buyAndSell() {
        return new Object[][]{
                {TestRequestOption.BUY},
                {TestRequestOption.SELL}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void startupAndLogin() {
        driver = DriverSingleton.getDriver();
        finamLoginPO = new FinamLoginPage(driver);
        finamLoginPO.openPage();
        finamHomePO = finamLoginPO.loginToFinam(UserCreator.withCredentialsFromProperty());
        finamHomePO.openSettings()
                .openAppSettings()
                .setTickerToolView()
                .closeSettings();

        testStep = new TestStepLayer(finamHomePO);
    }

    @AfterClass(alwaysRun = true)
    public void closeWebDriver() {
        DriverSingleton.closeDriver();
    }
}

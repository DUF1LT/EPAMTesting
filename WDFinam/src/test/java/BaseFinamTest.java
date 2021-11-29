import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import page.FinamHomePO;
import page.FinamLoginPO;
import service.UserCreator;
import utils.TestListener;

@Listeners({TestListener.class})
public abstract class BaseFinamTest {

    public WebDriver driver;
    public FinamLoginPO finamLoginPO;
    public FinamHomePO finamHomePO;

    @BeforeClass(alwaysRun = true)
    public void StartupAndLogin()
    {
        driver = DriverSingleton.getDriver();
        finamLoginPO = new FinamLoginPO(driver);
        finamLoginPO.openPage();
        finamHomePO = finamLoginPO.loginToFinam(UserCreator.withCredentialsFromProperty());
        finamHomePO.openMarkets()
                .switchToStocksAndFondsPanel();
    }

    @AfterClass(alwaysRun = true)
    public void CloseWebDriver()
    {
        DriverSingleton.closeDriver();
    }
}

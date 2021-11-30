package driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton(){}

    public static WebDriver getDriver()
    {
        if(driver == null)
        {
            switch (System.getProperty("browser"))
            {
                case "msedge":{
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                default:{
                    WebDriverManager.chromedriver().version("chrome96").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
        driver = null;
    }
}

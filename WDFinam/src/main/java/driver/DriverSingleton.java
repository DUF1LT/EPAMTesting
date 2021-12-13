package driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if(driver == null) {

            String browserVersion = System.getProperty("browserVersion");

            switch (System.getProperty("browser")) {
                case "msedge":{
                    WebDriverManager.edgedriver().version(browserVersion).setup();
                    driver = new EdgeDriver();
                }
                default:{
                    WebDriverManager.chromedriver().version(browserVersion).setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

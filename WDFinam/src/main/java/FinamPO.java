import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.openqa.selenium.By.xpath;

public class FinamPO {
    private final String HOME_PAGE = "https://trading.finam.ru/";
    protected final int WAIT_TIMEOUT_SECONDS = 15;
    protected WebDriver driver;
    private Wait<WebDriver> wait;

    public FinamPO openPage()
    {
        driver.get(HOME_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        return this;
    }

    public FinamPO loginToFinam(String login, String password)
    {
        WaitForXpath("//*[@id=\"login-dialog-EDOX_STOP_LIST\"]/div[3]/a");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        WebElement otherLoginMethods = driver.findElement(xpath("//*[@id=\"login-dialog-EDOX_STOP_LIST\"]/div[3]/a"));
        otherLoginMethods.click();

        WaitForId("login-account-type-UNION_DEMO");

        WebElement demoAccSetting = driver.findElement(xpath("//*[@id='login-account-type-UNION_DEMO']"));
        demoAccSetting.click();

        WaitForId("txauth-widget-login");

        WebElement loginField = driver.findElement(By.id("txauth-widget-login"));
        loginField.click();
        loginField.sendKeys(login);

        WaitForId("txauth-widget-password");

        WebElement passwordField = driver.findElement(By.id("txauth-widget-password"));
        passwordField.click();
        passwordField.sendKeys(password);

        WaitForXpath("//*[@id=\"txauth-widget-login-form\"]/button[1]");

        WebElement loginButton = driver.findElement(xpath("//*[@id=\"txauth-widget-login-form\"]/button[1]"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return this;
    }

    public FinamPO buyCompanyNSales(String company, int n){
        WaitForId("markets-tab-button");
        WebElement markets = driver.findElement(By.id("markets-tab-button"));
        markets.click();

        WaitForId("market-group-dropdown");
        WebElement groupDropdown = driver.findElement(By.id("market-group-dropdown"));
        groupDropdown.click();

        WaitForXpath("/html/body/div[5]/div[2]/ul/li[3]");
        WebElement salesAndFondGroup = driver.findElement(xpath("/html/body/div[5]/div[2]/ul/li[@value = 'E2']"));
        salesAndFondGroup.click();

        WaitForXpath("//*[@id=\"market-list-root\"]/div[2]/div[1]/div/div/div");
        String xpath = "//span[contains(text(),'"+ company + "')]";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WaitForXpath(xpath);
        WebElement companyDiv = driver.findElement(xpath(xpath));
        companyDiv.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WaitForId("order-button-open-form");
        WebElement request = driver.findElement(By.id("order-button-open-form"));
        request.click();

        WaitForXpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/input");
        WebElement salesAmount = driver.findElement(xpath("//*[@id=\"root\"]/div/div[6]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/input"));
        salesAmount.click();
        salesAmount.sendKeys(Keys.BACK_SPACE);
        salesAmount.sendKeys(String.valueOf(n));

        WaitForId("order-form-submit");
        WebElement orderButton = driver.findElement(By.id("order-form-submit"));
        orderButton.click();

        WaitForXpath("//*[@id=\"order-confirm-dialog\"]/div[2]/div/div[2]/table[1]/tbody/tr[5]/td[2]/p");
        String price = driver.findElement(xpath("//*[@id=\"order-confirm-dialog\"]/div[2]/div/div[2]/table[1]/tbody/tr[5]/td[2]/p")).getText();

        WaitForId("order-confirm-submit");
        WebElement submit = driver.findElement(By.id("order-confirm-submit"));

        submit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        return this;
    }

    public FinamPO closeSubmitWindows()
    {
        WaitForId("order-form-close");
        WebElement closeButton = driver.findElement(By.id("order-form-close"));
        closeButton.sendKeys(Keys.ESCAPE);
        closeButton.click();

        return this;
    }

    public boolean isNewSalesExists(String company)
    {
        WaitForXpath("//*[@id=\"content-root\"]/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/div");
        String xpath = "//*[@id=\"content-root\"]/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/div/div[@data-row-id = '" + company + "']";
        try
        {
            WebElement companySales = driver.findElement(By.xpath(xpath));
        }
        catch (Exception NoSuchElementException)
        {
            return false;
        }
        return true;
    }

    private void WaitForXpath(String xpath)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(xpath(xpath));
            }
        });
    }

    private void WaitForId(String id)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id(id));
            }
        });
    }

    public FinamPO(WebDriver driver)
    {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .ignoring(NoSuchElementException.class);
    }
}

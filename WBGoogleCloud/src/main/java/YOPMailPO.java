
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class YOPMailPO extends AbstractPO
{
    private final String HOME_PAGE = "https://yopmail.com/ru/";

    private Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
            .ignoring(NoSuchElementException.class);

    @Override
    public YOPMailPO openPage() {
        driver.get(HOME_PAGE);
        return this;
    }

    public YOPMailPO selectRandomEmailPage()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("listeliens"));
            }
        });

        WebElement randomEmail = driver.findElement(By.xpath("//a[@href = 'email-generator']"));
        randomEmail.click();

        return this;
    }

    public String getRandomEmail() {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("egen"));
            }
        });

        WebElement randEmail = driver.findElement(By.id("egen"));
        return  randEmail.getText();
    }

    public YOPMailPO checkRandomEmail()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.className("nw"));
            }
        });

        WebElement checkEmail = driver.findElement(By.xpath("//button//span[contains(text(), 'Проверить почту')]"));
        checkEmail.click();

        return this;
    }


    public String getEstimatedPrice()
    {
        new WebDriverWait(driver, Duration.ofSeconds(7));

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("refresh"));
            }
        });

        WebElement refresh = driver.findElement(By.id("refresh"));
        refresh.click();

        driver.switchTo().frame(driver.findElement(By.id("ifmail")));

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("#mail > div > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td:nth-child(2)"));
            }
        });

        WebElement price = driver.findElement(By.cssSelector("#mail > div > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td:nth-child(2)"));
        return price.getText().substring(4);
    }

    public YOPMailPO(WebDriver driver) {
        super(driver);
    }
}

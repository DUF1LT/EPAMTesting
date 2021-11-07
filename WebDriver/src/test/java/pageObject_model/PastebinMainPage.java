package pageObject_model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.function.Function;

public class PastebinMainPage extends AbstractPage {

    private Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
            .ignoring(NoSuchElementException.class);

    private static final String HOMEPAGE_URL = "https://pastebin.com/";

    @FindBy(id = "postform-text")
    private WebElement textArea;

    @FindBy(id = "postform-expiration")
    private Select pasteExpirationSelect;

    @Override
    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);

        wait.until(new Function<WebDriver, Object>() {
           public WebElement apply(WebDriver driver)
           {
               return driver.findElement(By.id("postform-text"));
           }
        });

        return this;
    }

    public PastebinMainPage inputCodeToTextArea(String code)
    {

        textArea.click();
        textArea.sendKeys(code);
        return this;
    }

    public PastebinMainPage selectPasteExpiration(String pasteExpiration)
    {
        WebElement select = driver.findElement(By.id("select2-postform-expiration-container"));
        select.click();

        pasteExpirationSelect.selectByVisibleText(pasteExpiration);
        return this;
    }

    public PastebinMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

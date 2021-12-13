package page;

import model.enums.FindByOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

    protected final Logger logger = LogManager.getRootLogger();
    protected final static int WAIT_TIMEOUT_SECONDS = 15;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibilityOfElement(WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForPresenceOfElementLocated(String xpath) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    protected List<WebElement> findElements(String argument, FindByOption option) {
        switch (option)
        {
            case XPATH:
                return driver.findElements(By.xpath(argument));
            case ID:
                return driver.findElements(By.id(argument));
            case NAME:
                return driver.findElements(By.name(argument));
        }
        return new ArrayList<WebElement>();
    }

    protected WebElement findElement(String argument, FindByOption option) {
        switch (option)
        {
            case XPATH:
                return driver.findElement(By.xpath(argument));
            case ID:
                return driver.findElement(By.id(argument));
            case NAME:
                return driver.findElement(By.name(argument));
        }
        return null;
    }

    protected boolean isWebElementExists(String argument, FindByOption option){
        switch (option)
        {
            case XPATH:
                return driver.findElements(By.xpath(argument)).size() != 0;
            case ID:
                return driver.findElements(By.id(argument)).size() != 0;
            case NAME:
                return driver.findElements(By.name(argument)).size() != 0;
        }
        return false;
    }
}

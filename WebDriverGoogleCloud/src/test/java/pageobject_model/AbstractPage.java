package pageobject_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected WebDriver driver;
    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}

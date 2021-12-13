package page.login;

import model.user.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import page.home.FinamHomePage;

public class FinamLoginPage extends BasePage {

    private final String BASE_URL = "https://trading.finam.ru/";

    @FindBy(xpath = "//*[@id='login-dialog-EDOX_STOP_LIST']/div[3]/a")
    private WebElement otherLoginMethodsLabel;

    @FindBy(xpath = "//*[@id='login-account-type-UNION_DEMO']")
    private WebElement demoAccountSetting;

    @FindBy(id = "txauth-widget-login")
    private WebElement loginField;

    @FindBy(id = "txauth-widget-password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='txauth-widget-login-form']/button[1]")
    private WebElement loginButton;

    public FinamLoginPage(WebDriver driver) {
        super(driver);
    }

    public FinamLoginPage openPage() {
        driver.get(BASE_URL);
        logger.info("Open " + BASE_URL);

        return this;
    }

    public FinamHomePage loginToFinam(User user) {
        waitForElementToBeClickable(otherLoginMethodsLabel).click();;
        waitForElementToBeClickable(demoAccountSetting).click();
        waitForElementToBeClickable(loginField).click();;
        loginField.sendKeys(user.getUsername());

        waitForElementToBeClickable(passwordField).click();;
        passwordField.sendKeys(user.getPassword());

        waitForElementToBeClickable(loginButton).click();;
        logger.info("Logged as " + user.getUsername() + "/" + user.getPassword());

        return new FinamHomePage(driver);
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class FinamLoginPO extends BasePO{

    protected FinamLoginPO(WebDriver driver) {
        super(driver);
    }

    private final String HOME_PAGE = "https://trading.finam.ru/";

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

    public FinamLoginPO openPage()
    {
        driver.get(HOME_PAGE);
        return this;
    }

    public FinamHomePO loginToFinam(String login, String password)
    {
        waitForElementToBeClickable(driver, otherLoginMethodsLabel);
        otherLoginMethodsLabel.click();

        waitForElementToBeClickable(driver, demoAccountSetting);
        demoAccountSetting.click();

        waitForElementToBeClickable(driver, loginField);
        loginField.click();
        loginField.sendKeys(login);

        waitForElementToBeClickable(driver, passwordField);
        passwordField.click();
        passwordField.sendKeys(password);

        waitForElementToBeClickable(driver, loginButton);
        loginButton.click();

        return new FinamHomePO(driver);
    }


}

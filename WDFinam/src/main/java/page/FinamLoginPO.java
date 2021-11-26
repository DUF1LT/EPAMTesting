package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamLoginPO extends BasePO{

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

    public FinamLoginPO(WebDriver driver) {
        super(driver);
    }

    public FinamLoginPO openPage()
    {
        driver.get(BASE_URL);
        logger.info("Open " + BASE_URL);
        return this;
    }

    public FinamHomePO loginToFinam(User user)
    {
        waitForElementToBeClickable(driver, otherLoginMethodsLabel);
        otherLoginMethodsLabel.click();

        waitForElementToBeClickable(driver, demoAccountSetting);
        demoAccountSetting.click();

        waitForElementToBeClickable(driver, loginField);
        loginField.click();
        loginField.sendKeys(user.getUsername());

        waitForElementToBeClickable(driver, passwordField);
        passwordField.click();
        passwordField.sendKeys(user.getPassword());

        waitForElementToBeClickable(driver, loginButton);
        loginButton.click();

        logger.info("Logged as " + user.getUsername() + "/" + user.getPassword());
        return new FinamHomePO(driver);
    }
}

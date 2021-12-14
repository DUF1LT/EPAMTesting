package page.alert;

import model.alert.Alert;
import model.alert.AlertArgumentOption;
import model.alert.Condition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

public class FinamAlertPage extends BasePage {
    private final String ALERT_CONDITION_TEMPLATE = "//div[@role='presentation']//li[@value='%s']";
    private final String ALERT_ARGUMENT_OPTION_TEMPLATE = "//div[@role='presentation']//li[@value='%s']";

    @FindBy(id = "alert-form-submit")
    private WebElement alertSubmitButton;

    @FindBy(xpath = "//input[@name='condition']//preceding-sibling::button")
    private WebElement alertConditionButton;

    @FindBy(xpath = "//input[@name='level.type']//preceding-sibling::button")
    private WebElement alertArgumentOptionButton;

    @FindBy(xpath = "//input[@name='level']")
    private WebElement alertArgumentInput;

    public FinamAlertPage(WebDriver driver) {
        super(driver);
    }

    public FinamAlertPage selectAlertCondition(Alert alert) {
        waitForElementToBeClickable(alertConditionButton).click();
        switchAlertCondition(alert.getAlertCondition()).click();

        waitForElementToBeClickable(alertArgumentOptionButton).click();
        switchAlertArgumentOption(alert.getAlertArgumentOption()).click();
        logger.info("Alert conditions switched");

        return this;
    }

    public FinamAlertPage sendKeysToAlertArgument(String input) {
        waitForElementToBeClickable(alertArgumentInput).click();
        alertArgumentInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        alertArgumentInput.sendKeys(input);
        logger.info("Keys send to alert argument");

        return this;
    }

    public FinamAlertPage submitAlert() {
        waitForElementToBeClickable(alertSubmitButton);
        alertSubmitButton.click();
        logger.info("Submit alert");

        return this;
    }

    private WebElement switchAlertCondition(Condition alertCondition){
        switch(alertCondition) {
            case PriceCross:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross"));
            case PriceBelowLevel:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross_down"));
            case PriceAboveLevel:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross_up"));
            case PriceDecreaseTo:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch_down"));
            case PriceIncreaseTo:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch_up"));
            default:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch"));
        }
    }

    private WebElement switchAlertArgumentOption(AlertArgumentOption argumentOption){
        switch(argumentOption) {
            case PriceUnit:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "2"));
            case PricePercent:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "1"));
            default:
                return waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "0"));
        }
    }
}

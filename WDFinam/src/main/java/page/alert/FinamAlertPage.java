package page.alert;

import model.alert.Alert;
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

        WebElement alertCondition;
        switch(alert.getAlertCondition()) {
            case PriceCross:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross"));
                break;
            case PriceBelowLevel:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross_down"));
                break;
            case PriceAboveLevel:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "cross_up"));
                break;
            case PriceDecreaseTo:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch_down"));
                break;
            case PriceIncreaseTo:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch_up"));
                break;
            default:
                alertCondition = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "touch"));
        }
        alertCondition.click();

        waitForElementToBeClickable(alertArgumentOptionButton).click();

        WebElement alertArgumentOption;
        switch(alert.getAlertArgumentOption()) {
            case PriceUnit:
                alertArgumentOption = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "2"));
                break;
            case PricePercent:
                alertArgumentOption = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "1"));
                break;
            default:
                alertArgumentOption = waitForPresenceOfElementLocated(String.format(ALERT_CONDITION_TEMPLATE, "0"));
        }
        alertArgumentOption.click();

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
}

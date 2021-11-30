package page.alert;

import model.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamAlertPO extends BasePO {

    @FindBy(id = "alert-form-submit")
    private WebElement alertSubmitButton;

    @FindBy(xpath = "//input[@name='condition']//preceding-sibling::button")
    private WebElement alertConditionButton;

    @FindBy(xpath = "//input[@name='level.type']//preceding-sibling::button")
    private WebElement alertArgumentOptionButton;

    @FindBy(xpath = "//input[@name='level']")
    private WebElement alertArgumentInput;

    private String alertConditionTemplate = "//div[@role='presentation']//li[@value='%s']";
    private String alertArgumentOptionTemplate = "//div[@role='presentation']//li[@value='%s']";

    public FinamAlertPO(WebDriver driver) {
        super(driver);
    }

    public FinamAlertPO selectAlertCondition(Alert alert)
    {
        waitForElementToBeClickable(driver, alertConditionButton);
        alertConditionButton.click();

        WebElement alertCondition;
        switch(alert.getAlertCondition())
        {
            case PriceCross:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "cross"));
                break;
            case PriceBelowLevel:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "cross_down"));
                break;
            case PriceAboveLevel:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "cross_up"));
                break;
            case PriceDecreaseTo:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "touch_down"));
                break;
            case PriceIncreaseTo:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "touch_up"));
                break;
            default:
                alertCondition = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "touch"));
        }

        alertCondition.click();

        waitForElementToBeClickable(driver, alertArgumentOptionButton);
        alertArgumentOptionButton.click();

        WebElement alertArgumentOption;
        switch(alert.getAlertArgumentOption())
        {
            case PriceUnit:
                alertArgumentOption = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "2"));
                break;
            case PricePercent:
                alertArgumentOption = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "1"));
                break;
            default:
                alertArgumentOption = waitForPresenceOfElementLocated(driver,
                        String.format(alertConditionTemplate, "0"));
        }

        alertArgumentOption.click();

        return this;
    }

    public FinamAlertPO sendKeysToAlertArgument(String input)
    {
        waitForElementToBeClickable(driver, alertArgumentInput);
        alertArgumentInput.click();
        alertArgumentInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        alertArgumentInput.sendKeys(input);

        return this;
    }

    public FinamAlertPO submitAlert()
    {
        waitForElementToBeClickable(driver, alertSubmitButton);
        alertSubmitButton.click();

        return this;
    }
}

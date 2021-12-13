package page.request;

import model.stop.Stop;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamRequestStopPage extends FinamRequestPage {
    @FindBy(name = "orderTypeStopLoss")
    private WebElement stopLossCheckbox;

    @FindBy(xpath = "//input[@name='orderTypeStopLoss']//..//..")
    private WebElement stopLossCheckboxClick;

    @FindBy(name = "slActivationPrice")
    private WebElement stopLossInput;

    @FindBy(name = "slQuantity")
    private WebElement stopLossAmount;

    @FindBy(name = "orderTypeTakeProfit")
    private WebElement takeProfitCheckbox;

    @FindBy(xpath = "//input[@name='orderTypeTakeProfit']//..//..")
    private WebElement takeProfitCheckboxClick;

    @FindBy(name = "tpQuantity")
    private WebElement takeProfitAmount;

    @FindBy(name = "tpActivationPrice")
    private WebElement takeProfitInput;

    public FinamRequestStopPage(WebDriver driver) {
        super(driver);
    }


    public FinamRequestStopPage selectStopOptions(Stop stop) {
        if(stop.isEnableStopLoss() ^ stopLossCheckbox.isSelected()) {
            waitForElementToBeClickable(stopLossCheckboxClick).click();
        }

        if(stop.isEnableTakeProfit() ^ takeProfitCheckbox.isSelected()) {
            waitForElementToBeClickable(takeProfitCheckboxClick).click();;
        }
        logger.info("Stop option selected");

        return this;
    }

    public FinamRequestStopPage sendKeysToStopInputs(Stop stop) {
        if(stop.isEnableStopLoss()) {
            waitForElementToBeClickable(stopLossInput).click();
            stopLossInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            stopLossInput.sendKeys(stop.getStopLossPrice());

            waitForElementToBeClickable(stopLossAmount).click();
            stopLossAmount.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            stopLossAmount.sendKeys(String.valueOf(stop.getAmount()));
        }

        if(stop.isEnableTakeProfit()) {
            waitForElementToBeClickable(takeProfitInput).click();
            takeProfitInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            takeProfitInput.sendKeys(stop.getTakeProfitPrice());

            waitForElementToBeClickable(takeProfitAmount).click();
            takeProfitAmount.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            takeProfitAmount.sendKeys(String.valueOf(stop.getAmount()));
        }

        logger.info("Keys send to stop inputs");

        return this;
    }

}

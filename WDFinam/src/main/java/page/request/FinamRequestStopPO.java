package page.request;

import model.ConditionalDeal;
import model.DealCondition;
import model.Stock;
import model.Stop;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamRequestStopPO extends FinamRequestPO {

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

    public FinamRequestStopPO(WebDriver driver) {
        super(driver);
    }


    public FinamRequestStopPO selectStopOptions(Stop stop)
    {
        logger.info(String.valueOf(stop.isEnableStopLoss()) +  " ^ " + String.valueOf(stopLossCheckbox.isSelected()));
        if(stop.isEnableStopLoss() ^ stopLossCheckbox.isSelected())
        {
            waitForElementToBeClickable(driver, stopLossCheckboxClick);
            stopLossCheckboxClick.click();
        }

        logger.info(String.valueOf(stop.isEnableTakeProfit()) +  " ^ " + String.valueOf(takeProfitCheckbox.isSelected()));


        if(stop.isEnableTakeProfit() ^ takeProfitCheckbox.isSelected())
        {
            waitForElementToBeClickable(driver, takeProfitCheckboxClick);
            takeProfitCheckboxClick.click();
        }

        return this;
    }

    public FinamRequestStopPO sendKeysToStopInputs(Stop stop, Stock stock)
    {
        if(stop.isEnableStopLoss())
        {
            waitForElementToBeClickable(driver, stopLossInput);
            stopLossInput.click();
            stopLossInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            stopLossInput.sendKeys(stop.getStopLossPrice());

            waitForElementToBeClickable(driver, stopLossAmount);
            stopLossAmount.click();
            stopLossAmount.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            stopLossAmount.sendKeys(String.valueOf(stock.getAmount()));
        }

        if(stop.isEnableTakeProfit())
        {
            waitForElementToBeClickable(driver, takeProfitInput);
            takeProfitInput.click();
            takeProfitInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            takeProfitInput.sendKeys(stop.getTakeProfitPrice());

            waitForElementToBeClickable(driver, takeProfitAmount);
            takeProfitAmount.click();
            takeProfitAmount.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            takeProfitAmount.sendKeys(String.valueOf(stock.getAmount()));
        }

        return this;
    }

}

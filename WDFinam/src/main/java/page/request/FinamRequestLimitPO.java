package page.request;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamRequestLimitPO extends FinamRequestPO{

    @FindBy(name = "limitPrice")
    private WebElement limitPriceInput;

    public FinamRequestLimitPO(WebDriver driver) {
        super(driver);
    }

    public FinamRequestLimitPO sendKeysToLimitPriceInput(String input)
    {
        BasePO.waitForElementToBeClickable(driver, limitPriceInput);
        limitPriceInput.click();
        limitPriceInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        limitPriceInput.sendKeys(input);

        logger.info("Send keys (" + input +") to limit price input");

        return this;
    }

    @Override
    public FinamRequestLimitPO sendKeysToStockAmountInput(String input) {
        super.sendKeysToStockAmountInput(input);
        return this;
    }
}

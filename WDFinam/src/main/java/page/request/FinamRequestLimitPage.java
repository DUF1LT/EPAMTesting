package page.request;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

public class FinamRequestLimitPage extends FinamRequestPage {

    @FindBy(name = "limitPrice")
    private WebElement limitPriceInput;

    public FinamRequestLimitPage(WebDriver driver) {
        super(driver);
    }

    public FinamRequestLimitPage sendKeysToLimitPriceInput(String input) {
        waitForElementToBeClickable(limitPriceInput).click();;
        limitPriceInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        limitPriceInput.sendKeys(input);
        logger.info("Send keys (" + input +") to limit price input");

        return this;
    }

    @Override
    public FinamRequestLimitPage sendKeysToStockAmountInput(String input) {
        super.sendKeysToStockAmountInput(input);

        return this;
    }
}

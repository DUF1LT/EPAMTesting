package page.request;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

public class FinamRequestPage extends BasePage {
    @FindBy(id = "order-type-market")
    private WebElement marketPanel;

    @FindBy(id = "order-type-cond")
    private WebElement conditionalRequestPanel;

    @FindBy(id = "order-type-limit")
    private WebElement limitPanel;

    @FindBy(id = "order-type-stop")
    private WebElement stopPanel;

    @FindBy(xpath = "//div[@id='order-form-side']//button[@value='2']")
    private WebElement sellOptionButton;

    @FindBy(name = "quantity")
    private WebElement stocksAmountInput;

    @FindBy(id = "order-form-submit")
    private WebElement orderButton;

    @FindBy(id = "order-confirm-submit")
    private WebElement submitButton;

    @FindBy(id = "order-form-close")
    private WebElement closeSubmitButton;

    @FindBy(xpath = "//button[@data-test='newOrder']")
    private WebElement newOrderButton;

    public FinamRequestPage(WebDriver driver) {
        super(driver);
    }

    public FinamRequestPage sendKeysToStockAmountInput(String input) {
        waitForElementToBeClickable(stocksAmountInput).click();;
        stocksAmountInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        stocksAmountInput.sendKeys(input);
        logger.info("Send keys (" + input +") to amount input");

        return this;
    }

    public FinamRequestPage switchToMarketPanel() {
        waitForElementToBeClickable(marketPanel).click();
        logger.info("Switched to market request panel");

        return this;
    }

    public FinamRequestConditionPage switchToConditionalRequestPanel() {
        waitForElementToBeClickable(conditionalRequestPanel).click();;
        logger.info("Switched to conditional request panel");

        return new FinamRequestConditionPage(driver);
    }

    public FinamRequestLimitPage switchToLimitPanel() {
        waitForElementToBeClickable(limitPanel).click();
        logger.info("Switched to limit request panel");

        return new FinamRequestLimitPage(driver);
    }

    public FinamRequestStopPage switchToStopPanel() {
        waitForElementToBeClickable(stopPanel).click();
        logger.info("Switched to stop request panel");

        return new FinamRequestStopPage(driver);
    }

    public FinamRequestPage switchToRequestSellOption() {
        waitForElementToBeClickable(sellOptionButton).click();
        logger.info("Switched to request sell option");

        return this;
    }

    public FinamRequestPage submitRequest() {
        waitForElementToBeClickable(orderButton).click();
        waitForElementToBeClickable(submitButton).click();
        waitForVisibilityOfElement(newOrderButton).click();
        waitForVisibilityOfElement(closeSubmitButton).click();
        logger.info("Request submitted");

        return this;
    }
}

package page.request;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamRequestPO extends BasePO {

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

    public FinamRequestPO(WebDriver driver) {
        super(driver);
    }

    public FinamRequestPO sendKeysToStockAmountInput(String input)
    {
        waitForElementToBeClickable(driver, stocksAmountInput);
        stocksAmountInput.click();
        stocksAmountInput.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        stocksAmountInput.sendKeys(input);

        logger.info("Send keys (" + input +") to amount input");

        return this;
    }

    public FinamRequestPO switchToMarketPanel()
    {
        waitForElementToBeClickable(driver, marketPanel);
        marketPanel.click();

        return this;
    }

    public FinamRequestConditionPO switchToConditionalRequestPanel()
    {
        waitForElementToBeClickable(driver, conditionalRequestPanel);
        conditionalRequestPanel.click();

        logger.info("Switched to conditional request panel");

        return new FinamRequestConditionPO(driver);
    }

    public FinamRequestLimitPO switchToLimitPanel()
    {
        waitForElementToBeClickable(driver, limitPanel);
        limitPanel.click();

        return new FinamRequestLimitPO(driver);
    }

    public FinamRequestStopPO switchToStopPanel()
    {
        waitForElementToBeClickable(driver, stopPanel);
        stopPanel.click();

        return new FinamRequestStopPO(driver);
    }

    public FinamRequestPO switchToRequestSellOption()
    {
        waitForElementToBeClickable(driver, sellOptionButton);
        sellOptionButton.click();

        logger.info("Switched to request sell option");

        return this;
    }

    public FinamRequestPO submitRequest()
    {
        waitForElementToBeClickable(driver, orderButton);
        orderButton.click();

        waitForElementToBeClickable(driver, submitButton);
        submitButton.click();

        waitForVisibilityOfElement(driver, newOrderButton);
        newOrderButton.click();

        closeSubmitButton.click();

        logger.info("Request submitted");

        return this;
    }
}

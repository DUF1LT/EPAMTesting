package page;

import model.ConditionalDeal;
import model.DealCondition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamRequestPO extends BasePO{

    @FindBy(id = "order-type-market")
    private WebElement marketPanel;

    @FindBy(id = "order-type-cond")
    private WebElement conditionalRequestPanel;

    @FindBy(xpath = "//span[contains(text(), 'Условие')]/../..")
    private WebElement conditionButton;

    @FindBy(xpath = "//label[contains(text(),'Время активации')]/../div[2]//input")
    private WebElement conditionTimeArgumentButton;

    @FindBy(xpath = "//label[contains(text(),'Цена активации')]/..//input")
    private WebElement conditionArgumentButton;

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

    private final String conditionXpathTemplate = "//div[@role='presentation']//li[@value='%s']";
    private final String conditionTimeArgumentTemplate = "//div[@role='presentation']//li[contains(text(), '%s')]";

    public FinamRequestPO(WebDriver driver) {
        super(driver);
    }

    public FinamRequestPO switchToMarketPanel()
    {
        waitForElementToBeClickable(driver, marketPanel);
        marketPanel.click();

        return this;
    }

    public FinamRequestPO sendKeysToStockAmountInput(String input)
    {
        waitForElementToBeClickable(driver, stocksAmountInput);
        stocksAmountInput.click();
        stocksAmountInput.sendKeys(Keys.BACK_SPACE);
        stocksAmountInput.sendKeys(input);

        logger.info("Send keys (" + input +") to amount input");

        return this;
    }

    public FinamRequestPO switchToConditionalRequestPanel()
    {
        waitForElementToBeClickable(driver, conditionalRequestPanel);
        conditionalRequestPanel.click();

        logger.info("Switched to conditional request panel");

        return this;
    }

    public FinamRequestPO switchToRequestSellOption()
    {
        waitForElementToBeClickable(driver, sellOptionButton);
        sellOptionButton.click();

        logger.info("Switched to request sell option");

        return this;
    }

    public FinamRequestPO selectRequestCondition(ConditionalDeal deal)
    {
        waitForElementToBeClickable(driver, conditionButton);
        conditionButton.click();

        WebElement conditionalElement;

        switch(deal.getCondition())
        {
            case UpperOrEquals:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "8"));
                break;
            case LowerOrEquals:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "9"));
                break;
            default:
                conditionalElement = waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "5"));
        }

        conditionalElement.click();

        if(deal.getCondition() == DealCondition.CompletionTime)
        {
            waitForElementToBeClickable(driver, conditionTimeArgumentButton);
            conditionTimeArgumentButton.click();

            WebElement timeElement = waitForPresenceOfElementLocated(driver,
                    String.format(conditionTimeArgumentTemplate, deal.getConditionArgument()));

            timeElement.click();
        }
        else
        {
            waitForElementToBeClickable(driver, conditionArgumentButton);
            conditionArgumentButton.click();
            conditionalElement.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            conditionalElement.sendKeys(deal.getConditionArgument());
        }

        logger.info("Condition switched to " + deal.getCondition() + " with " + deal.getConditionArgument() + " arguments");

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

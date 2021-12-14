package page.request;

import model.conditional.ConditionalRequest;
import model.conditional.Condition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamRequestConditionPage extends FinamRequestPage {

    @FindBy(xpath = "//span[contains(text(), 'Условие')]/../..")
    private WebElement conditionButton;

    @FindBy(xpath = "//label[contains(text(),'Время активации')]/../div[2]//input")
    private WebElement conditionTimeArgumentButton;

    @FindBy(xpath = "//label[contains(text(),'Цена активации')]/..//input")
    private WebElement conditionArgumentButton;

    private final String CONDITION_TEMPLATE = "//div[@role='presentation']//li[@value='%s']";
    private final String CONDITION_TIME_ARGUMENT_TEMPLATE = "//div[@role='presentation']//li[contains(text(), '%s')]";

    public FinamRequestConditionPage(WebDriver driver) {
        super(driver);
    }

    public FinamRequestConditionPage selectRequestCondition(ConditionalRequest deal) {
        waitForElementToBeClickable(conditionButton).click();;
        WebElement conditionalElement = switchCondition(deal.getCondition());
        conditionalElement.click();

        if(deal.getCondition() == Condition.CompletionTime) {
            waitForElementToBeClickable(conditionTimeArgumentButton).click();
            waitForPresenceOfElementLocated(String.format(CONDITION_TIME_ARGUMENT_TEMPLATE, deal.getConditionArgument())).click();;
        }
        else {
            waitForElementToBeClickable(conditionArgumentButton).click();;
            conditionalElement.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            conditionalElement.sendKeys(deal.getConditionArgument());
        }
        logger.info("Condition switched to " + deal.getCondition() + " with " + deal.getConditionArgument() + " arguments");

        return this;
    }

    private WebElement switchCondition(Condition condition){
        switch(condition) {
            case UpperOrEquals:
                return waitForPresenceOfElementLocated(String.format(CONDITION_TEMPLATE, "8"));
            case LowerOrEquals:
                return waitForPresenceOfElementLocated(String.format(CONDITION_TEMPLATE, "9"));
            default:
                return waitForPresenceOfElementLocated(String.format(CONDITION_TEMPLATE, "5"));
        }
    }


}

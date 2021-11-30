package page.request;

import model.ConditionalDeal;
import model.DealCondition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamRequestConditionPO extends FinamRequestPO{

    @FindBy(xpath = "//span[contains(text(), 'Условие')]/../..")
    private WebElement conditionButton;

    @FindBy(xpath = "//label[contains(text(),'Время активации')]/../div[2]//input")
    private WebElement conditionTimeArgumentButton;

    @FindBy(xpath = "//label[contains(text(),'Цена активации')]/..//input")
    private WebElement conditionArgumentButton;

    private final String conditionXpathTemplate = "//div[@role='presentation']//li[@value='%s']";
    private final String conditionTimeArgumentTemplate = "//div[@role='presentation']//li[contains(text(), '%s')]";

    public FinamRequestConditionPO(WebDriver driver) {
        super(driver);
    }

    public FinamRequestConditionPO selectRequestCondition(ConditionalDeal deal)
    {
        BasePO.waitForElementToBeClickable(driver, conditionButton);
        conditionButton.click();

        WebElement conditionalElement;

        switch(deal.getCondition())
        {
            case UpperOrEquals:
                conditionalElement = BasePO.waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "8"));
                break;
            case LowerOrEquals:
                conditionalElement = BasePO.waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "9"));
                break;
            default:
                conditionalElement = BasePO.waitForPresenceOfElementLocated(driver,
                        String.format(conditionXpathTemplate, "5"));
        }

        conditionalElement.click();

        if(deal.getCondition() == DealCondition.CompletionTime)
        {
            BasePO.waitForElementToBeClickable(driver, conditionTimeArgumentButton);
            conditionTimeArgumentButton.click();

            WebElement timeElement = BasePO.waitForPresenceOfElementLocated(driver,
                    String.format(conditionTimeArgumentTemplate, deal.getConditionArgument()));

            timeElement.click();
        }
        else
        {
            BasePO.waitForElementToBeClickable(driver, conditionArgumentButton);
            conditionArgumentButton.click();
            conditionalElement.sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
            conditionalElement.sendKeys(deal.getConditionArgument());
        }

        logger.info("Condition switched to " + deal.getCondition() + " with " + deal.getConditionArgument() + " arguments");

        return this;
    }


}

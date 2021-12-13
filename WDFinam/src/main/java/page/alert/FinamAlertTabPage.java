package page.alert;

import model.alert.Alert;
import model.alert.Condition;
import model.enums.FindByOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

import java.time.Duration;

public class FinamAlertTabPage extends BasePage {
    private final String ALERT_COMPANY_TEMPLATE = "//p[@data-test='ticker' and contains(text(),'%s')]";
    private final String ALERT_ARGUMENT_TEMPLATE = "//..//p[@data-test='level' and contains(text(),'%s')]";
    private final String ALERT_CONDITION_TEMPLATE = "//..//following-sibling::div//p[@data-test='condition' and contains(text(),'%s')]";

    @FindBy(id = "alerts-tab-button")
    private WebElement alertsTab;

    public FinamAlertTabPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAlertExists(Alert alert) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return isWebElementExists(String.format(ALERT_COMPANY_TEMPLATE + ALERT_CONDITION_TEMPLATE + ALERT_ARGUMENT_TEMPLATE,
                alert.getCompany(), getStringFromCondition(alert.getAlertCondition()), alert.getAlertArgument()), FindByOption.XPATH);
    }

    public FinamAlertTabPage closeAlertTab() {
        waitForElementToBeClickable(alertsTab).click();
        logger.info("Close alert tab");

        return this;
    }

    private String getStringFromCondition(Condition condition)
    {
        switch (condition)
        {
            case PriceCross:
                return "Пересечет";
            case PriceBelowLevel:
                return "Ниже";
            case PriceAboveLevel:
                return "Выше";
            case PriceDecreaseTo:
                return "Снизится до";
            case PriceIncreaseTo:
                return "Вырастет до";
            default:
                return "Достигнет";
        }
    }
}

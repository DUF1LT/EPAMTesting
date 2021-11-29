package page;

import model.Alert;
import model.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class FinamAlertTabPO extends BasePO
{

    @FindBy(id = "alerts-tab-button")
    private WebElement alertsTab;

    private String alertDivTemplate = "//div//p[@data-test='ticker' and contains(text(), '%s')]";

    public FinamAlertTabPO(WebDriver driver) {
        super(driver);
    }

    public boolean isAlertExists(Stock stock)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if(driver.findElements(By.xpath(String.format(alertDivTemplate, stock.getCompany()))).size() == 0)
        {
            logger.error("Alert wasn't found");
            return false;
        }

        logger.info("Alert was found");
        return true;
    }

    public FinamAlertTabPO closeAlertTab()
    {
        waitForElementToBeClickable(driver, alertsTab);
        alertsTab.click();

        return this;
    }
}

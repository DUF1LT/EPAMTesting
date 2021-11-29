package page;

import model.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class FinamBriefcasePO extends BasePO{
    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='positions']")
    private WebElement briefcasePanel;

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='orders']")
    private WebElement requestPanel;

    private final String stockBriefcaseCellXpathTemplate = "//div[@role='gridcell']/p[contains(text(), '%s')]";
        private final String stockBriefcaseAmountXpathTemplate = "//div[@data-row-id='%s']//div[@data-test='quantity']//p";
    private final String stockRequestCell = "//p[@title='Условная']//..//preceding-sibling::div//p[contains(text(),'%s')]";

    public FinamBriefcasePO(WebDriver driver) {
        super(driver);
    }

    public FinamBriefcasePO switchToRequestPanel()
    {
        waitForElementToBeClickable(driver, requestPanel);
        requestPanel.click();

        logger.info("Switched to request panel");

        return this;
    }

    public FinamBriefcasePO switchToBriefcasePanel()
    {
        waitForElementToBeClickable(driver, briefcasePanel);
        briefcasePanel.click();

        logger.info("Switched to briefcase panel");

        return this;
    }

    public int getStockCurrentAmount(Stock stock)
    {
        switchToBriefcasePanel();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if(driver.findElements(By.xpath(String.format(stockBriefcaseAmountXpathTemplate, stock.getCompany()))).size() == 0)
            return 0;
        else
        {
            WebElement stockAmountLabel = driver.findElement(By.xpath(
                    String.format(stockBriefcaseAmountXpathTemplate,stock.getCompany())));

            waitForVisibilityOfElement(driver, stockAmountLabel);

            return Integer.parseInt(stockAmountLabel.getText());
        }
    }

    public boolean isStockExist(Stock stock)
    {
        switchToBriefcasePanel();

        if(driver.findElements(By.xpath(String.format(stockBriefcaseCellXpathTemplate, stock.getCompany()))).size() == 0) {
            logger.error("Stocks wasn't found");
            return false;
        }
        logger.info("Stocks was found");

        return true;
    }

    public boolean isRequestExist(Stock stock)
    {
        switchToRequestPanel();

        if(driver.findElements(By.xpath(String.format(stockRequestCell, stock.getCompany()))).size() == 0) {
            logger.error("Request wasn't found");
            return false;
        }
        logger.info("Request was found");

        return true;
    }
}

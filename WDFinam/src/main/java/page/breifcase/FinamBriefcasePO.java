package page.breifcase;

import model.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

import java.time.Duration;

public class FinamBriefcasePO extends BasePO {
    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='positions']")
    private WebElement briefcasePanel;

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='orders']")
    private WebElement requestPanel;

    private final String stockBriefcaseCellXpathTemplate = "//div[@role='gridcell']/p[contains(text(), '%s')]";
    private final String stockBriefcaseAmountXpathTemplate = "//div[@data-row-id='%s']//div[@data-test='quantity']//p";
    private final String stockRequestCellTemplate = "//p[@title='Условная']//..//preceding-sibling::div//p[@title='%s']";
    private final String limitRequestCellTemplate = "//p[@title='Лимит']//..//preceding-sibling::div//p[@title='%s']";
    private final String stopLossRequestCellTemplate = "//p[@title='Стоп-лосс']/ancestor::div[@role='row']//p[@title='%s']";
    private final String takeProfitRequestCellTemplate = "//p[@title='Тейк-профит']/ancestor::div[@role='row']//p[@title='%s']";

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

    public boolean isConditionalRequestExist(Stock stock)
    {
        switchToRequestPanel();

        if(driver.findElements(By.xpath(String.format(stockRequestCellTemplate, stock.getCompany()))).size() == 0) {
            logger.error("Conditional request wasn't found");
            return false;
        }
        logger.info("Conditional request was found");

        return true;
    }

    public boolean isLimitRequestExist(Stock stock)
    {
        switchToRequestPanel();

        if(driver.findElements(By.xpath(String.format(limitRequestCellTemplate, stock.getCompany()))).size() == 0) {
            logger.error("Limit request wasn't found");
            return false;
        }
        logger.info("Limit request was found");

        return true;
    }

    public boolean isStopRequestExist(Stock stock)
    {
        switchToRequestPanel();

        if(driver.findElements(By.xpath(String.format(stopLossRequestCellTemplate, stock.getCompany()))).size() == 0 &&
                driver.findElements(By.xpath(String.format(takeProfitRequestCellTemplate, stock.getCompany()))).size() == 0) {
            logger.error("Stop request wasn't found");
            return false;
        }
        logger.info("Stop request was found");

        return true;
    }

}

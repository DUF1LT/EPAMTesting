package page;

import model.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinamHomePO extends BasePO {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsElement;

    @FindBy(id = "market-group-dropdown")
    private WebElement marketsGroupDropdown;

    @FindBy(xpath = "//li[@value ='E2']")
    private WebElement salesAndFondGroup;

    @FindBy(id = "order-button-open-form")
    private WebElement requestButton;

    @FindBy(name = "quantity")
    private WebElement salesAmountInput;

    @FindBy(id = "order-form-submit")
    private WebElement orderButton;

    @FindBy(id = "order-confirm-submit")
    private WebElement submitButton;

    @FindBy(id = "order-form-close")
    private WebElement closeSubmitButton;

    protected FinamHomePO(WebDriver driver) {
        super(driver);
    }

    public FinamHomePO buyStocks(Stock stock){

        waitForElementToBeClickable(driver, marketsElement);
        marketsElement.click();

        waitForElementToBeClickable(driver, marketsGroupDropdown);
        marketsGroupDropdown.click();

        waitForElementToBeClickable(driver, salesAndFondGroup);
        salesAndFondGroup.click();

        String companyDivXpathTemplate = String.format("//p/span[contains(text(),'%s')]", stock.getCompany());
        WebElement companyDiv = waitForPresenceOfElementLocated(driver, companyDivXpathTemplate);
        companyDiv.click();

        waitForElementToBeClickable(driver, requestButton);
        requestButton.click();

        waitForElementToBeClickable(driver, salesAmountInput);
        salesAmountInput.click();
        salesAmountInput.sendKeys(Keys.BACK_SPACE);
        salesAmountInput.sendKeys(String.valueOf(stock.getAmount()));

        waitForElementToBeClickable(driver, orderButton);
        orderButton.click();

        waitForElementToBeClickable(driver, submitButton);
        submitButton.click();
        logger.info("Bought stocks " + stock.getCompany() + "(" + stock.getAmount() + ")");
        return this;
    }

    public FinamHomePO closeSubmitWindows()
    {
        waitForVisibilityOfElement(driver, closeSubmitButton);
        closeSubmitButton.sendKeys(Keys.ESCAPE);
        closeSubmitButton.click();

        logger.info("Submit window closed");
        return this;
    }

    public boolean isNewStocksExist(String company)
    {
        String companyCellXpathTemplate = String.format("//div[@role='gridcell']/p[contains(text(), '%s')]", company);
        if(driver.findElements(By.xpath(companyCellXpathTemplate)).size() == 0) {
            logger.error("Bought stocks wasn't found");
            return false;
        }
        logger.info("Bought stocks was found");
        return true;
    }
}

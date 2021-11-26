import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class FinamHomePO extends BasePO {

    protected FinamHomePO(WebDriver driver) {
        super(driver);
    }

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

    public FinamHomePO buyCompanyNSales(String company, int n){

        waitForElementToBeClickable(driver, marketsElement);
        marketsElement.click();

        waitForElementToBeClickable(driver, marketsGroupDropdown);
        marketsGroupDropdown.click();

        waitForElementToBeClickable(driver, salesAndFondGroup);
        salesAndFondGroup.click();

        String companyDivXpathTemplate = String.format("//p/span[contains(text(),'%s')]", company);
        WebElement companyDiv = waitForPresenceOfElementLocated(driver, companyDivXpathTemplate);
        companyDiv.click();

        waitForElementToBeClickable(driver, requestButton);
        requestButton.click();

        waitForElementToBeClickable(driver, salesAmountInput);
        salesAmountInput.click();
        salesAmountInput.sendKeys(Keys.BACK_SPACE);
        salesAmountInput.sendKeys(String.valueOf(n));

        waitForElementToBeClickable(driver, orderButton);
        orderButton.click();

        waitForElementToBeClickable(driver, submitButton);
        submitButton.click();

        return this;
    }

    public FinamHomePO closeSubmitWindows()
    {
        waitForVisibilityOfElement(driver, closeSubmitButton);
        closeSubmitButton.sendKeys(Keys.ESCAPE);
        closeSubmitButton.click();

        return this;
    }

    public boolean isNewSalesExists(String company)
    {
        String companyCellXpathTemplate = String.format("//div[@role='gridcell']/p[contains(text(), '%s')]", company);
        if(driver.findElements(By.xpath(companyCellXpathTemplate)).size() == 0)
            return false;

        return true;
    }




}

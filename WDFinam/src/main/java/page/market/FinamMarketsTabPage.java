package page.market;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

public class FinamMarketsTabPage extends BasePage {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsTab;

    @FindBy(id = "market-group-dropdown")
    private WebElement marketsGroupDropdown;

    @FindBy(xpath = "//li[@value='E2']")
    private WebElement salesAndFondGroup;

    private final String COMPANY_STOCK_ELEMENT_TEMPLATE = "//p/span[contains(text(),'%s')]";

    public FinamMarketsTabPage(WebDriver driver) {
        super(driver);
    }

    public FinamMarketsTabPage selectCompany(String company) {
        switchToStocksAndFondsPanel();
        waitForPresenceOfElementLocated(String.format(COMPANY_STOCK_ELEMENT_TEMPLATE, company)).click();
        logger.info("Stock " + company + " was selected");

        return this;
    }

    private FinamMarketsTabPage switchToStocksAndFondsPanel() {
        waitForElementToBeClickable(marketsGroupDropdown).click();
        waitForElementToBeClickable(salesAndFondGroup).click();
        logger.info("Menu switched to Stocks and Fonds");

        return this;
    }

    public FinamMarketsTabPage closeMarketsTab() {
        waitForElementToBeClickable(marketsTab).click();
        logger.info("Close markets tab");

        return this;
    }
}

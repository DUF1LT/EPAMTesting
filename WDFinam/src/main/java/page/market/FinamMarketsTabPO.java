package page.market;

import model.Stock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePO;

public class FinamMarketsTabPO extends BasePO {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsTab;

    @FindBy(id = "market-group-dropdown")
    private WebElement marketsGroupDropdown;

    @FindBy(xpath = "//li[@value='E2']")
    private WebElement salesAndFondGroup;

    private final String companyStockElementXpathTemplate = "//p/span[contains(text(),'%s')]";

    public FinamMarketsTabPO(WebDriver driver) {
        super(driver);
    }

    public FinamMarketsTabPO selectStock(Stock stock)
    {
        switchToStocksAndFondsPanel();

        WebElement companyDiv = waitForPresenceOfElementLocated(driver,
                String.format(companyStockElementXpathTemplate, stock.getCompany()));

        companyDiv.click();

        logger.info("Stock " + stock.getCompany() + " was selected");

        return this;
    }

    private FinamMarketsTabPO switchToStocksAndFondsPanel()
    {
        waitForElementToBeClickable(driver, marketsGroupDropdown);
        marketsGroupDropdown.click();

        waitForElementToBeClickable(driver, salesAndFondGroup);
        salesAndFondGroup.click();

        logger.info("Menu switched to Stocks and Fonds");

        return this;
    }

    public FinamMarketsTabPO closeMarketsTab()
    {
        waitForElementToBeClickable(driver, marketsTab);
        marketsTab.click();

        return this;
    }
}

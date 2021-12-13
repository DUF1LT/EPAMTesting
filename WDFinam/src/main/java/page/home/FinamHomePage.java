package page.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;
import page.alert.FinamAlertPage;
import page.alert.FinamAlertTabPage;
import page.breifcase.FinamBriefcasePage;
import page.market.FinamMarketsTabPage;
import page.request.FinamRequestPage;

public class FinamHomePage extends BasePage {

    @FindBy(id = "markets-tab-button")
    private WebElement marketsTab;

    @FindBy(id = "alerts-tab-button")
    private WebElement alertsTab;

    @FindBy(id = "order-button-open-form")
    private WebElement requestButton;

    @FindBy(id = "alert-form-open-button")
    private WebElement alertButton;

    @FindBy(id = "settings-menu-button")
    private WebElement settingsButton;

    @FindBy(id = "settings-close")
    private WebElement settingsCloseButton;

    @FindBy(xpath = "//li[@value='appSettings']")
    private WebElement appSettingsButton;

    @FindBy(id = "security-title-switch")
    private WebElement securityTitleSwitch;

    @FindBy(xpath = "//li[@value='ticker']")
    private WebElement tickerValue;

    private FinamBriefcasePage briefcasePO;

    public FinamHomePage(WebDriver driver) {
        super(driver);
        briefcasePO = new FinamBriefcasePage(driver);
    }

    public FinamMarketsTabPage openMarketsTab() {
        waitForElementToBeClickable(marketsTab).click();
        logger.info("Open markets tab");

        return new FinamMarketsTabPage(driver);
    }

    public FinamAlertTabPage openAlertsTab() {
        waitForElementToBeClickable(alertsTab).click();
        logger.info("Open alerts tab");

        return new FinamAlertTabPage(driver);
    }

    public FinamRequestPage openRequest() {
        waitForElementToBeClickable(requestButton).click();
        logger.info("Open requests tab");

        return new FinamRequestPage(driver);
    }

    public FinamAlertPage openAlertWindow() {
        waitForElementToBeClickable(alertButton).click();
        logger.info("Open alert window");

        return new FinamAlertPage(driver);
    }

    public FinamHomePage openSettings() {
        waitForElementToBeClickable(settingsButton).click();
        logger.info("Open settings");

        return this;
    }

    public FinamHomePage openAppSettings() {
        waitForElementToBeClickable(appSettingsButton).click();
        logger.info("Open app settings");

        return this;
    }

    public FinamHomePage setTickerToolView(){
        waitForElementToBeClickable(securityTitleSwitch).click();
        waitForElementToBeClickable(tickerValue).click();
        logger.info("Set ticker tool view");

        return this;
    }

    public FinamHomePage closeSettings(){
        waitForVisibilityOfElement(settingsCloseButton).click();
        logger.info("Close settings");

        return this;
    }

    public FinamBriefcasePage getBriefcasePO() {
        return briefcasePO;
    }
}

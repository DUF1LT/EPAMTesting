package page.breifcase;

import model.conditional.ConditionalRequest;
import model.enums.FindByOption;
import model.limit.Limit;
import model.market.MarketRequest;
import model.stop.Stop;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.BasePage;

import java.time.Duration;

public class FinamBriefcasePage extends BasePage {
    private final String STOCK_BRIEFCASE_AMOUNT_TEMPLATE = "//div[@data-row-id='%s']//div[@data-test='trendBadge']//p";

    private final String REQUEST_COMPANY_TEMPLATE = "//div[@role='row']//div[1]//p[contains(text(),'%s')]";
    private final String REQUEST_TYPE_TEMPLATE = "//ancestor::div[@role='row']//div[4]//p[contains(text(),'%s')]";
    private final String REQUEST_PRICE_TEMPLATE = "//ancestor::div[@role='row']//p[contains(text(),'%s')]";
    private final String REQUEST_AMOUNT_TEMPLATE = "//ancestor::div[@role='row']//div[5]//p[contains(text(),'%s')]";

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='positions']")
    private WebElement briefcasePanel;

    @FindBy(xpath = "//div[@id='portfolio-tabs']//button[@value='orders']")
    private WebElement requestPanel;

    public FinamBriefcasePage(WebDriver driver) {
        super(driver);
    }

    public FinamBriefcasePage switchToRequestPanel() {
        waitForElementToBeClickable(requestPanel).click();
        logger.info("Switched to request panel");

        return this;
    }

    public FinamBriefcasePage switchToBriefcasePanel() {
        waitForElementToBeClickable(briefcasePanel).click();
        logger.info("Switched to briefcase panel");

        return this;
    }

    public int getStockCurrentAmount(MarketRequest marketRequest) {
        switchToBriefcasePanel();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if(!isWebElementExists(String.format(STOCK_BRIEFCASE_AMOUNT_TEMPLATE, marketRequest.getCompany()), FindByOption.XPATH))
            return 0;
        else {
            WebElement stockAmountLabel = findElement(String.format(STOCK_BRIEFCASE_AMOUNT_TEMPLATE, marketRequest.getCompany()),
                    FindByOption.XPATH);

            return Integer.parseInt(waitForVisibilityOfElement(stockAmountLabel).getText());
        }
    }

     public boolean isLimitExists(Limit limit) {
         switchToRequestPanel();

         return isWebElementExists(generateXpathForRequest(limit.getCompany(),
                 "Лимит",
                 limit.getPrice(),
                 limit.getAmount()),
                FindByOption.XPATH);
    }

    public boolean isConditionalRequestExists(ConditionalRequest conditionalRequest) {
        switchToRequestPanel();

        return isWebElementExists(generateXpathForRequest(conditionalRequest.getCompany(),
                "Условная",
                conditionalRequest.getConditionArgument(),
                conditionalRequest.getAmount()),
                FindByOption.XPATH);
    }

    public boolean isStopRequestExists(Stop stop) {
        switchToRequestPanel();

        return isWebElementExists(generateXpathForRequest(stop.getCompany(),
                "Стоп-лосс",
                stop.getStopLossPrice(),
                stop.getAmount()),
                FindByOption.XPATH)
                ||
                isWebElementExists(generateXpathForRequest(stop.getCompany(),
                "Тейк-профит",
                stop.getTakeProfitPrice(),
                stop.getAmount()),
                FindByOption.XPATH);
    }

    private String generateXpathForRequest(String company, String type, String price, String amount){
        return  String.format(REQUEST_COMPANY_TEMPLATE + REQUEST_TYPE_TEMPLATE + REQUEST_PRICE_TEMPLATE + REQUEST_AMOUNT_TEMPLATE,
                company, type, price, amount);
    }

}

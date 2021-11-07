package pageobject_model;

import jdk.jfr.Timespan;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GoogleCloudMainPO extends AbstractPage {

    private Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
            .ignoring(NoSuchElementException.class);

    private final String HOME_PAGE = "https://cloud.google.com/";

    @FindBy(xpath = "/html/body/section/devsite-header/div/div[1]/div/div/div[2]/devsite-search/form/div[1]/div/input")
    private WebElement searchInput;

    @Override
    public GoogleCloudMainPO openPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(HOME_PAGE);
        return this;
    }

    public GoogleCloudMainPO enterTermToSearchInputAndFind(String Term)
    {
        searchInput.click();
        searchInput.sendKeys(Term);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public GoogleCloudMainPO openTermReferenceAtFound(String Term)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//*[contains(text(),'" + Term + "')]"));
            }
        });

        WebElement reference = driver.findElement(By.xpath("//*[contains(text(),'" + Term + "')]"));
        reference.click();
        return this;
    }

    public GoogleCloudMainPO switchToFrame()
    {
        driver.get("https://cloudpricingcalculator.appspot.com");
        return this;
    }

    public GoogleCloudMainPO setVMType(String type)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//*[contains(@title,'" + type + "')]"));
            }
        });
        WebElement computeEngine = driver.findElement(By.xpath("//*[contains(@title,'" + type + "')]"));
        computeEngine.click();
        return this;
    }

    public GoogleCloudMainPO setNumbersOfInstances(int numbers)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//input[contains(@type,'number')]"));
            }
        });
        WebElement nOfInstances = driver.findElement(By.xpath("//*[contains(@type,'number')]"));
        nOfInstances.click();
        nOfInstances.sendKeys(String.valueOf(numbers));
        return this;
    }

    public GoogleCloudMainPO setFreeOS()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_87"));
            }
        });

        WebElement osSelect = driver.findElement(By.id("select_87"));
        osSelect.click();
        WebElement freeOS = driver.findElement(By.xpath("//md-option[contains(@value, 'free')]"));
        freeOS.click();
        return this;
    }

    public GoogleCloudMainPO setRegularMachineClass()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_91"));
            }
        });

        WebElement machineclass = driver.findElement(By.id("select_91"));
        machineclass.click();

        WebElement machineTypeOption = driver.findElement(By.id("select_option_89"));
        machineTypeOption.click();
        return this;
    }

    public GoogleCloudMainPO setE2S8MachineType()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_value_label_70"));
            }
        });

        WebElement machinetype = driver.findElement(By.id("select_value_label_70"));
        machinetype.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_271"));
            }
        });

        WebElement machineTypeOption = driver.findElement(By.id("select_option_271"));
        machineTypeOption.click();
        return this;
    }

    public GoogleCloudMainPO set1NOfNodes()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("input_120"));
            }
        });

        WebElement nofnodes = driver.findElement(By.id("input_120"));
        nofnodes.click();
        nofnodes.sendKeys(String.valueOf(1));

        return this;
    }

    public GoogleCloudMainPO set1TeslaV100GPU()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//md-checkbox[contains(@aria-label, 'Add GPUs')]"));
            }
        });

        WebElement gpusCheckbox = driver.findElement(By.xpath("//md-checkbox[contains(@aria-label, 'Add GPUs')]"));
        gpusCheckbox.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_411"));
            }
        });

        WebElement nofGpus = driver.findElement(By.id("select_411"));
        nofGpus.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_418"));
            }
        });

        WebElement nofGpus4 = driver.findElement(By.id("select_option_418"));
        nofGpus4.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_413"));
            }
        });

        WebElement gpuType = driver.findElement(By.id("select_413"));
        gpuType.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_421"));
            }
        });

        WebElement gpuTypeV100 = driver.findElement(By.id("select_option_421"));
        gpuTypeV100.click();

        return this;
    }

    public GoogleCloudMainPO set2x375SSD()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_value_label_117"));
            }
        });

        WebElement localssd = driver.findElement(By.id("select_value_label_117"));
        localssd.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_132"));
            }
        });

        WebElement localssd2x375 = driver.findElement(By.id("select_option_132"));
        localssd2x375.click();

        return this;
    }

    public GoogleCloudMainPO setLADatacenterLocation()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("select_value_label_118"));
            }
        });

        WebElement datacenter = driver.findElement(By.id("select_value_label_118"));
        datacenter.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_287"));
            }
        });

        WebElement la = driver.findElement(By.id("select_option_287"));
        la.click();

        return this;
    }

    public GoogleCloudMainPO set1yearCommittedUsage()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("select_value_label_119"));
            }
        });

        WebElement committedusage = driver.findElement(By.id("select_value_label_119"));
        committedusage.click();

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.id("select_option_139"));
            }
        });

        WebElement committedusage1year = driver.findElement(By.id("select_option_139"));
        committedusage1year.click();

        return this;
    }

    public GoogleCloudMainPO pressAddToEstimate()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//button[contains(@aria-label, 'Add to Estimate')]"));
            }
        });

        WebElement addToEstimate = driver.findElement(By.xpath("//button[contains(@aria-label, 'Add to Estimate')]"));
        addToEstimate.click();

        return this;
    }

    public GoogleCloudMainPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

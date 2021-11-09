import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class GoogleCloudPO extends AbstractPO
{

    private Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
            .ignoring(NoSuchElementException.class);

    private final String HOME_PAGE = "https://cloud.google.com/";

    private WebElement searchInput;

    @Override
    public GoogleCloudPO openPage() {
        driver.get(HOME_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return this;
    }

    public GoogleCloudPO enterTermToSearchInputAndFind(String Term)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("/html/body/section/devsite-header/div/div[1]/div/div/div[2]/devsite-search/form/div[1]/div/input"));
            }
        });

        searchInput = driver.findElement(By.xpath("/html/body/section/devsite-header/div/div[1]/div/div/div[2]/devsite-search/form/div[1]/div/input"));

        searchInput.click();
        searchInput.sendKeys(Term);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public GoogleCloudPO openTermReferenceAtFound(String Term)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

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

    public GoogleCloudPO switchToFrame()
    {
        driver.get("https://cloudpricingcalculator.appspot.com");
        return this;
    }

    public GoogleCloudPO setVMType(String type)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("//*[contains(text(),'" + type + "')]"));
            }
        });
        WebElement computeEngine = driver.findElement(By.xpath("//*[contains(text(),'" + type + "')]"));
        computeEngine.click();
        return this;
    }

    public GoogleCloudPO setNumbersOfInstances(int numbers)
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

    public GoogleCloudPO setFreeOS()
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

    public GoogleCloudPO setRegularMachineClass()
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

    public GoogleCloudPO setE2S8MachineType()
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

    public GoogleCloudPO set1NOfNodes()
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

    public GoogleCloudPO set1TeslaV100GPU()
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

    public GoogleCloudPO set2x375SSD()
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

    public GoogleCloudPO setLADatacenterLocation()
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

    public GoogleCloudPO set1yearCommittedUsage()
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

    public GoogleCloudPO pressAddToEstimate()
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

    public GoogleCloudPO pressEmailEstimate()
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("email_quote"));
            }
        });

        WebElement emailButton = driver.findElement(By.id("email_quote"));
        emailButton.click();

        return this;
    }

    public GoogleCloudPO sendEstimateToEmail(String email)
    {
        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("input_461"));
            }
        });

        WebElement emailField = driver.findElement(By.id("input_461"));
        emailField.click();
        emailField.sendKeys(email);

        wait.until(new Function<WebDriver, Object>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//button[@aria-label = 'Send Email']"));
            }
        });

        WebElement sendButton = driver.findElement(By.xpath("//button[@aria-label = 'Send Email']"));
        WebElement cancelButton = driver.findElement(By.xpath("//button[@aria-label = 'Cancel']"));

        sendButton.click();
        cancelButton.click();


        return this;
    }

    public String getEstimatePrice()
    {
        WebElement estimatePrice = driver.findElement(By.xpath("/html/body/md-content/md-card/div/md-card-content[2]/md-card/md-card-content/div/div/div/h2/b"));

        return estimatePrice.getText().split("\\s+")[4];
    }

    public GoogleCloudPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

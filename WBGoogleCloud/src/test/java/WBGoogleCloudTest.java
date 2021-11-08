import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WBGoogleCloudTest {

    @Test
    public void TotalEstimatedCostEqualsEmailEstimatedCost()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver()
        WebDriver yopdriver = new ChromeDriver();

        GoogleCloudPO gcPO = new GoogleCloudPO(driver);
        gcPO.openPage()
                .enterTermToSearchInputAndFind("Google Cloud Platform Pricing Calculator")
                .openTermReferenceAtFound("Google Cloud Platform Pricing Calculator")
                .switchToFrame()
                .setVMType("Compute Engine")
                .setNumbersOfInstances(4)
                .setFreeOS()
                .setRegularMachineClass()
                .setE2S8MachineType()
                .set1NOfNodes()
                .set1TeslaV100GPU()
                .set2x375SSD()
                .setLADatacenterLocation()
                .set1yearCommittedUsage()
                .pressAddToEstimate();

        YOPMailPO yopmOP = new YOPMailPO(yopdriver);
        String email = yopmOP.openPage()
                .selectRandomEmailPage()
                .getRandomEmail();

        gcPO.pressEmailEstimate()
                .sendEstimateToEmail(email);

        String emailPrice = yopmOP.checkRandomEmail()
                .getEstimatedPrice();

        String estimatePrice = gcPO.getEstimatePrice();

        Assert.assertEquals(emailPrice, estimatePrice);
    }
}

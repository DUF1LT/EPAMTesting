import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@RunWith(JUnit4.class)
public class WBGoogleCloudTest {

    @Test
    public void TotalEstimatedCostEqualsEmailEstimatedCost()
    {
        WebDriver driver = new ChromeDriver();
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

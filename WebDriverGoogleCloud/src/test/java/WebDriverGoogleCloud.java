import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject_model.GoogleCloudMainPO;

public class WebDriverGoogleCloud {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        GoogleCloudMainPO gcPO = new GoogleCloudMainPO(driver);
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

        Thread.sleep(5000);
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Runner {
    public static void main(String[] args) throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        FinamPO finam = new FinamPO(driver);

        finam.openPage();
        finam.loginToFinam("7646", "5447456733");
        finam.buyCompanyNSales("GMKN", 2);
        finam.closeSubmitWindows();
        System.out.println(finam.isNewSalesExists("GMKN"));
    }
}

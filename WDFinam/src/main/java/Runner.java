import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Runner {
    public static void main(String[] args) throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        FinamLoginPO finamLoginPO = new FinamLoginPO(driver);

        System.out.println(finamLoginPO
                    .openPage()
                    .loginToFinam("7646", "5447456733")
                    .buyCompanyNSales("GMKN", 2)
                    .closeSubmitWindows());
    }
}

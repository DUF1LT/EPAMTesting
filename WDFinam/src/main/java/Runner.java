import model.Stock;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.FinamLoginPO;
import service.StockCreator;
import service.UserCreator;

public class Runner {
    public static void main(String[] args) throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        FinamLoginPO finamLoginPO = new FinamLoginPO(driver);
        User finamUser = UserCreator.withCredentialsFromProperty();
        Stock stock = StockCreator.withCredentialsFromProperty();

        System.out.println(finamLoginPO
                    .openPage()
                    .loginToFinam(finamUser)
                    .buyStock(stock)
                    .submitRequest());
    }
}

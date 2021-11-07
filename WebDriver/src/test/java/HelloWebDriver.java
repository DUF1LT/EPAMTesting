import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pageObject_model.PastebinMainPage;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();

        PastebinMainPage pastebinOP = new PastebinMainPage(driver);
        pastebinOP.openPage()
                .inputCodeToTextArea("Hello from WebDriver")
                .selectPasteExpiration("10 Minutes");

        Thread.sleep(5000);
    }
}

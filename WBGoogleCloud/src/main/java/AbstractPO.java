import org.openqa.selenium.WebDriver;

public abstract class AbstractPO {
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected WebDriver driver;
    protected abstract AbstractPO openPage();

    protected AbstractPO(WebDriver driver)
    {
        this.driver = driver;
    }
}

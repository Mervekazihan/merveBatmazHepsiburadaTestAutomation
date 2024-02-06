import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BasePageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void before() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void down() {
        driver.quit();
    }

    public <T extends BasePage> T goToUrl(T page, String url){
        driver.get(url);
        return page;
    }
}

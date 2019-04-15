import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseRunner {

    WebDriver driver;
    private String browserName = System.getProperty("browser");
    String baseUrl;
    WebDriverWait wait;

    @Before
    public void setUp() {
        try {
            driver = BrowsersFactory.valueOf(browserName).create();
        } catch (NullPointerException | IllegalArgumentException e) {
            driver = BrowsersFactory.valueOf("chrome").create();
        }
        driver.manage().window().maximize();
        baseUrl = "https://www.tinkoff.ru/career/vacancies/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, 5);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
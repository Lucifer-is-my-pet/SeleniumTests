import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {

    WebDriver driver;
    private String browserName = System.getProperty("browser");
    String baseUrl;

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
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
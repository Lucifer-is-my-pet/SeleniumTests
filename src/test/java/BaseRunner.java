import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseRunner {

    WebDriver driver;
    private String browserName = System.getProperty("browser");
    String baseUrl;
    WebDriverWait wait;

    @BeforeEach
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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
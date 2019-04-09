package test.java;

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
        driver = BrowsersFactory.valueOf(browserName).create();
        driver.manage().window().maximize();
        baseUrl = "https://www.tinkoff.ru/career/vacancies/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

/*    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return BrowsersFactory.valueOf(browserName).create();
    }*/
}
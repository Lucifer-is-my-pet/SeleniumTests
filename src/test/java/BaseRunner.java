import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class BaseRunner {
    WebDriver driver;
    private String browserName = System.getProperty("browser");
    String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

//    @Before
//    public void setUp() {
//        driver = getDriver();
//        driver.manage().window().maximize();
//        baseUrl = "https://moscow-job.tinkoff.ru/";
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    }

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        baseUrl = "https://www.tinkoff.ru/career/vacancies/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }

    @After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            browserName = randomBrowserPicker(); // todo
            System.setProperty("browser", browserName);
        }
        return BrowsersFactory.valueOf(browserName).create();
    }

    private String randomBrowserPicker() {
        System.out.println("\nThe driver is not set. Running a random browser...");
        int pick = new Random().nextInt(BrowsersFactory.values().length);
        return BrowsersFactory.values()[pick].toString();
    }
}
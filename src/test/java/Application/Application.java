package Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Pages.*;

import java.util.concurrent.TimeUnit;

public class Application {

    public Logger logger;
    protected WebDriverWait wait;
    protected WebDriver driver;
    public String browserName = "chrome";

    public Application() {
        driver = BrowsersFactory.valueOf(browserName).create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);
        logger = LoggerFactory.getLogger(Application.class);

        logger.info("Браузер запущен");
    }

    protected Application(Application app) {
        app.clone(this);
    }

    private void clone(Application app) {
        app.browserName = browserName;
        app.driver = driver;
        app.wait = wait;
        app.logger = logger;
    }

    public void tearDown() {
        driver.quit();
        logger.info("Браузер закрыт");
    }

    public boolean checkUrl(String url) {
        return driver.getCurrentUrl().equals(url);
    }

}

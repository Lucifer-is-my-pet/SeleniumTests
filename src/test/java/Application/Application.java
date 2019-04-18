package Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Pages.*;
import java.BrowsersFactory;

import java.util.concurrent.TimeUnit;

public class Application {

    public final String browserName = "chrome";

    public Logger logger;
    private WebDriverWait wait;
    private WebDriver driver;
    public GoogleMainPage google;
    public GoogleResultsPage googleResults;
    public VacanciesPage vacancies;
    public TariffsPage tariffs;

    public Application() {
        driver = BrowsersFactory.valueOf(browserName).create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);
        logger = LoggerFactory.getLogger(Application.class);

        google = new GoogleMainPage(driver);
        googleResults = new GoogleResultsPage(driver);
        vacancies = new VacanciesPage(driver);
        tariffs = new TariffsPage(driver);
    }

    private WebDriver getDriver() {
        return BrowsersFactory.valueOf(browserName).create();
    }

    public void tearDown() {
        driver.quit();
        logger.info("Закрыли браузер");
    }

}

package Pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        logger = LoggerFactory.getLogger(BasePage.class);
    }

    public void goTo(String url) {
        this.driver.navigate().to(url);
        logger.info("Открыта страница " + url);
    }

    public void switchToTab(String tabName) {
        this.driver.switchTo().window(tabName);
        this.wait.until(ExpectedConditions.titleContains(tabName));
        logger.info("Переключились на вкладку \"" + tabName + "\"");
    }

    public void closeTab(String tabName) {
        if (!tabName.equals(this.driver.getTitle())) {
            switchToTab(tabName);
        }
        this.driver.close();
        logger.info("Закрыта вкладка \"" + tabName + "\"");
    }
}

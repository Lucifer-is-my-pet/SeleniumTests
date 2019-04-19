package Pages;

import Application.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class BasePage extends Application {

    public BasePage(Application app) {
        super(app);
    }

    public BasePage() {
        super();
    }

    public void goTo(String url) {
        driver.navigate().to(url);
        logger.info("Открыта страница " + url);
    }

    public void openNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
        logger.info("Открыта страница " + url);
    }

    public void switchToNextTab(String tabName) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.titleContains(tabName));
        logger.info("Переключились на вкладку \"" + tabName + "\"");
    }

    public void closeCurrentTab() {
        String title = this.driver.getTitle();
        this.driver.close();
        logger.info("Закрыта вкладка \"" + title + "\"");
    }

    public boolean checkUrl(String url) {
        logger.info("Проверяем, верный ли адрес страницы");
        return driver.getCurrentUrl().equals(url);
    }

    public boolean checkTitle(String title) {
        return driver.getTitle().equals(title);
    }

    public void click(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

}

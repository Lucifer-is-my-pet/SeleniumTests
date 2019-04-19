package Pages;

import Application.Application;
import Elements.Checkbox;
import Elements.Select;
import org.apache.maven.shared.utils.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TariffsPage extends BasePage {

    private final String URL = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    private final String PAGE_NAME = "Тарифы Тинькофф Мобайла";

    private String regionXPath = "//div[@class='MvnoRegionConfirmation__title_DOqnW']";
    private String amountXPath = "//h3[@data-qa-file='UITitle']";

    public TariffsPage(Application app) {
        super(app);
    }

    public TariffsPage() {
        super();
    }

        public void open() {
        goTo(URL);
    }

    public void openInNewTab() {
        openNewTab(URL);
        switchToTab(PAGE_NAME);
    }

    public boolean isPageTitleCorrect() {
        return checkTitle(PAGE_NAME);
    }

    public boolean isUrlCorrect() {
        return checkUrl(URL);
    }

    public void changeRegion(String regionToChange) {
        try {
            driver.findElement(By.xpath("//span[@data-qa-file='MvnoRegionConfirmation'][contains(text(), 'Ваш регион —')]")).isDisplayed();
            driver.findElement(By.xpath("//span[contains(@class, 'MvnoRegionConfirmation__optionRejection_1NrnL')]")).click();
        }
        catch (NoSuchElementException nee) {
            driver.findElement(By.xpath(regionXPath)).click();
        }
        driver.findElement(By.xpath("//div[text()='" + regionToChange + "']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@name='headerSlim']")));
        logger.info("Установили регион " + regionToChange);
    }

    public boolean checkRegion(String regionToCheck) {
        return driver.findElement(By.xpath(regionXPath)).getText().equals(regionToCheck);
    }

    public String getAmount() {
        logger.info("Считаем общую цену тарифа");
        return StringUtils.join(driver.findElement(By.xpath(amountXPath)).getText().split("\\D+"), "").trim();
    }

    public void setMaxSettings() {
        Checkbox unlimitedSms = new Checkbox("//input[@id=2048]"),
                modemMode = new Checkbox("//input[@id=2058]");
        Select internet = new Select("//select[@name='internet']/parent::div"),
                calls = new Select("//select[@name='calls']/parent::div");

        unlimitedSms.check(driver);
        internet.selectOption(driver, "Безлимитный интернет");
        modemMode.check(driver);
        calls.selectOption(driver, "Безлимитные минуты");

        logger.info("Установили максимальные настройки тарифов");
    }

}

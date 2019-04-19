import Application.BaseRunner;
import Elements.Button;
import Elements.Checkbox;
import Elements.Select;
import Elements.TextInput;
import Pages.*;
import com.sun.org.glassfish.gmbal.Description;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.junit.gen5.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Тарифы Тинькофф Мобайла")
public class TariffsTest extends BaseRunner {

    @Test
    @DisplayName("Переключение вкладок")
    public void checkTabsSwitching() {
//        VacanciesPage vp = new VacanciesPage(app);
//        vp.open();

        GoogleMainPage google = new GoogleMainPage(app);
        google.openInNewTab();
        google.sendQuery("тинькофф мобайл");
        google.selectFromSuggestions("тарифы");

        GoogleResultsPage results = new GoogleResultsPage(app);
        results.click("https://www.tinkoff.ru/mobile-operator/tariffs/");

        TariffsPage tariffs = new TariffsPage(app);
        tariffs.checkTitle("Тарифы Тинькофф Мобайла");
        results.closeCurrentTab();
        tariffs.switchToTab("Тарифы Тинькофф Мобайла");

        assertTrue(tariffs.isUrlCorrect());
    }

    @Test
    @DisplayName("Смена региона")
    @Description("Проверить, что суммы общей цены тарифа с выбранными пакетами и сервисами по дефолту для регионов Москва и Краснодар разные;" +
            "Проверить, что суммы общей цены тарифа с максимальными выбранными пакетами и сервисами для регионов Москва и Краснодар одинаковые")
    public void checkRegionChange() {
        TariffsPage tariffs = new TariffsPage(app);
        tariffs.open();
        tariffs.changeRegion("Москва и Московская обл.");
        tariffs.checkRegion("Москва и Московская область");

        String moscowAmount = tariffs.getAmount();

        tariffs.changeRegion("Краснодарский кр.");

        String krasnodarAmount = tariffs.getAmount();
        assertNotEquals(moscowAmount, krasnodarAmount);

        tariffs.setMaxSettings();

        String krasnodarAmountMax = tariffs.getAmount();

        tariffs.changeRegion("Москва и Московская обл.");
        tariffs.setMaxSettings();
        String moscowAmountMax = tariffs.getAmount();

        assertEquals(moscowAmountMax, krasnodarAmountMax);
    }

 @Test
    @DisplayName("Активная кнопка")
    @Description("Проверить, что кнопка «Заказать SIM-карту» активна после отключения всех пакетов и сервисов")
    public void checkActiveButton() {

/*           ((JavascriptExecutor) driver).executeScript("window.open('https://www.tinkoff.ru/mobile-operator/tariffs/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        selectMoscow();

        Checkbox messengers = new Checkbox("//input[@id=2050]"),
                socialMedia = new Checkbox("//input[@id=2053]");

        Select internet = new Select("//select[@name='internet']/parent::div"),
                calls = new Select("//select[@name='calls']/parent::div");

        messengers.uncheck(driver);
        socialMedia.uncheck(driver);
        internet.selectOption(driver, "0 ГБ");
        calls.selectOption(driver, "0 минут");

        String amount = getAmount("//h3[@data-qa-file='UITitle']");
        assertEquals("0", amount);

        assertTrue(new Button("//div[text()='Заказать сим-карту']/ancestor::button").isActive(driver));
    }

    private void selectMoscow() throws InterruptedException {
        if (driver.findElement(By.xpath("//span[@data-qa-file='MvnoRegionConfirmation'][contains(text(), 'Ваш регион —')]")).isDisplayed()) {
            driver.findElement(By.xpath("//span[contains(@class, 'MvnoRegionConfirmation__optionRejection_1NrnL')]")).click();
        } else {
            driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        }

        driver.findElement(By.xpath("//div[text()='Москва и Московская обл.']")).click();
        assertEquals("Москва и Московская область", driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).getText());*/
    }

}

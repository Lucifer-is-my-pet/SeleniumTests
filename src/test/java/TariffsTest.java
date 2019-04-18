import Elements.Button;
import Elements.Checkbox;
import Elements.Select;
import Elements.TextInput;
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
        driver.get(baseUrl);

        ((JavascriptExecutor) driver).executeScript("window.open('https://www.google.ru/','_blank');"); // обычный способ открытия новой вкладки не сработал
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        TextInput query = new TextInput("//input[@name='q']");
        query.fill(driver, "тинькофф мобайл");
        Select suggestions = new Select("//input[@name='q']");
        suggestions.selectOption(driver, "тарифы");
        driver.findElement(By.xpath("//cite[text()='https://www.tinkoff.ru/mobile-operator/tariffs/']")).click();

        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        assertEquals("Тарифы Тинькофф Мобайла", driver.getTitle());

        driver.switchTo().window(tabs.get(1));
        driver.close();

        driver.switchTo().window(tabs.get(2));
        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/", driver.getCurrentUrl());
        driver.close();
    }

    @Test
    @DisplayName("Смена региона")
    @Description("Проверить, что суммы общей цены тарифа с выбранными пакетами и сервисами по дефолту для регионов Москва и Краснодар разные;" +
            "Проверить, что суммы общей цены тарифа с максимальными выбранными пакетами и сервисами для регионов Москва и Краснодар одинаковые")
    public void checkRegionChange() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.tinkoff.ru/mobile-operator/tariffs/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        selectMoscow();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")),
                "Москва и Московская область"));

        String moscowAmount = getAmount("//h3[@data-qa-file='UITitle']");

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        driver.findElement(By.xpath("//div[text()='Краснодарский кр.']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@name='headerSlim']")));

        String krasnodarAmount = getAmount("//h3[@data-qa-file='UITitle']");
        assertNotEquals(moscowAmount, krasnodarAmount);

        Checkbox unlimitedSms = new Checkbox("//input[@id=2048]"),
                modemMode = new Checkbox("//input[@id=2058]");
        Select internet = new Select("//select[@name='internet']/parent::div"),
                calls = new Select("//select[@name='calls']/parent::div");

        unlimitedSms.check(driver);
        internet.selectOption(driver, "Безлимитный интернет");
        modemMode.check(driver);
        calls.selectOption(driver, "Безлимитные минуты");

        String krasnodarAmountMax = getAmount("//h3[@data-qa-file='UITitle']");

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        driver.findElement(By.xpath("//div[text()='Москва и Московская обл.']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@name='headerSlim']")));

        unlimitedSms.check(driver);
        internet.selectOption(driver, "Безлимитный интернет");
        modemMode.check(driver);
        calls.selectOption(driver, "Безлимитные минуты");

        String moscowAmountMax = getAmount("//h3[@data-qa-file='UITitle']");
        assertEquals(moscowAmountMax, krasnodarAmountMax);

        driver.close();
    }

    @Test
    @DisplayName("Активная кнопка")
    @Description("Проверить, что кнопка «Заказать SIM-карту» активна после отключения всех пакетов и сервисов")
    public void checkActiveButton() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.tinkoff.ru/mobile-operator/tariffs/','_blank');");
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
        assertEquals("Москва и Московская область", driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).getText());
    }

    private String getAmount(String locator) {
        return StringUtils.join(driver.findElement(By.xpath(locator)).getText().split("\\D+"), "").trim();
    }

}

import Elements.Button;
import Elements.Checkbox;
import Elements.Select;
import com.sun.org.glassfish.gmbal.Description;
import org.apache.maven.shared.utils.StringUtils;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class Vacancies extends BaseRunner {

    @Test
    public void checkEmptyFields() {
        driver.get(baseUrl);

        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("socialLink0")).click();
        driver.findElement(By.xpath("//div[@class='schema__addSocialLink_Yyu6i']")).click(); // Добавить ещё ссылку
        driver.findElement(By.xpath("//div[@class='ui-checkbox__check']")).click(); // Я согласен с условиями передачи информации

        driver.findElement(By.xpath("//button[@aria-label='Отправить']")).click();
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')][1]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[2]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[3]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[4]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[5]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[6]")).getText());
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[7]")).getText());
    }

    @Test
    public void checkInvalidInputs() {
        driver.get(baseUrl);

        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("!!!");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("birthday")).clear();
        driver.findElement(By.name("birthday")).sendKeys("00.00.0000");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("111");
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("(333)");
        driver.findElement(By.name("socialLink0")).click();
        driver.findElement(By.name("socialLink0")).clear();
        driver.findElement(By.name("socialLink0")).sendKeys("text");

        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[4]")).getText());
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис",
                driver.findElement(By.xpath("//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')][1]")).getText());
        assertEquals("Поле заполнено некорректно",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[2]")).getText());
        assertEquals("Введите корректный адрес эл. почты",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[3]")).getText());

        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("phone")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("phone")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("phone")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("phone")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("phone")).sendKeys("(000) 000-00-00");

        driver.findElement(By.xpath("//button[@aria-label='Отправить']")).click();

        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                driver.findElement(By.xpath("(//div[contains(@class, 'ui-form-field-error-message') and contains(@class, 'ui-form-field-error-message_ui-form')])[5]")).getText());
    }

    @Test
    @DisplayName("Переключение вкладок")
    public void checkTabsSwitching() {
        driver.get(baseUrl);

        ((JavascriptExecutor) driver).executeScript("window.open('https://www.google.ru/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.findElement(By.name("q")).sendKeys("тинькофф мобайл");
        driver.findElement(By.xpath("//*[contains(text(), 'тарифы')]/parent::span")).click();
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

        Button orderSim = new Button("//div[text()='Заказать сим-карту']/ancestor::button");

        assertTrue(orderSim.isActive(driver));
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
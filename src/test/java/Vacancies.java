import org.apache.maven.shared.utils.StringUtils;
import org.junit.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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
    @DisplayName("Переключание вкладок")
    public void checkTabsSwitching() {
        driver.get(baseUrl);
//        driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL, "t")); // не работает
//        driver.get("https://www.google.ru/");
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.google.ru/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф n");
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
    public void checkRegionChange() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.tinkoff.ru/mobile-operator/tariffs/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Москва и Московская обл.')]")).click();
        assertEquals("Москва и Московская обл.", driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).getText());
        sleep(3000); // страница перезагружается
        assertEquals("Москва и Московская обл.", driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).getText());

        String moscowAmount = StringUtils.join(driver.findElement(By.xpath("//h3[@data-qa-file='UITitle']")).getText().split("\\D+"), "").trim();

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Краснодарский кр.')]")).click();
        sleep(3000); // страница перезагружается

        String krasnodarAmount = StringUtils.join(driver.findElement(By.xpath("//h3[@data-qa-file='UITitle']")).getText().split("\\D+"), "").trim();
        assertNotEquals(moscowAmount, krasnodarAmount);

        driver.findElement(By.xpath("//label[contains(text(), 'Безлимитные СМС (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')][1]")).click(); // список
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитный интернет')]")).click();
        driver.findElement(By.xpath("//label[contains(text(), 'Режим модема (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("(//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')])[2]")).click(); // список
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные минуты')]")).click();

        String krasnodarAmountMax = StringUtils.join(driver.findElement(By.xpath("//h3[@data-qa-file='UITitle']")).getText().split("\\D+"), "").trim();

        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_DOqnW']")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Москва и Московская обл.')]")).click();
        sleep(3000); // страница перезагружается

        driver.findElement(By.xpath("//label[contains(text(), 'Безлимитные СМС (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')][1]")).click(); // Интернет
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитный интернет')]")).click();
        driver.findElement(By.xpath("//label[contains(text(), 'Режим модема (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("(//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')])[2]")).click(); // Звонки
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные минуты')]")).click();

        String moscowAmountMax = StringUtils.join(driver.findElement(By.xpath("//h3[@data-qa-file='UITitle']")).getText().split("\\D+"), "").trim();
        assertEquals(moscowAmountMax, krasnodarAmountMax);

        driver.close();
    }

    @Test
    @DisplayName("Активная кнопка")
    public void activeButton() {
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.tinkoff.ru/mobile-operator/tariffs/','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.findElement(By.xpath("//label[contains(text(), 'Мессенджеры (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("//label[contains(text(), 'Социальные сети (')]/preceding-sibling::div")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')][1]")).click(); // Интернет
        driver.findElement(By.xpath("//span[contains(text(), '0 ГБ')]")).click();
        driver.findElement(By.xpath("(//div[contains(@class, 'ui-select__title') and contains(@class, 'ui-select__title_columned')])[2]")).click(); // Звонки
        driver.findElement(By.xpath("//span[contains(text(), '0 минут')]")).click();

        String amount = StringUtils.join(driver.findElement(By.xpath("//h3[@data-qa-file='UITitle']")).getText().split("\\D+"), "").trim();
        assertEquals("0", amount);

        assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Заказать сим-карту')]/ancestor::button")).isEnabled());
    }
}
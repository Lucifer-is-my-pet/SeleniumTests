import Elements.Button;
import Elements.Checkbox;
import Elements.Select;
import Elements.TextInput;
import com.sun.org.glassfish.gmbal.Description;
import org.apache.maven.shared.utils.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

@DisplayName("Вакансии в Тинькофф")
public class VacanciesTest extends BaseRunner {

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

}
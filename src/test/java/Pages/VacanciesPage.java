package Pages;

import Application.Application;
import Elements.Button;
import Elements.Checkbox;
import Elements.TextInput;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class VacanciesPage extends BasePage {

    private final String URL = "https://www.tinkoff.ru/career/vacancies/";

    public VacanciesPage(Application app) {
        super(app);
    }

    public VacanciesPage() {
        super();
    }

    public void open() {
        goTo(URL);
    }

    public void clickAllClickables() {
        TextInput name = new TextInput("//input[@name='name']"),
                birthday = new TextInput("//input[@name='birthday']"),
                city = new TextInput("//input[@name='city']"),
                email = new TextInput("//input[@name='email']"),
                phone = new TextInput("//input[@name='phone']");
        Button addAnotherLink = new Button("//div[@class='schema__addSocialLink_Yyu6i']"),
                orderSim = new Button("//button[@aria-label='Отправить']"); // Добавить ещё ссылку
        Checkbox conditions = new Checkbox("//div[@class='ui-checkbox__check']"); // Я согласен с условиями передачи информации

        name.click(driver);
        birthday.click(driver);
        city.click(driver);
        email.click(driver);
        phone.click(driver);
        addAnotherLink.click(driver);
        conditions.click(driver);

        logger.info("Прокликали все поля формы");

        orderSim.click(driver);
    }

    public boolean checkErrorNearField(String fieldName, String errorText) {
        logger.info("Проверяем текст ошибки под полем \"" + fieldName + "\"");
        return driver.findElement(By.xpath("//*[text()='" + fieldName +
                "']/ancestor::div/following-sibling::div[@data-qa-file='UIFormRowError']")).getText().equals(errorText);
    }

    public void typeText(String fieldName, String text) {
        TextInput input = new TextInput(fieldName, true);
        input.type(driver, text);
        logger.info("Ввели в поле \"" + fieldName + "\" текст \"" + text + "\"");
    }

    public void clear(String fieldName, String result) {
        TextInput input = new TextInput(fieldName, true);
        do {
            input.type(driver, Keys.BACK_SPACE);
        } while (!input.getText(driver).equals(result));

        logger.info("Очистили поле \"" + fieldName + "\"");
    }
}

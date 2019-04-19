import Application.BaseRunner;
import Pages.VacanciesPage;

import static org.junit.gen5.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Вакансии в Тинькофф")
public class VacanciesTest extends BaseRunner {

    @Test
    @DisplayName("Проверка ошибок под пустыми полями")
    public void checkEmptyFields() {
        VacanciesPage vacancies = new VacanciesPage(app);
        vacancies.open();

        vacancies.clickAllClickables();

        assertTrue(vacancies.checkErrorNearField("Фамилия и имя", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("Дата рождения", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("Город проживания", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("Электронная почта", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("Мобильный телефон", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("загрузите резюме/портфолио", "Поле обязательное"));
        assertTrue(vacancies.checkErrorNearField("условиями передачи информации", "Поле обязательное"));
    }

    @Test
    @DisplayName("Проверка невалидных данных в полях")
    public void checkInvalidInputs() {
        VacanciesPage vacancies = new VacanciesPage(app);
        vacancies.open();

        vacancies.typeText("Фамилия и имя", "!!!");
        vacancies.typeText("Дата рождения", "00.00.0000");
        vacancies.typeText("Электронная почта", "111");
        vacancies.typeText("Мобильный телефон", "(333)");
        vacancies.typeText("Ссылка на ваш профиль в соцсетях или портфолио", "text");

        assertTrue(vacancies.checkErrorNearField("Мобильный телефон", "Номер телефона должен состоять из 10 цифр, начиная с кода оператора"));
        assertTrue(vacancies.checkErrorNearField("Фамилия и имя", "Допустимо использовать только буквы русского алфавита и дефис"));
        assertTrue(vacancies.checkErrorNearField("Дата рождения", "Поле заполнено некорректно"));
        assertTrue(vacancies.checkErrorNearField("Электронная почта", "Введите корректный адрес эл. почты"));

        vacancies.clear("Мобильный телефон", "+7(");
        vacancies.typeText("Мобильный телефон", "(000) 000-00-00");
        vacancies.typeText("Ссылка на ваш профиль в соцсетях или портфолио", "text");

        assertTrue(vacancies.checkErrorNearField("Мобильный телефон", "Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9"));
    }

}
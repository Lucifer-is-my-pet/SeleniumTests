package Pages;

import Application.Application;
import Elements.Select;
import Elements.TextInput;

public class GoogleMainPage extends BasePage {

    private final String URL = "https://www.google.ru/";

    public GoogleMainPage(Application app) {
        super(app);
    }

    public GoogleMainPage() {
        super();
    }

    public void open() {
        goTo(URL);
    }

    public void openInNewTab() {
        openNewTab(URL);
        switchToNextTab("Google");
    }

    public void sendQuery(String text) {
        TextInput query = new TextInput("//input[@name='q']");
        query.type(super.driver, text);
        logger.info("Ищем в Гугле '" + text + "'");
    }

    public void selectFromSuggestions(String variant) {
        Select suggestions = new Select("//input[@name='q']");
        suggestions.selectOption(this.driver, variant);
        logger.info("Выбрали из предложенных вариантов '" + variant + "'");
    }

}

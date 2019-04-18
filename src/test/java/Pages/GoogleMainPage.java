package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage extends BasePage {

    private final String URL = "https://www.google.ru/";

    public GoogleMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

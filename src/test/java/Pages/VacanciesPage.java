package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VacanciesPage extends BasePage {

    private final String URL = "https://www.tinkoff.ru/mobile-operator/tariffs/";

    public VacanciesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    WebElement name;

    @FindBy(xpath = "//input[@name='birthday']")
    WebElement birthday;

    @FindBy(xpath = "//input[@name='city']")
    WebElement city;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement phone;
}

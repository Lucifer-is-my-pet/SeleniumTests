package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultsPage extends BasePage {

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

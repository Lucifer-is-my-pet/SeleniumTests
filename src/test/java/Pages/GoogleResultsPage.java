package Pages;

import Application.Application;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultsPage extends BasePage {

    public GoogleResultsPage(Application app) {
        super(app);
    }

    public GoogleResultsPage() {
        super();
    }

    @Override
    public void click(String url) {
        super.click("//cite[text()='" + url + "']");
    }
}

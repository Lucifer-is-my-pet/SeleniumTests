package Pages;

import Application.Application;

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

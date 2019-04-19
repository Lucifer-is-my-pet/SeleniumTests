package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends BaseElement {

    public Button(String locator) {
        super(locator);
    }

    public boolean isActive(WebDriver driver) {
        return driver.findElement(By.xpath(locator)).isEnabled();
    }

}

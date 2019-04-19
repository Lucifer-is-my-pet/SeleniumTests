package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseElement {

    String locator;

    BaseElement(String locator) {
        this.locator = locator;
    }

    public void click(WebDriver driver) {
        driver.findElement(By.xpath(this.locator)).click();
    }
}

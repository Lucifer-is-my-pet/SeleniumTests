package Elements;

import okio.Buffer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button {

    private String locator;

    public Button(String locator) {
        this.locator = locator;
    }

    public boolean isActive(WebDriver driver) {
        return driver.findElement(By.xpath(this.locator)).isEnabled();
    }

    public void click(WebDriver driver) {
        driver.findElement(By.xpath(this.locator)).click();
    }
}

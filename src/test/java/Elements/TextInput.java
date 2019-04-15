package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextInput {

    private String locator;

    public TextInput(String locator) {
        this.locator = locator;
    }

    public void fill(WebDriver driver, String text) {
        driver.findElement(By.xpath(this.locator)).sendKeys(text);
    }

    public String getText(WebDriver driver) {
        return driver.findElement(By.xpath(this.locator)).getAttribute("value");

    }
}

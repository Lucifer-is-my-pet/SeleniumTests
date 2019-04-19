package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextInput extends BaseElement {

    private final static String ANCESTOR_SIBLING_INPUT = "/ancestor::span/preceding-sibling::input";

    public TextInput(String locator) {
        super(locator);
    }

    public TextInput(String locator, boolean fieldNameFlag) {
        super(locatorForAll(locator, fieldNameFlag));
    }

    public void type(WebDriver driver, CharSequence text) {
        driver.findElement(By.xpath(locator)).sendKeys(text);
    }

    public String getText(WebDriver driver) {
        return driver.findElement(By.xpath(locator)).getAttribute("value");
    }

    private static String locatorForAll(String locator, boolean fieldNameFlag) {
        if (fieldNameFlag) {
            return "//*[text()='" + locator + "']" + ANCESTOR_SIBLING_INPUT;
        } else {
            return locator;
        }
    }
}

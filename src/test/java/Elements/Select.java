package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Select extends BaseElement {

    public Select(String locator) {
        super(locator);
    }

    public void selectOption(WebDriver driver, String option) {
        driver.findElement(By.xpath(locator)).click();
        driver.findElement(By.xpath("(" + locator + "/ancestor::div//*[normalize-space(text()) = '" + option + "'])[last()]")).click();
    }

    public String selectedOption(WebDriver driver) {
        return driver.findElement(By.xpath(locator)).findElement(By.xpath("//span[@class='ui-select__title-flex-subtext']")).getText();
    }
}

package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Select {

    String locator;

    public Select(String locator) {
        this.locator = locator;
    }

    public void selectOption(WebDriver driver, String option) {
        driver.findElement(By.xpath(this.locator)).click(); //
        driver.findElement(By.xpath(this.locator + "/following-sibling::div//span[text()='" + option + "']")).click();
    }

    public String selectedOption(WebDriver driver) {
        return driver.findElement(By.xpath(this.locator)).findElement(By.xpath("//span[@class='ui-select__title-flex-subtext']")).getText();
    }
}

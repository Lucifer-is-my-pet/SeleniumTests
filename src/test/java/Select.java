import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Select extends BaseRunner {

    String locator;
    WebElement select; // //div[@data-qa-file='UIDropdownSelect']

    Select(String locator) {
        this.locator = locator;
    }

    public void selectOption(WebDriver driver, String option) {
        driver.findElement(By.xpath(this.locator)).click();
        driver.findElement(By.xpath(this.locator)).findElement(By.xpath("//span[contains(text(), '" + option + "')]")).click();
//        this.select.click();
//        this.select.findElement(By.xpath("//span[contains(text(), '" + option + "')]")).click();
    }

    public String selectedOption(WebDriver driver) {
//        return select.findElement(By.xpath("//span[@class='ui-select__title-flex-subtext']")).getText();
        return driver.findElement(By.xpath(this.locator)).findElement(By.xpath("//span[@class='ui-select__title-flex-subtext']")).getText();
    }
}

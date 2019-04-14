import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox {

    private String locator;
    private final String ancestorDiv = "/ancestor::div[@data-qa-type='uikit/checkbox']";

    Checkbox(String locator) {
        this.locator = locator;
    }

    void check(WebDriver driver) {
        if (!isChecked(driver)) {
            driver.findElement(By.xpath(this.locator + this.ancestorDiv)).click();
        }
    }

    void uncheck(WebDriver driver) {
        if (isChecked(driver)) {
            driver.findElement(By.xpath(this.locator + this.ancestorDiv)).click();
        }
    }

    public String getLabel(WebDriver driver) {
        return driver.findElement(By.xpath(this.locator + this.ancestorDiv)).findElement(By.xpath("/following-sibling::label")).getText();
    }

    public boolean isChecked(WebDriver driver) {
        return driver.findElement(By.xpath(this.locator)).isSelected();

    }

}

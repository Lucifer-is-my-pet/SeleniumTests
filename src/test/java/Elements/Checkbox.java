package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox extends BaseElement {

    private final String ANCESTOR_DIV = "/ancestor::div[@data-qa-type='uikit/checkbox']";

    public Checkbox(String locator) {
        super(locator);
    }

    public void check(WebDriver driver) {
        if (!isChecked(driver)) {
            driver.findElement(By.xpath(locator + this.ANCESTOR_DIV)).click();
        }
    }

    public void uncheck(WebDriver driver) {
        if (isChecked(driver)) {
            driver.findElement(By.xpath(locator + this.ANCESTOR_DIV)).click();
        }
    }

    private boolean isChecked(WebDriver driver) {
        return driver.findElement(By.xpath(locator)).isSelected();

    }

    public String getLabel(WebDriver driver) {
        return driver.findElement(By.xpath(locator + this.ANCESTOR_DIV + "/following-sibling::label")).getText();
    }

}

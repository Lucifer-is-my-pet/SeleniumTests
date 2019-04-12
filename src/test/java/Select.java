import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Select extends BaseRunner {

    WebElement select; // //div[@data-qa-file='UIDropdownSelect']

    Select(WebElement element) {
        this.select = element;
    }

    public void selectOption(String option) {
        this.select.click();
        this.select.findElement(By.xpath("//span[contains(text(), '" + option + "')]")).click();
    }

    public String selectedOption() {
        return select.findElement(By.xpath("//span[@class='ui-select__title-flex-subtext']")).getText();
    }
}

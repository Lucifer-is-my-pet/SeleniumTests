package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TariffsPage extends BasePage {

    private final String URL = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    private final String PAGE_NAME = "Тарифы Тинькофф Мобайла";

    public TariffsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    WebElement name;

    @FindBy(xpath = "//input[@name='birthday']")
    WebElement birthday;

    @FindBy(xpath = "//input[@name='city']")
    WebElement city;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement phone;

    @FindBy(xpath = "//span[@data-qa-file='MvnoRegionConfirmation'][contains(text(), 'Ваш регион —')]")
    WebElement regionQuestion;

    @FindBy(xpath = "//span[contains(@class, 'MvnoRegionConfirmation__optionRejection_1NrnL')]")
    WebElement regionQuestionReject;

    @FindBy(xpath = "//div[@class='MvnoRegionConfirmation__title_DOqnW']")
    WebElement region;

    public void open() {
        goTo("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    public void openInNewTab() {
        openNewTab("https://www.tinkoff.ru/mobile-operator/tariffs/");
        switchToTab("");
    }

    public boolean isPageTitleCorrect() {
        return checkTitle(PAGE_NAME);
    }

    public boolean checkUrl() {
        return driver.getCurrentUrl().equals("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    public void changeRegion(String regionToChange) {
        if (regionQuestion.isDisplayed()) {
            regionQuestionReject.click();
        } else {
            this.region.click();
        }
        driver.findElement(By.xpath("//div[text()='" + regionToChange + "']")).click();
        logger.info("Установили регион " + regionToChange);
    }

    public boolean checkRegion(String regionTocheck) {
        return region.getText().equals(regionTocheck);
    }

}

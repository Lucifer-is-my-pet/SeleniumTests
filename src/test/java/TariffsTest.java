import Application.BaseRunner;
import Pages.*;
import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.gen5.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Тарифы Тинькофф Мобайла")
public class TariffsTest extends BaseRunner {

    @Test
    @DisplayName("Переключение вкладок")
    public void checkTabsSwitching() {
        VacanciesPage vp = new VacanciesPage(app); // не уверена, что это всё ещё нужно, оставляю, раз было в предыдущей версии
        vp.open();

        GoogleMainPage google = new GoogleMainPage(app);
        google.openInNewTab();
        google.sendQuery("тинькофф мобайл");
        google.selectFromSuggestions("тарифы");

        GoogleResultsPage results = new GoogleResultsPage(app);
        results.click("https://www.tinkoff.ru/mobile-operator/tariffs/");

        TariffsPage tariffs = new TariffsPage(app);
        assertTrue(tariffs.checkTitle("Тарифы Тинькофф Мобайла"));
        results.closeCurrentTab();
        tariffs.switchToNextTab("Тарифы Тинькофф Мобайла");

        assertTrue(tariffs.isUrlCorrect());
    }

    @Test
    @DisplayName("Смена региона")
    @Description("Проверить, что суммы общей цены тарифа с выбранными пакетами и сервисами по дефолту для регионов Москва и Краснодар разные;" +
            "Проверить, что суммы общей цены тарифа с максимальными выбранными пакетами и сервисами для регионов Москва и Краснодар одинаковые")
    public void checkRegionChange() {
        TariffsPage tariffs = new TariffsPage(app);
        tariffs.open();
        tariffs.changeRegion("Москва и Московская обл.");
        tariffs.checkRegion("Москва и Московская область");

        String moscowAmount = tariffs.getAmount();

        tariffs.changeRegion("Краснодарский кр.");

        String krasnodarAmount = tariffs.getAmount();
        assertNotEquals(moscowAmount, krasnodarAmount);

        tariffs.setMaxSettings();

        String krasnodarAmountMax = tariffs.getAmount();

        tariffs.changeRegion("Москва и Московская обл.");
        tariffs.setMaxSettings();
        String moscowAmountMax = tariffs.getAmount();

        assertEquals(moscowAmountMax, krasnodarAmountMax);
    }

    @Test
    @DisplayName("Активная кнопка")
    @Description("Проверить, что кнопка «Заказать SIM-карту» активна после отключения всех пакетов и сервисов")
    public void checkActiveButton() {
        TariffsPage tariffs = new TariffsPage(app);
        tariffs.open();
        tariffs.changeRegion("Москва и Московская обл.");
        tariffs.setZeroSettings();
        assertEquals("0", tariffs.getAmount());

        assertTrue(tariffs.isButtonActive("Заказать сим-карту"));
    }

}

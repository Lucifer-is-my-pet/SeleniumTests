package Application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseRunner {

    protected Application app;

    @BeforeEach
    public void setUp() {
        app = new Application();
    }

    @AfterEach
    public void tearDown() {
        app.tearDown();
    }

}
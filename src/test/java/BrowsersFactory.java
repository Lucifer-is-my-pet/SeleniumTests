import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public enum  BrowsersFactory {

    chrome {
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            return new ChromeDriver(options);
        }
    },

    firefox {
        public WebDriver create() {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },

    opera {
        public WebDriver create() {
            OperaOptions options_opera = new OperaOptions();
//            options_opera.setBinary("C:/Program Files/Opera/56.0.3051.52/Opera.exe");
            options_opera.addArguments("--disable-notifications");
            return new OperaDriver(options_opera);
        }
    };

    public WebDriver create() {
        return null;
    }
}
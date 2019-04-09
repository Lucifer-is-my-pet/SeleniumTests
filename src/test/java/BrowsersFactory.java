import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public enum BrowsersFactory {

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
    }/*,

    opera {
        public WebDriver create() {
            OperaOptions options = new OperaOptions();
            options.setBinary(System.getProperty("operaBinary"));
            options.addArguments("--disable-notifications");
            return new OperaDriver(options);
        }
    }*/;

    public WebDriver create() {
        return null;
    }
}
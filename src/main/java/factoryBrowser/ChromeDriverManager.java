package factoryBrowser;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("start-maximized");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getDownloadFile());
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }
}

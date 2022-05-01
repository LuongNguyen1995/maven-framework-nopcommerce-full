package factoryBrowser;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CoccocDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().driverVersion("97.0.4692.71").setup();
        ChromeOptions options = new ChromeOptions();
        if (GlobalConstants.getGlobalConstants().getOsName().startsWith("Windows")) {
            options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
        }else {
            options.setBinary("...");
        }
        return new ChromeDriver(options);
    }
}

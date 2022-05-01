package factoryBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }
}

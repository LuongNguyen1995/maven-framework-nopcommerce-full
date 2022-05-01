package factoryEnviroment;

import factoryBrowser.BrowserList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {

    private String browserName;
    private String ipAddress;
    private String portNumber;
    private WebDriver driver;
    public GridFactory(String browserName, String ipAddress, String portNumber) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public WebDriver createDriver(){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        DesiredCapabilities capability = null;
        if (browser == BrowserList.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.ANY);

            FirefoxOptions options = new FirefoxOptions();
            options.merge(capability);
        } else if (browser == BrowserList.CHROME) {
            WebDriverManager.chromedriver().setup();
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.ANY);

            ChromeOptions options = new ChromeOptions();
            options.merge(capability);
        }else if (browser == BrowserList.SAFARI) {
            capability = DesiredCapabilities.safari();
            capability.setBrowserName("safari");
            capability.setJavascriptEnabled(true);
            capability.setPlatform(Platform.MAC);
        }  else if (browser == BrowserList.EDGE) {
            WebDriverManager.edgedriver().setup();
            capability = DesiredCapabilities.edge();
            capability.setBrowserName("edge");
            capability.setPlatform(Platform.ANY);
            capability.setJavascriptEnabled(true);
        }else if (browser == BrowserList.IE) {
            WebDriverManager.iedriver().arch32().setup();
            capability = DesiredCapabilities.internetExplorer();
            capability.setBrowserName("internetexplorer");
            capability.setPlatform(Platform.WINDOWS);
            capability.setJavascriptEnabled(true);
        } else {
            throw new RuntimeException("Please input valid browser name value");
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
        } catch(MalformedURLException e){
            e.printStackTrace();
        }

        return driver;

    }

}

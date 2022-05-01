package factoryEnviroment;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LambdaFactory {
    private WebDriver driver;
    private String osName;
    private String browserName;

    public LambdaFactory(String osName, String browserName) {
        this.osName = osName;
        this.browserName = browserName;
    }

    public WebDriver createDriver() {
        DesiredCapabilities capability = new DesiredCapabilities() ;
        capability.setCapability("platform", osName);
        capability.setCapability("browserName", browserName);
        capability.setCapability("version", "latest");
        capability.setCapability("video", true);
        capability.setCapability("visual", true);

        capability.setCapability("resolution", "1920x1080");
        capability.setCapability("name", "Run on " + osName + " | " + browserName);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getLambdaUrl()), capability);
        } catch(MalformedURLException e){
            e.printStackTrace();
        }
        return driver;
    }
}

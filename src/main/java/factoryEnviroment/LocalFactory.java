package factoryEnviroment;

import commons.BrowserNotSupportedException;
import factoryBrowser.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalFactory {

    private WebDriver driver;
    private String browserName;

    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser){
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case COCCOC:
                driver = new CoccocDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case IE:
                driver = new IEDriverManager().getBrowserDriver();
                break;
            case OPERA:
                driver = new OperaDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            case BRAVE:
                driver = new BraveDriverManager().getBrowserDriver();
                break;
            case H_CHROME:
                driver = new HeadlessChromeDriverManager().getBrowserDriver();
                break;
            case H_FIREFOX:
                driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
                break;

            default:
                throw new BrowserNotSupportedException(browserName);
        }
        return driver;
    }

}

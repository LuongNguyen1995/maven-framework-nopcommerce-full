package commons;

import lombok.Getter;

import java.io.File;

@Getter
public class GlobalConstants {
    private static GlobalConstants globalInstance;

    private GlobalConstants(){

    }

    public static synchronized GlobalConstants getGlobalConstants() {
        if (globalInstance==null) {
            globalInstance = new  GlobalConstants();
        }
        return globalInstance;
    }

    private final String portalPageUrl = "https://demo.nopcommerce.com";
    private final String adminPageUrl = "https://admin-demo.nopcommerce.com";
    private final String portalTestingUrl = "https://demo.nopcommerce.com";
    private final String adminTestingUrl = "https://admin-demo.nopcommerce.com";
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");

    private final String uploadFile = projectPath + File.separator +"uploadFiles" + File.separator ;
    private final String downloadFile = projectPath + File.separator +"downloadFiles" ;
    private final String browserLog = projectPath + File.separator +"browserLogs" ;
    private final String dragDropHtml5 = projectPath + File.separator +"dragDropHTML5" ;
    private final String autoItScript = projectPath + File.separator +"autoIT" ;
    private final String reportingScreenshot = projectPath + File.separator +"ReportNGImages"+ File.separator ;

    private final long shortTimeout = 5;
    private final long longTimeout = 30;
    private final long retryTestFail = 3;

    private final String javaVersion = System.getProperty("java.version");
    private final String browserStackUsername = "automationfc1";
    private final String browserStackKey = "UEEKVvr8xuyup8zw36i5";
    private final String browserStackUrl = "https://" +browserStackUsername + ":" +  browserStackKey + "@hub-cloud.browserstack.com/wd/hub";

    private final String saucelabUsername = "oauth-luong.epu.dtvt1-42bdf";
    private final String saucelabKey = "8e5a87d9-50de-4cfd-8958-385499342240";
    private final String saucelabUrl = "https://" + saucelabUsername + ":" + saucelabKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    private final String lambdaUsername = "luong.epu.dtvt1";
    private final String lambdaKey = "hbBg7C6PaDMdWkbesHnEW0TTlKMu97ft7LtTSBNYk2PfgTWbHq";
    private final String lambdaUrl = "https://" + lambdaUsername + ":" + lambdaKey +  "@hub.lambdatest.com/wd/hub";
}

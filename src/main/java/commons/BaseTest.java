package commons;


import factoryEnviroment.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import reportConfig.VerificationFailures;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected final Log log;

    public void initBeforeSuite(){
        deleteAllureReport();
    }
    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String envName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
        switch (envName) {
            case "local":
                driver.set(new LocalFactory(browserName).createDriver());
                break;
            case "grid":
                driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
                break;
            case "browserStack":
                driver.set(new BrowserstackFactory(browserName, osName, osVersion).createDriver());
                break;
            case "saucelab":
                driver.set(new SaucelabFactory(osName, browserName).createDriver());
                break;
            case "lambda":
                driver.set(new LambdaFactory(osName, browserName).createDriver());
                break;

            default:
                driver.set(new LocalFactory(browserName).createDriver());
                break;
        }

        driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
        driver.get().get(getEnviromentUrl(serverName));
        return driver.get();
    }

    public WebDriver getDriverInstance() {
        return this.driver.get();
    }
    private String getEnviromentUrl(String serverName) {
        String envUrl = null;
        EnviromentList enviroment = EnviromentList.valueOf(serverName.toUpperCase());
        if (enviroment == EnviromentList.USER ) {
            envUrl = "https://demo.nopcommerce.com/";
        }else if (enviroment == EnviromentList.ADMIN) {
            envUrl = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
        }
        return envUrl;
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected boolean verifyTrue (boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public void deleteAllureReport() {
        try {
            String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + "/allure-json";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void closeBrowserAndDriver(String envName) {
        if (envName.equals("local") || envName.equals("grid") ) {
            String cmd = "";
            try {
                String osName = System.getProperty("os.name").toLowerCase();
                log.info("OS name = " + osName);

                String driverInstanceName = driver.get().toString().toLowerCase();
                log.info("Driver instance name = " + driverInstanceName);

                if (driverInstanceName.contains("chrome")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                    } else {
                        cmd = "pkill chromedriver";
                    }
                } else if (driverInstanceName.contains("internetexplorer")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                    }
                } else if (driverInstanceName.contains("firefox")) {
                    if (osName.contains("windows")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                    } else {
                        cmd = "pkill geckodriver";
                    }
                } else if (driverInstanceName.contains("edge")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                    } else {
                        cmd = "pkill msedgedriver";
                    }
                } else if (driverInstanceName.contains("opera")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                    } else {
                        cmd = "pkill operadriver";
                    }
                } else if (driverInstanceName.contains("safari")) {
                    if (osName.contains("mac")) {
                        cmd = "pkill safaridriver";
                    }
                }

                if (driver != null) {
                    driver.get().manage().deleteAllCookies();
                    driver.get().quit();

                    driver.remove();
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            } finally {
                try {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void showBrowserConsoleLog(WebDriver driver) {
        if (driver.toString().contains("chrome")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging : logList) {
                log.info("--------------------" + logging.getLevel().toString() + "-----------------\n" + logging.getMessage());
            }
        }
    }


}

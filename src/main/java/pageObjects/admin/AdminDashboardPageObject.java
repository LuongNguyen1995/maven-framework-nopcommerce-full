package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    private WebDriver driver;
    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLeftBarMainTypeByName(String leftBarMainTypeName) {
        waitForElementClickable(driver, AdminDashboardPageUI.LEFT_BAR_MAIN_TYPE_BY_NAME, leftBarMainTypeName);
        clickToElement(driver, AdminDashboardPageUI.LEFT_BAR_MAIN_TYPE_BY_NAME, leftBarMainTypeName);
    }

    public void clickToLeftBarSubTypeByName(String leftBarSubType) {
        waitForElementClickable(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
        clickToElement(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
    }
}

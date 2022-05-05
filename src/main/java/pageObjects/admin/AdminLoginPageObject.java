package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    private WebDriver driver;
    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmail(String email) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPassword(String pass) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, pass);
    }

    public AdminDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}

package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserHomePageUI;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

    private WebDriver driver;
    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;

    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
    }


    public String emailErrorMessage() {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public void enterToEmailTextbox(String invalidEmail) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, invalidEmail);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public String mainErrorMassage() {
        waitForElementVisible(driver, UserLoginPageUI.MAIN_ERROR_MESSAGE);
        return getElementText(driver, UserLoginPageUI.MAIN_ERROR_MESSAGE);
    }

    public boolean isTitleSignInDisplay() {
        return isElementDisplayed(driver, UserHomePageUI.TITLE_WELCOME_OUR_STORE);
    }
}

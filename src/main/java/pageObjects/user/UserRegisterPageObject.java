package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {

    private WebDriver driver;
    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String firstNameErrorText() {
        waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
    }

    public String lastNameErrorText() {
        waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
    }

    public String emailErrorText() {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String passwordErrorText() {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String confirmPasswordErrorText() {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String emailInvalid) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailInvalid);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public String registerSucessfulMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCESSFUL_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCESSFUL_MESSAGE);
    }

    public UserHomePageObject clickToLogoutLink() {
        waitForElementVisible(driver, UserRegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public String registerErrorMassageInMainPage() {
        waitForElementVisible(driver, UserRegisterPageUI.ERROR_MESSAGE_MAIN_PAGE);
        return getElementText(driver, UserRegisterPageUI.ERROR_MESSAGE_MAIN_PAGE);
    }


    public UserHomePageObject clickBannerToHomePage() {
        waitForElementClickable(driver, UserRegisterPageUI.BANNER_NOPCOMMERCE);
        clickToElement(driver, UserRegisterPageUI.BANNER_NOPCOMMERCE);
        return PageGeneratorManager.getUserHomePage(driver);
    }
}

package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {

    private WebDriver driver;

    public UserHomePageObject(WebDriver driver){
        this.driver = driver;
    }


    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public UserMyAccountPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }

    public void clickToButtonSearch() {
        waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
        clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
    }

    public String verifyAlertTextEnterKeyword() {
        waitForAlertPresence(driver);
        return getTextAlert(driver);
    }

    public void acceptSearchAlert() {
        acceptAlert(driver);
    }

    public void enterToSearchTextBox(String searchText) {
        waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver,UserHomePageUI.SEARCH_TEXTBOX, searchText);
    }
}

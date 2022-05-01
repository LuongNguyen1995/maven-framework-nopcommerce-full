package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserMyAccountPageUI;

public class UserMyAccountPageObject extends BasePage {
    private WebDriver driver;
    public UserMyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToGenderFemail() {
       waitForElementClickable(driver, UserMyAccountPageUI.FEMALE_CHECKBOX);
       checkToDefaultCheckboxOrRadio(driver, UserMyAccountPageUI.FEMALE_CHECKBOX);
    }

    public void enterToEditTextboxByID(String textboxID, String textboxEditValue) {
        waitForElementVisible(driver, UserMyAccountPageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, UserMyAccountPageUI.TEXTBOX_BY_ID,textboxEditValue, textboxID);
    }

    public void selectDateValueByName(String dateByName, String valueDate) {
        waitForElementClickable(driver, UserMyAccountPageUI.DATE_BY_NAME, dateByName);
        selectItemInDefaultDropdown(driver, UserMyAccountPageUI.DATE_BY_NAME, valueDate, dateByName);
    }

    public void clickToSaveInfoButton() {
        waitForElementClickable(driver, UserMyAccountPageUI.SAVE_BUTTON);
        clickToElement(driver,UserMyAccountPageUI.SAVE_BUTTON);
    }

    public boolean verifyGenderFemaleIsSelected() {
        return isElementSelected(driver,UserMyAccountPageUI.FEMALE_CHECKBOX);
    }

    public String verifyInfoChangeByID(String textboxID) {
        waitForElementVisible(driver, UserMyAccountPageUI.TEXTBOX_BY_ID, textboxID);
        return getElementText(driver,UserMyAccountPageUI.TEXTBOX_BY_ID, textboxID);
    }

    public String verifyInfoDateChangeByName(String dateByName) {
        waitForElementVisible(driver, UserMyAccountPageUI.DATE_BY_NAME, dateByName);
        return getElementText(driver, UserMyAccountPageUI.DATE_BY_NAME, dateByName);
    }

    public void clickToLeftTabByName(String tabName) {
        waitForElementClickable(driver, UserMyAccountPageUI.LEFT_TAB_BY_NAME, tabName);
        clickToElement(driver, UserMyAccountPageUI.LEFT_TAB_BY_NAME, tabName);
    }

    public void clickToAddNewButton() {
        waitForElementClickable(driver, UserMyAccountPageUI.ADDRESS_ADD_NEW_BUTTON);
        clickToElement(driver, UserMyAccountPageUI.ADDRESS_ADD_NEW_BUTTON);
    }

    public void editAddressByID(String addressFieldByID, String editValue) {
        waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_TEXTBOX_BY_ID, addressFieldByID);
        sendkeyToElement(driver, UserMyAccountPageUI.ADDRESS_TEXTBOX_BY_ID, editValue, addressFieldByID);
    }

    public void selectAddressByID(String addressSelectByID, String selectValue) {
        waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_SELECT_FIELD_BY_ID, addressSelectByID);
        selectItemInDefaultDropdown(driver, UserMyAccountPageUI.ADDRESS_SELECT_FIELD_BY_ID, selectValue, addressSelectByID);
    }

    public void clickToSaveAddressButton() {
        waitForElementClickable(driver, UserMyAccountPageUI.ADDRESS_SAVE_BUTTON);
        clickToElement(driver, UserMyAccountPageUI.ADDRESS_SAVE_BUTTON);
    }

    public String verifyAddressByClassName(String className) {
        waitForElementVisible(driver, UserMyAccountPageUI.ADDRESS_EDITED_BY_CLASSSNAME, className);
        return getElementText(driver, UserMyAccountPageUI.ADDRESS_EDITED_BY_CLASSSNAME, className);
    }

    public void enterToEditChangePasswordFieldByID(String passwordFieldID, String passwordValue) {
        waitForElementVisible(driver, UserMyAccountPageUI.CHANGE_PASSWORD_TEXTBOX_BY_ID, passwordFieldID);
        sendkeyToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_TEXTBOX_BY_ID, passwordValue, passwordFieldID);
    }

    public void clickToChangePasswordButton() {
        waitForElementClickable(driver, UserMyAccountPageUI.CHANGE_PASSWORD_CHANGE_BUTTON);
        clickToElement(driver, UserMyAccountPageUI.CHANGE_PASSWORD_CHANGE_BUTTON);
    }

    public UserHomePageObject clickToLogOutLink() {
        sleepInSecond(1);
        waitForElementClickable(driver, UserMyAccountPageUI.LOGOUT_LINK);
        clickToElement(driver, UserMyAccountPageUI.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public void clickToCloseBannerPasswordChanged() {
        waitForElementClickable(driver, UserMyAccountPageUI.BANNER_NOTI_PASSWORD_CHANGE);
        clickToElement(driver, UserMyAccountPageUI.BANNER_NOTI_PASSWORD_CHANGE);
    }

    public String verifyReviewTitleDisplay() {
        waitForElementVisible(driver, UserMyAccountPageUI.REVIEW_TITLE);
        return getElementText(driver, UserMyAccountPageUI.REVIEW_TITLE);
    }

    public String verifyReviewTextDetailDisplay() {
        waitForElementVisible(driver, UserMyAccountPageUI.REVIEW_TEXT_DETAIL);
        return getElementText(driver, UserMyAccountPageUI.REVIEW_TEXT_DETAIL);
    }

    public String verifyReviewProductNameDisplay() {
        waitForElementVisible(driver, UserMyAccountPageUI.REVIEW_PRODUCT_NAME);
        return getElementText(driver, UserMyAccountPageUI.REVIEW_PRODUCT_NAME);
    }
}

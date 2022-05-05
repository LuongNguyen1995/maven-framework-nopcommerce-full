package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminCustomersPageUI;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminCustomersPageObject extends BasePage {
    private WebDriver driver;
    public AdminCustomersPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAddNewButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.ADD_NEW_BUTTON);
        sleepInSecond(2);
    }

    public void enterToTextboxByID(String textboxID, String textboxValue) {
        waitForElementVisible(driver, AdminCustomersPageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, AdminCustomersPageUI.TEXTBOX_BY_ID, textboxValue, textboxID);
    }

    public void selectGenderMale() {
        waitForElementClickable(driver, AdminCustomersPageUI.GENDER_MALE_RADIO);
        clickToElement(driver, AdminCustomersPageUI.GENDER_MALE_RADIO);
    }

    public void selectCustomerRoles(String roleValue) {

        waitForElementClickable(driver, AdminCustomersPageUI.CLICK_CUSTOMER_RULE);
        clickToElement(driver, AdminCustomersPageUI.CLICK_CUSTOMER_RULE);
        waitForElementClickable(driver, AdminCustomersPageUI.SELECT_CUSTOMER_RULE, roleValue);
        clickToElement(driver, AdminCustomersPageUI.SELECT_CUSTOMER_RULE, roleValue);
    }

    public void checkToActive() {
        waitForElementClickable(driver, AdminCustomersPageUI.CHECKBOX_ACTIVE);
        checkToDefaultCheckboxOrRadio(driver, AdminCustomersPageUI.CHECKBOX_ACTIVE);
    }

    public void enterToAdminComment(String adminComment) {
        waitForElementVisible(driver, AdminCustomersPageUI.TEXTAREA_ADMIN_COMMENT);
        sendkeyToElement(driver, AdminCustomersPageUI.TEXTAREA_ADMIN_COMMENT, adminComment);
    }

    public void clickToSaveContinueButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.SAVE_CONTINUE_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.SAVE_CONTINUE_BUTTON);
    }

    public boolean isMessageAddedSuccessDisplay(String textExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.MESSAGE_SUCCESSFULLY);
        String actualText = getElementText(driver, AdminCustomersPageUI.MESSAGE_SUCCESSFULLY);
        boolean isDisplay;
        if (actualText.equals(textExpect)){
            isDisplay = true;
        }else {
            isDisplay = false;
        }
        return  isDisplay;
    }

    public String verifyTextboxDisplayByID(String valueID) {
        waitForElementVisible(driver, AdminCustomersPageUI.TEXTBOX_BY_ID, valueID);
        return getElementText(driver, AdminCustomersPageUI.TEXTBOX_BY_ID, valueID);
    }

    public boolean isSelectedGenderMale() {
        return isElementDisplayed(driver, AdminCustomersPageUI.GENDER_MALE_CHECKED);
    }

    public boolean isSelectedCustomerRoles(String customerRoles) {
        return isElementDisplayed(driver, AdminCustomersPageUI.CUSTOMER_ROLES_GUEST_DISPLAY, customerRoles);

    }

    public boolean isSelectedActive() {
        return isElementSelected(driver, AdminCustomersPageUI.CHECKBOX_ACTIVE);
    }

    public String verifyAdminCommentDisplay() {
        waitForElementVisible(driver, AdminCustomersPageUI.TEXTAREA_ADMIN_COMMENT);
        return getElementText(driver, AdminCustomersPageUI.TEXTAREA_ADMIN_COMMENT);
    }

    public void clickToBackCustomerList() {
        waitForElementClickable(driver, AdminCustomersPageUI.BACK_CUSTOMER_LIST_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.BACK_CUSTOMER_LIST_BUTTON);
    }

    public void clickToSearchButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.SEARCH_BUTTON);
    }

    public boolean isDataDisplayOnTableResult(String s, String companyName) {
        return true;
    }

    public void clickToDeleteRolesByName(String roleName) {
        waitForElementClickable(driver, AdminCustomersPageUI.CUSTOMER_ROLES_VENDOR_DELETE_BUTTON, roleName);
        clickToElement(driver, AdminCustomersPageUI.CUSTOMER_ROLES_VENDOR_DELETE_BUTTON, roleName);
    }

    public boolean isDataInSearchResultDisplay(String email, String name, String role, String companyName) {
        boolean isDisplay = isElementDisplayed(driver, AdminCustomersPageUI.TEXT_INFO_DISPLAY, email) && isElementDisplayed(driver, AdminCustomersPageUI.TEXT_INFO_DISPLAY, name)
        && isElementDisplayed(driver, AdminCustomersPageUI.TEXT_INFO_DISPLAY, role) && isElementDisplayed(driver, AdminCustomersPageUI.TEXT_INFO_DISPLAY, companyName);
        return isDisplay;
    }

    public void clearDataEmailTextbox() {
        getWebElement(driver, AdminCustomersPageUI.EMAIL_SEARCH).clear();
    }

    public void clearDataFirstnameTextbox() {
        getWebElement(driver, AdminCustomersPageUI.FIRSTNAME_SEARCH).clear();
    }

    public void clearDataLastnameTextbox() {
        getWebElement(driver, AdminCustomersPageUI.LASTNAME_SEARCH).clear();
    }

    public void selectDropdownSearchByID(String dropdownID, String dropdownValue) {
        waitForElementClickable(driver, AdminCustomersPageUI.SELECT_DROPDOWN_SEARCH_BY_ID, dropdownID);
        selectItemInDefaultDropdown(driver, AdminCustomersPageUI.SELECT_DROPDOWN_SEARCH_BY_ID,dropdownValue, dropdownID);
    }

    public void clickToLeftBarSubTypeByName(String leftBarSubType) {
        waitForElementClickable(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
        clickToElement(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
    }

    public void clickToEditButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.EDIT_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.EDIT_BUTTON);
    }

    public void clickToSaveButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.SAVE_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.SAVE_BUTTON);
    }

    public void clickToAddressesTab() {
        waitForElementClickable(driver, AdminCustomersPageUI.ADDRESSES_TAB);
        clickToElement(driver, AdminCustomersPageUI.ADDRESSES_TAB);
    }

    public void clickToAddNewAddressButton() {
        waitForElementClickable(driver, AdminCustomersPageUI.ADD_NEW_ADDRESS_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.ADD_NEW_ADDRESS_BUTTON);
    }

    public void selectDropdownByID(String dropdownID, String dropdownName) {
        waitForElementClickable(driver, AdminCustomersPageUI.SELECT_DROPDOWN_BY_ID, dropdownID);
        selectItemInDefaultDropdown(driver, AdminCustomersPageUI.SELECT_DROPDOWN_BY_ID,dropdownName, dropdownID);
    }

    public void clickToSaveNewAddress() {
        waitForElementClickable(driver, AdminCustomersPageUI.SAVE_NEW_ADDRESS_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.SAVE_NEW_ADDRESS_BUTTON);
    }

    public void clickToBackCustomerDetail() {
        waitForElementClickable(driver, AdminCustomersPageUI.BACK_CUSTOMER_DATAIL_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.BACK_CUSTOMER_DATAIL_BUTTON);
    }

    public boolean isAddressFirstnameDisplay(String valueExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_FIRST_NAME);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_FIRST_NAME);
        boolean isDisplay;
        if (valueActual.equals(valueExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isAddressLastnameDisplay(String valueExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_LAST_NAME);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_LAST_NAME);
        boolean isDisplay;
        if (valueActual.equals(valueExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isAddressEmailDisplay(String valueExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_EMAIL);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_EMAIL);
        boolean isDisplay;
        if (valueActual.equals(valueExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isAddressPhoneNumberDisplay(String valueExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_PHONE_NUMBER);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_PHONE_NUMBER);
        boolean isDisplay;
        if (valueActual.equals(valueExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isAddressFaxNumberDisplay(String valueExpect) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_FAX_NUMBER);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_FAX_NUMBER);
        boolean isDisplay;
        if (valueActual.equals(valueExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isAddressInfoDetailDisplay(String company, String country, String city, String add1, String add2, String zip) {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_ADDRESS);
        String valueActual = getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_ADDRESS);
        boolean isDisplay;
        if (valueActual.equals(company) && valueActual.equals(country) && valueActual.equals(city) && valueActual.equals(add1) && valueActual.equals(add2) && valueActual.equals(zip)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public void clickToEditInAddressTable() {
        waitForElementClickable(driver, AdminCustomersPageUI.ADDRESS_TABLE_EDIT_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.ADDRESS_TABLE_EDIT_BUTTON);
    }

    public void clickToDeleteInAddressTable() {
        waitForElementClickable(driver, AdminCustomersPageUI.ADDRESS_TABLE_DELETE_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.ADDRESS_TABLE_DELETE_BUTTON);
        acceptAlert(driver);
    }


    public String verifyNoDataInTable() {
        waitForElementVisible(driver, AdminCustomersPageUI.ADDRESS_TABLE_EMPTY_DATA);
        return getElementText(driver, AdminCustomersPageUI.ADDRESS_TABLE_EMPTY_DATA);
    }

    public void clickToSaveEditAddress() {
        waitForElementClickable(driver, AdminCustomersPageUI.SAVE_EDIT_ADDRESS_BUTTON);
        clickToElement(driver, AdminCustomersPageUI.SAVE_EDIT_ADDRESS_BUTTON);
    }
}

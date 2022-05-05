package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserBasePageUI;
import pageUIs.user.UserCheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
    private WebDriver driver;
    public UserCheckoutPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingCountryDone() {
        areJQueryAndJSLoadedSuccess(driver);
    }

    public void uncheckToCheckboxByID(String idCheckbox) {
        waitForElementClickable(driver, UserCheckoutPageUI.CHECKBOX_BY_ID, idCheckbox);
        uncheckToDefaultCheckboxRadio(driver, UserCheckoutPageUI.CHECKBOX_BY_ID, idCheckbox);
    }

    public void selectDropdownByID(String idDropdown, String valueSelect) {
        waitForElementClickable(driver, UserCheckoutPageUI.SELECT_DROPDOWN_BY_ID, idDropdown);
        selectItemInDefaultDropdown(driver, UserCheckoutPageUI.SELECT_DROPDOWN_BY_ID, valueSelect, idDropdown);
    }

    public void enterToTextboxByID(String idTextbox, String valueTextbox) {
        waitForElementVisible(driver, UserCheckoutPageUI.INPUT_TEXTBOX_BY_ID, idTextbox);
        sendkeyToElement(driver, UserCheckoutPageUI.INPUT_TEXTBOX_BY_ID,valueTextbox,idTextbox);
    }

    public void clickButtonContinueByID(String idContainer) {
        waitForElementClickable(driver, UserCheckoutPageUI.CONTINUE_BUTTON_BY_ID, idContainer);
        clickToElement(driver, UserCheckoutPageUI.CONTINUE_BUTTON_BY_ID, idContainer);

    }

    public void selectRadioboxByName(String radioName) {
        waitForElementClickable(driver, UserCheckoutPageUI.RADIOBOX_BY_NAME, radioName);
        checkToDefaultCheckboxOrRadio(driver, UserCheckoutPageUI.RADIOBOX_BY_NAME, radioName);
    }

    public boolean isPaymentInfoDisplay() {
        return isElementDisplayed(driver, UserCheckoutPageUI.PAYMENT_INFO_DISPLAY);
    }

    public boolean isBillingAddressDisplay(String inforExpect) {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_BILLING_ADDRESS_TEXT);
        String actualTextDisplay = getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_BILLING_ADDRESS_TEXT);
        boolean isDisplay;
        if (actualTextDisplay.equals(inforExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isPaymentMethodDisplay(String inforExpect) {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_PAYMENT_METHOD_TEXT);
        String actualTextDisplay = getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_PAYMENT_METHOD_TEXT);
        boolean isDisplay;
        if (actualTextDisplay.equals(inforExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isShippingAddressDisplay(String inforExpect) {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_SHIPPING_ADDRESS_TEXT);
        String actualTextDisplay = getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_SHIPPING_ADDRESS_TEXT);
        boolean isDisplay;
        if (actualTextDisplay.equals(inforExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isShippingMethodDisplay(String inforExpect) {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_SHIPPING_METHOD_TEXT);
        String actualTextDisplay = getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_SHIPPING_METHOD_TEXT);
        boolean isDisplay;
        if (actualTextDisplay.equals(inforExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }


    public String verifyInfoProductName() {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_PRODUCT_NAME_DISPLAY);
        return getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_PRODUCT_NAME_DISPLAY);
    }

    public String verifyInfoProductByClassName(String className) {
        waitForElementVisible(driver, UserCheckoutPageUI.CONFIRM_ORDER_PRODUCT_INFO_BY_CLASS_NAME, className);
        return getElementText(driver, UserCheckoutPageUI.CONFIRM_ORDER_PRODUCT_INFO_BY_CLASS_NAME, className);
    }

    public String verifyInfoInCartTotalByClassName(String className) {
        waitForElementVisible(driver, UserCheckoutPageUI.CART_TOTAL_DISPLAY_BY_CLASS_NAME, className);
        return getElementText(driver, UserCheckoutPageUI.CART_TOTAL_DISPLAY_BY_CLASS_NAME, className);
    }

    public boolean isThankYouDisplay() {
        return isElementDisplayed(driver, UserCheckoutPageUI.THANK_ORDER);
    }

    public String verifyOrderSuccessfulDisplayText() {
        waitForElementVisible(driver, UserCheckoutPageUI.ORDER_SUCCESS_TEXT);
        return getElementText(driver, UserCheckoutPageUI.ORDER_SUCCESS_TEXT);
    }

    public String getOrderNumber() {
        waitForElementVisible(driver, UserCheckoutPageUI.ORDER_NUMBER);
        return getElementText(driver, UserCheckoutPageUI.ORDER_NUMBER).substring(14);
    }

    public UserMyAccountPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserBasePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }

    public void clickButtonConfirmOrderInCheckout() {
        waitForElementClickable(driver, UserCheckoutPageUI.CONFIRM_ORDER_CHECKOUT_BUTTON);
        clickToElement(driver, UserCheckoutPageUI.CONFIRM_ORDER_CHECKOUT_BUTTON);
        sleepInSecond(3);
    }
}

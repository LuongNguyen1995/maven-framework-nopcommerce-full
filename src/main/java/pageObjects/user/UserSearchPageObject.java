package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserSearchPageUI;

import java.util.List;

public class UserSearchPageObject extends BasePage {
    WebDriver driver;
    public UserSearchPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String verifySearchErrorResult() {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_RESULT_ERROR);
        return getElementText(driver, UserSearchPageUI.SEARCH_RESULT_ERROR);
    }

    public void enterToSearchTextBox(String searchText) {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver, UserSearchPageUI.SEARCH_TEXTBOX, searchText);

    }

    public void clickToButtonSearch() {
        waitForElementClickable(driver,UserSearchPageUI.SEARCH_BUTTON_IN_SEARCH_STORE);
        clickToElement(driver,UserSearchPageUI.SEARCH_BUTTON_IN_SEARCH_STORE);
    }

    public String verifySearchNoResult() {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_NO_RESULT);
        return getElementText(driver, UserSearchPageUI.SEARCH_NO_RESULT);
    }

    public int totalProductNameSearchDisplayed() {
        waitForAllElementVisible(driver, UserSearchPageUI.PRODUCT_SEARCH_DISPLAYED);
        return getElementSize(driver, UserSearchPageUI.PRODUCT_SEARCH_DISPLAYED);
    }

    public boolean isProductNameSearchDisplayed(String productName) {
        waitForAllElementVisible(driver, UserSearchPageUI.PRODUCT_SEARCH_DISPLAYED);
        boolean isDisplayed = false;
        List<WebElement> products = getListWebElement(driver, UserSearchPageUI.PRODUCT_SEARCH_DISPLAYED);
        for(WebElement product : products){
            if (product.getText().equals(productName)){
                isDisplayed = true;
            }
        }
        return isDisplayed;
    }

    public void clickToCheckboxSearchByName(String labelCheckbox) {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_CHECKBOX_BY_LABEL, labelCheckbox);
        checkToDefaultCheckboxOrRadio(driver, UserSearchPageUI.SEARCH_CHECKBOX_BY_LABEL, labelCheckbox);
    }

    public void selectDropdownFieldByName(String dropdownFieldName, String dropdownName) {
        waitForElementVisible(driver, UserSearchPageUI.DROPDOWN_FIELD_BY_NAME,dropdownFieldName);
        selectItemInDefaultDropdown(driver, UserSearchPageUI.DROPDOWN_FIELD_BY_NAME, dropdownName, dropdownFieldName);
    }

    public void clickToUnCheckboxSearchByName(String labelCheckbox) {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_CHECKBOX_BY_LABEL, labelCheckbox);
        uncheckToDefaultCheckboxRadio(driver, UserSearchPageUI.SEARCH_CHECKBOX_BY_LABEL, labelCheckbox);
    }

    public void enterToSearchTextBoxField(String valueSearch) {
        waitForElementVisible(driver, UserSearchPageUI.SEARCH_KEYWORD_TEXTBOX);
        sendkeyToElement(driver, UserSearchPageUI.SEARCH_KEYWORD_TEXTBOX, valueSearch);
    }

    public void clickToButtonSearchKeyword() {
        waitForElementClickable(driver, UserSearchPageUI.SEARCH_BUTTON_IN_SEARCH_KEYWORD);
        clickToElement(driver, UserSearchPageUI.SEARCH_BUTTON_IN_SEARCH_KEYWORD);
    }
}

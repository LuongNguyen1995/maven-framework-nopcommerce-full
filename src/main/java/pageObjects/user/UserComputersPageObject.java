package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserBasePageUI;
import pageUIs.user.UserComputersPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserComputersPageObject extends BasePage {
    private WebDriver driver;
    public UserComputersPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToProductByName(String productName) {
        waitForElementClickable(driver, UserComputersPageUI.PRODUCT_NAME_BY_TEXT,productName);
        clickToElement(driver, UserComputersPageUI.PRODUCT_NAME_BY_TEXT,productName);
    }

    public void clickToAddYourReview() {
        waitForElementClickable(driver, UserComputersPageUI.ADD_YOUR_REVIEW_BUTTON);
        clickToElement(driver, UserComputersPageUI.ADD_YOUR_REVIEW_BUTTON);
    }

    public void enterToReviewTitle(String titleReview) {
        waitForElementVisible(driver, UserComputersPageUI.REVIEW_TITLE_TEXTBOX);
        sendkeyToElement(driver,UserComputersPageUI.REVIEW_TITLE_TEXTBOX, titleReview);
    }

    public void enterToReviewText(String reviewText) {
        waitForElementVisible(driver, UserComputersPageUI.REVIEW_TEXT_TEXTBOX);
        sendkeyToElement(driver, UserComputersPageUI.REVIEW_TEXT_TEXTBOX, reviewText);
    }

    public void clickToSubmitReview() {
        waitForElementClickable(driver, UserComputersPageUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver, UserComputersPageUI.SUBMIT_REVIEW_BUTTON);
    }

    public boolean verifyProductReviewAdded() {
        return isElementDisplayed(driver, UserComputersPageUI.PRODUCT_REVIEW_SUCCESS_TEXT);
    }

    public void clickToMyAccountLink() {
        waitForElementClickable(driver, UserBasePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserBasePageUI.MY_ACCOUNT_LINK);
    }

    public void clickToCategoriesByName(String categoriesProductName) {
        waitForElementClickable(driver, UserComputersPageUI.CATEGORIES_PRODUCT_BY_NAME, categoriesProductName);
        clickToElement(driver, UserComputersPageUI.CATEGORIES_PRODUCT_BY_NAME, categoriesProductName);
    }

    public void clickToSelectSortByText(String sortByValue) {
        waitForElementClickable(driver, UserComputersPageUI.NOTEBOOK_SORT_BY_DROPDOWN);
        selectItemInDefaultDropdown(driver,UserComputersPageUI.NOTEBOOK_SORT_BY_DROPDOWN, sortByValue);
    }

    public boolean isProductNameSortAscending() {
        List<String> productNameText = new ArrayList<>();
        List<WebElement> productNameElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_RPODUCT_TITLE_SORT);
        for (WebElement productName : productNameElements){
            productNameText.add(productName.getText());
        }
        List<String> productNameTextClone = new ArrayList<String>();
        for (String productName : productNameText){
            productNameTextClone.add(productName);
        }
        Collections.sort(productNameTextClone);
        return productNameText.equals(productNameTextClone);
    }

    public boolean isProductNameSortDescending() {
        List<String> productNameText = new ArrayList<>();
        List<WebElement> productNameElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_RPODUCT_TITLE_SORT);
        for (WebElement productName : productNameElements){
            productNameText.add(productName.getText());
        }
        List<String> productNameTextClone = new ArrayList<String>();
        for (String productName : productNameText){
            productNameTextClone.add(productName);
        }
        Collections.sort(productNameTextClone);
        Collections.reverse(productNameTextClone);
        return productNameText.equals(productNameTextClone);
    }

    public boolean isProductPriceSortAscending() {
        List<Float> productNamePrice = new ArrayList<Float>();
        List<WebElement> productPriceElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_RPODUCT_PRICE_SORT);
        for (WebElement productPrice : productPriceElements){
            productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$","").trim()));
        }
        List<Float> productNamePriceSort = new ArrayList<Float>();
        for (Float productPrice:productNamePrice){
            productNamePriceSort.add(productPrice);
        }
        Collections.sort(productNamePriceSort);
        return productNamePrice.equals(productNamePriceSort);
    }

    public boolean isProductPriceSortDescending() {
        List<Float> productNamePrice = new ArrayList<Float>();
        List<WebElement> productPriceElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_RPODUCT_PRICE_SORT);
        for (WebElement productPrice : productPriceElements){
            productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$","").trim()));
        }
        List<Float> productNamePriceSort = new ArrayList<Float>();
        for (Float productPrice:productNamePrice){
            productNamePriceSort.add(productPrice);
        }
        Collections.sort(productNamePriceSort);
        Collections.reverse(productNamePriceSort);
        return productNamePrice.equals(productNamePriceSort);
    }
}

package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserBasePageUI;
import pageUIs.user.UserShopingCartPageUI;

import java.util.List;

public class UserShopingCartPageObject extends BasePage {
    private WebDriver driver;
    public UserShopingCartPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayedProductName(String productNameExpect) {
        waitForAllElementVisible(driver, UserShopingCartPageUI.PRODUCT_NAME_IN_SHOPING_CART);
        List<WebElement> productNames = getListWebElement(driver, UserShopingCartPageUI.PRODUCT_NAME_IN_SHOPING_CART);
        boolean isProductDisplay = false;
        for (WebElement productName : productNames){
            if(productName.getText().equals(productNameExpect)){
                isProductDisplay = true;
            }
        }
        return isProductDisplay;
    }


    public UserWishlistPageObject clickToWishListLinkOnTopBar() {
        waitForElementClickable(driver, UserBasePageUI.WISHLIST_LINK);
        clickToElement(driver, UserBasePageUI.WISHLIST_LINK);
        return PageGeneratorManager.getUserWishListPage(driver);
    }

    public UserComputersPageObject clickToEditProduct() {
        waitForElementClickable(driver, UserShopingCartPageUI.EDIT_BUTTON_IN_SHOPING_CART);
        clickToElement(driver, UserShopingCartPageUI.EDIT_BUTTON_IN_SHOPING_CART);
        return PageGeneratorManager.getUserComputersPage(driver);
    }

    public void clickToRemoveProduct() {
        waitForElementClickable(driver, UserShopingCartPageUI.REMOVE_BUTTON_IN_SHOPING_CART);
        clickToElement(driver, UserShopingCartPageUI.REMOVE_BUTTON_IN_SHOPING_CART);
    }

    public String verifyShopingCartEmptyText() {
        waitForElementVisible(driver, UserShopingCartPageUI.EMPTY_DATA_IN_SHOPING_CART);
        return getElementText(driver, UserShopingCartPageUI.EMPTY_DATA_IN_SHOPING_CART);
    }

    public boolean isProductNameNotDisplay() {
        return isElementUndisplayed(driver, UserShopingCartPageUI.PRODUCT_NAME_IN_SHOPING_CART);
    }

    public void clickToAcceptTermsCheckbox() {
        waitForElementClickable(driver, UserShopingCartPageUI.ACCEPT_TERMS_CHECKBOX);
        checkToDefaultCheckboxOrRadio(driver, UserShopingCartPageUI.ACCEPT_TERMS_CHECKBOX);
    }

    public UserCheckoutPageObject clickToCheckoutButton() {
        waitForElementClickable(driver, UserShopingCartPageUI.CHECKOUT_BUTTON);
        clickToElement(driver, UserShopingCartPageUI.CHECKOUT_BUTTON);
        return PageGeneratorManager.getUserCheckoutPage(driver);
    }

    public void enterQuantityProduct(String quantity) {
        waitForElementVisible(driver, UserShopingCartPageUI.INPUT_QUANTITY_PRODUCT);
        sendkeyToElement(driver, UserShopingCartPageUI.INPUT_QUANTITY_PRODUCT, quantity);
    }
}

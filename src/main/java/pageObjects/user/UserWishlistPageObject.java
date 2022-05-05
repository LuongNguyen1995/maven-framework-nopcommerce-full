package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserBasePageUI;
import pageUIs.user.UserWishlistPageUI;

import java.util.List;

public class UserWishlistPageObject extends BasePage {
    private WebDriver driver;
    public UserWishlistPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductAddedInWishlist(String productNameExpect) {
    waitForAllElementVisible(driver, UserWishlistPageUI.PRODUCT_NAME_IN_WISHLIST);
    List<WebElement> productNames = getListWebElement(driver, UserWishlistPageUI.PRODUCT_NAME_IN_WISHLIST);
    boolean isProductDisplay = false;
    for (WebElement productName : productNames){
        if(productName.getText().equals(productNameExpect)){
            isProductDisplay = true;
        }
    }
    return isProductDisplay;
    }

    public void clickToWishlistSharingUrl() {
        waitForElementClickable(driver, UserWishlistPageUI.WISHLIST_SHARING_URL);
        clickToElement(driver, UserWishlistPageUI.WISHLIST_SHARING_URL);
    }

    public String verifyWishListBannerForUser() {
        waitForElementVisible(driver, UserWishlistPageUI.WISHLIST_USER_TITLE);
        return getElementText(driver, UserWishlistPageUI.WISHLIST_USER_TITLE);
    }

    public void clickToCheckboxProductNameInWishlist(String productName) {
        waitForElementClickable(driver,UserWishlistPageUI.WISHLIST_PRODUCT_CHECKBOX_BY_NAME,productName);
        checkToDefaultCheckboxOrRadio(driver, UserWishlistPageUI.WISHLIST_PRODUCT_CHECKBOX_BY_NAME, productName);
    }

    public void clickAddToCard() {
        waitForElementClickable(driver, UserWishlistPageUI.WISHLIST_ADD_TO_CARD_BUTTON);
        checkToDefaultCheckboxOrRadio(driver, UserWishlistPageUI.WISHLIST_ADD_TO_CARD_BUTTON);
    }

    public boolean isWishListEmpty(String textDisplay) {
        waitForElementVisible(driver, UserWishlistPageUI.WISHLIST_NO_DATA_RESULT);
        String textEmptyWishlist = getElementText(driver, UserWishlistPageUI.WISHLIST_NO_DATA_RESULT);
        boolean isEmptyData;
        if (textEmptyWishlist.equals(textDisplay)){
            isEmptyData = true;
        }else{
            isEmptyData = false;
        }
        return isEmptyData;
    }

    public void clickToWishListLinkOnTopBar() {
        waitForElementClickable(driver, UserBasePageUI.WISHLIST_LINK);
        clickToElement(driver, UserBasePageUI.WISHLIST_LINK);
    }

    public void backToWishlistPage() {
        backToPage(driver);
    }

    public void clickToRemoveProductByName(String productName) {
        waitForElementClickable(driver, UserWishlistPageUI.WISHTLIST_REMOVE_PRODUCT_BUTTON_BY_NAME, productName);
        clickToElement(driver, UserWishlistPageUI.WISHTLIST_REMOVE_PRODUCT_BUTTON_BY_NAME, productName);
    }

}

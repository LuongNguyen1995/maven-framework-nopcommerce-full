package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
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
        sleepInSecond(2);
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
        ArrayList<Float> productNamePrice = new ArrayList<Float>();
        List<WebElement> productPriceElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_PRICE_SORT);
        for (WebElement productPrice : productPriceElements){
            productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$","").replace(".00", "").replace(",","").trim()));
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
        List<WebElement> productPriceElements = getListWebElement(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_PRICE_SORT);
        for (WebElement productPrice : productPriceElements){
            productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$","").replace(".00", "").replace(",","").trim()));
        }
        List<Float> productNamePriceSort = new ArrayList<Float>();
        for (Float productPrice:productNamePrice){
            productNamePriceSort.add(productPrice);
        }
        Collections.sort(productNamePriceSort);
        Collections.reverse(productNamePriceSort);
        return productNamePrice.equals(productNamePriceSort);
    }

    public void clickToSelectDisplayPerPage(String numberProductPerPage) {
        waitForElementClickable(driver, UserComputersPageUI.NOTEBOOK_DISPLAY_PER_PAGE_DROPDOWN);
        selectItemInDefaultDropdown(driver, UserComputersPageUI.NOTEBOOK_DISPLAY_PER_PAGE_DROPDOWN, numberProductPerPage);
    }

    public int verifyTotalProductInThisPage() {
        waitForAllElementInisible(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_TITLE_DISPLAY_IN_PAGE);
        return getElementSize(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_TITLE_DISPLAY_IN_PAGE);
    }

    public boolean isNextButtonDisplayedWhenPageAt(String currentPage) {
        waitForElementVisible(driver, UserComputersPageUI.NOTEBOOK_CURRENT_PAGE);
        String actualPage = getElementText(driver, UserComputersPageUI.NOTEBOOK_CURRENT_PAGE);
        if (actualPage.equals(currentPage) && isElementDisplayed(driver, UserComputersPageUI.NOTEBOOK_NEXT_PAGE_BUTTON)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPreviosButtonDisplayedWhenPageAt(String currentPage) {
        waitForElementVisible(driver, UserComputersPageUI.NOTEBOOK_CURRENT_PAGE);
        String actualPage = getElementText(driver, UserComputersPageUI.NOTEBOOK_CURRENT_PAGE);
        if (actualPage.equals(currentPage) && isElementDisplayed(driver, UserComputersPageUI.NOTEBOOK_PREVIOS_PAGE_BUTTON)){
            return true;
        }else{
            return false;
        }


    }

    public boolean isTotalProductInThisPage(int maxProductInPage) {
        waitForAllElementInisible(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_TITLE_DISPLAY_IN_PAGE);
        int totalProductInPage = getElementSize(driver, UserComputersPageUI.NOTEBOOK_PRODUCT_TITLE_DISPLAY_IN_PAGE);
        if (totalProductInPage <= maxProductInPage){
            return true;
        }else{
            return false;
        }
    }

    public boolean isNotDisplayedNextButton() {
        return isElementUndisplayed(driver, UserComputersPageUI.NOTEBOOK_NEXT_PAGE_BUTTON);
    }

    public boolean isNotDisplayedPreviosButton() {
        return isElementUndisplayed(driver, UserComputersPageUI.NOTEBOOK_PREVIOS_PAGE_BUTTON);
    }

    public String verifyNotiAddSucces() {
        waitForElementVisible(driver, UserComputersPageUI.TEXT_BAR_NOTI_SUCCESS);
        return getElementText(driver, UserComputersPageUI.TEXT_BAR_NOTI_SUCCESS);
    }

    public void clickAddToWishlist() {
        waitForElementClickable(driver, UserComputersPageUI.PRODUCT_ADD_TO_WISHLIST_BUTTON);
        clickToElement(driver, UserComputersPageUI.PRODUCT_ADD_TO_WISHLIST_BUTTON);
    }

    public void clickToCloseNotiAddSuccess() {
        waitForElementClickable(driver, UserComputersPageUI.CLOSE_POPUP_BAR_NOTI_SUCCESS);
        clickToElement(driver, UserComputersPageUI.CLOSE_POPUP_BAR_NOTI_SUCCESS);
        sleepInSecond(1);
    }

    public UserWishlistPageObject clickToWishListLinkOnTopBar() {
        waitForElementClickable(driver, UserBasePageUI.WISHLIST_LINK);
        clickToElement(driver, UserBasePageUI.WISHLIST_LINK);
        return PageGeneratorManager.getUserWishListPage(driver);
    }

    public void clickToCompareProductByName(String productName) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_ADD_TO_COMPARE_BUTTON_BY_NAME, productName);
        clickToElement(driver, UserComputersPageUI.COMPUTER_ADD_TO_COMPARE_BUTTON_BY_NAME, productName);
    }

    public boolean isNotiCompareSuccessDisplayed(String textNotiExpect) {
        waitForElementVisible(driver, UserComputersPageUI.COMPUTER_NOTI_COMPARE_SUCCESS);
        String textActual = getElementText(driver, UserComputersPageUI.COMPUTER_NOTI_COMPARE_SUCCESS);
        boolean isDisplay;
        if (textNotiExpect.equals(textActual)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public void clickToCloseNotiCompareSuccess() {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_CLOSE_NOTI_COMPARE_SUCCESS_BUTTON);
        clickToElement(driver, UserComputersPageUI.COMPUTER_CLOSE_NOTI_COMPARE_SUCCESS_BUTTON);
        sleepInSecond(1);
    }

    public UserCompareProductPageObject clickToViewDetailCompareProduct() {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_COMPARE_ON_NOTI_BUTTON);
        clickToElement(driver, UserComputersPageUI.COMPUTER_COMPARE_ON_NOTI_BUTTON);
        return PageGeneratorManager.getUserCompareProductPage(driver);
    }


    public String saveProductPriceByName(String productName, String productPrice) {
        waitForElementVisible(driver, UserComputersPageUI.COMPUTER_PRICE_BY_NAME,productName);
        productPrice = getElementText(driver, UserComputersPageUI.COMPUTER_PRICE_BY_NAME,productName);
        return productPrice;
    }

    public void backToComputerPage() {
        backToPage(driver);
    }

    public boolean isRecentlyProductDisplayByName(String productNameExpect) {
        waitForAllElementVisible(driver, UserComputersPageUI.COMPUTER_RECENTLY_VIEW_PRODUCTS);
        List<WebElement> productElements = getListWebElement(driver, UserComputersPageUI.COMPUTER_RECENTLY_VIEW_PRODUCTS);
        boolean resultDisplay = false;
        for (WebElement product : productElements){
            if (product.getText().equals(productNameExpect)){
                resultDisplay = true;
            }
        }
        return resultDisplay;
    }

    public void selectProcessorByName(String computerProcessor) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_SELECT_PROCESSOR);
        selectItemInDefaultDropdown(driver,UserComputersPageUI.COMPUTER_SELECT_PROCESSOR,computerProcessor);
    }

    public void selectRamByName(String computerRam) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_SELECT_RAM);
        selectItemInDefaultDropdown(driver, UserComputersPageUI.COMPUTER_SELECT_RAM, computerRam);
    }


    public void selectRadioboxByName(String radioText) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_RADIOBOX_BY_NAME,radioText );
        checkToDefaultCheckboxOrRadio(driver, UserComputersPageUI.COMPUTER_RADIOBOX_BY_NAME,radioText);
    }

    public void selectCheckboxByName(String checkboxText) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_CHECKBOX_BY_NAME, checkboxText);
        checkToDefaultCheckboxOrRadio(driver, UserComputersPageUI.COMPUTER_CHECKBOX_BY_NAME, checkboxText);
    }

    public void clickAddToCart() {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_ADD_TO_CART_BUTTON);
        clickToElement(driver, UserComputersPageUI.COMPUTER_ADD_TO_CART_BUTTON);
    }

    public String verifyAddProductInShoppingCart() {
        waitForElementVisible(driver, UserBasePageUI.SHOPPING_CART_LINK);
        return getElementText(driver, UserBasePageUI.SHOPPING_CART_LINK);
    }

    public void hoverMouseToShoppingCartLink() {
        scrollToElement(driver, UserBasePageUI.SHOPPING_CART_LINK);
        waitForElementVisible(driver, UserBasePageUI.SHOPPING_CART_LINK);
        hoverMouseToElement(driver, UserBasePageUI.SHOPPING_CART_LINK);
    }


    public String getPriceProductAfterConfig() {
        waitForElementVisible(driver, UserComputersPageUI.COMPUTER_PRODUCT_PRICE_AFTER_CONFIG);
        return getElementText(driver, UserComputersPageUI.COMPUTER_PRODUCT_PRICE_AFTER_CONFIG);
    }

    public String verifyTotalItemInCart() {
        waitForElementVisible(driver, UserBasePageUI.MINI_SHOPPING_CART_COUNT_ITEM);
        return getElementText(driver, UserBasePageUI.MINI_SHOPPING_CART_COUNT_ITEM);
    }

    public String verifyProductNameAddInCart() {
        waitForElementVisible(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_NAME);
        return getElementText(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_NAME);
    }

    public boolean isDisplayProductInfoAddInCart(String infoTextExpect) {
        waitForElementVisible(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_INFO);
        String infoTextActual = getElementText(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_INFO);
        boolean isDisplay;
        if (infoTextActual.equals(infoTextExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isDisplayProductQuantityAddInCart(String infoTextExpect) {
        waitForElementVisible(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_QUANTITY);
        String infoTextActual = getElementText(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_QUANTITY);
        boolean isDisplay;
        if (infoTextActual.equals(infoTextExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public boolean isDisplayProductSubTotalAddInCart(String infoTextExpect) {
        waitForElementVisible(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_PRICE);
        String infoTextActual = getElementText(driver, UserBasePageUI.MINI_SHOPPING_PRODUCT_PRICE);
        boolean isDisplay;
        if (infoTextActual.equals(infoTextExpect)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public UserShopingCartPageObject clickToShoppingCartLink() {
        waitForElementClickable(driver, UserBasePageUI.SHOPPING_CART_LINK);
        clickToElement(driver, UserBasePageUI.SHOPPING_CART_LINK);
        return PageGeneratorManager.getUserShopingCartPage(driver);
    }


    public void selectUncheckboxByName(String softwareName) {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_CHECKBOX_BY_NAME, softwareName);
        uncheckToDefaultCheckboxRadio(driver, UserComputersPageUI.COMPUTER_CHECKBOX_BY_NAME, softwareName);
    }

    public void clickToUpdateButton() {
        waitForElementClickable(driver, UserComputersPageUI.COMPUTER_UPDATE_PRODUCT_BUTTON);
        clickToElement(driver, UserComputersPageUI.COMPUTER_UPDATE_PRODUCT_BUTTON);
    }

    public void updateQuantityProduct(String quantity) {
        waitForElementVisible(driver, UserComputersPageUI.COMPUTER_QUANTITY_PRODUCT_UPDATE);
        sendkeyToElement(driver, UserComputersPageUI.COMPUTER_QUANTITY_PRODUCT_UPDATE, quantity);
    }

    public void clickToUpdateQuantity(String quantity) {
        waitForElementVisible(driver, UserComputersPageUI.COMPUTER_QUANTITY_PRODUCT);
        sendkeyToElement(driver, UserComputersPageUI.COMPUTER_QUANTITY_PRODUCT, quantity);
    }
}

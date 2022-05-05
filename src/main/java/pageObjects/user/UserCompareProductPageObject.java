package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserCompareProductPageUI;

import java.util.List;

public class UserCompareProductPageObject extends BasePage {
    private WebDriver driver;
    public UserCompareProductPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTotalRemoveButtonProductDisplayed(int numberRemoveButtonExpect) {
        waitForAllElementVisible(driver, UserCompareProductPageUI.REMOVE_BUTTON);
        int numberRemoveButtonActual = getElementSize(driver, UserCompareProductPageUI.REMOVE_BUTTON);
        if (numberRemoveButtonActual == numberRemoveButtonExpect){
            return true;
        }else{
            return false;
        }
    }

    public boolean isProductNameDisplayByName(String productNameExpect) {
        waitForAllElementVisible(driver, UserCompareProductPageUI.PRODUCT_NAME);
        List<WebElement> productElement = getListWebElement(driver, UserCompareProductPageUI.PRODUCT_NAME);
        boolean isDisplay = false;
        for (WebElement product : productElement){
            if (product.getText().equals(productNameExpect)){
                isDisplay = true;
            }
        }
    return isDisplay;
    }

    public boolean isProductPriceDisplayByName(String productPriceExpect) {
        waitForAllElementVisible(driver, UserCompareProductPageUI.PRODUCT_PRICE);
        List<WebElement> productElement = getListWebElement(driver, UserCompareProductPageUI.PRODUCT_PRICE);
        boolean isDisplay = false;
        for (WebElement product : productElement){
            if (product.getText().equals(productPriceExpect)){
                isDisplay = true;
            }
        }
        return isDisplay;
    }

    public boolean isClearListDisplay() {
        return isElementDisplayed(driver, UserCompareProductPageUI.CLEAR_LIST_BUTTON);
    }

    public void clickToClearListButton() {
        waitForElementClickable(driver, UserCompareProductPageUI.CLEAR_LIST_BUTTON);
        clickToElement(driver, UserCompareProductPageUI.CLEAR_LIST_BUTTON);
    }

    public String verifyClearCompareProduct() {
        waitForElementVisible(driver, UserCompareProductPageUI.NO_PRODUCT_COMPARE_DISPLAY);
        return getElementText(driver, UserCompareProductPageUI.NO_PRODUCT_COMPARE_DISPLAY);
    }
}

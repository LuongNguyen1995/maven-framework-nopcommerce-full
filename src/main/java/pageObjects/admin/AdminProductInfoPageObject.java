package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminProductInfoPageUI;

public class AdminProductInfoPageObject extends BasePage {
    private WebDriver driver;
    public AdminProductInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductDetailsPageDisplay(String titlePage) {
        waitForElementVisible(driver, AdminProductInfoPageUI.PRODUCT_NAME_DETAIL_BANNER);
        String actualTitle = getElementText(driver, AdminProductInfoPageUI.PRODUCT_NAME_DETAIL_BANNER);
        boolean isDisplay;
        if (actualTitle.equals(titlePage)){
            isDisplay = true;
        }else{
            isDisplay = false;
        }
        return isDisplay;
    }

    public String verifyProductNameDisplay() {
        waitForElementVisible(driver, AdminProductInfoPageUI.PRODUCT_NAME);
        return getElementText(driver, AdminProductInfoPageUI.PRODUCT_NAME);
    }
}

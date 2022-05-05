package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminCatalogProductsPageUI;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminCatalogProductsPageObject extends BasePage {
    private WebDriver driver;
    public AdminCatalogProductsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToSearchByID(String textboxID, String textboxValue) {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, AdminCatalogProductsPageUI.TEXTBOX_BY_ID, textboxValue, textboxID);
    }

    public void clickToSearchButton() {
        waitForElementClickable(driver, AdminCatalogProductsPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminCatalogProductsPageUI.SEARCH_BUTTON);
    }

    public int verifyNumberProductDisplay() {
        waitForAllElementVisible(driver, AdminCatalogProductsPageUI.CHECKBOX_PRODUCT_NUMBER);
        return getElementSize(driver, AdminCatalogProductsPageUI.CHECKBOX_PRODUCT_NUMBER);
    }

    public String verifyProductNameDisplay() {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.PRODUCT_NAME_DISPLAY);
        return getElementText(driver, AdminCatalogProductsPageUI.PRODUCT_NAME_DISPLAY);
    }

    public String verifyProductSkuDisplay() {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.PRODUCT_SKU_DISPLAY);
        return getElementText(driver, AdminCatalogProductsPageUI.PRODUCT_SKU_DISPLAY);
    }

    public String verifyProductPriceDisplay() {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.PRODUCT_PRICE_DISPLAY);
        return getElementText(driver, AdminCatalogProductsPageUI.PRODUCT_PRICE_DISPLAY);
    }

    public String verifyProductQuantityDisplay() {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.PRODUCT_QUANTITY_DISPLAY);
        return getElementText(driver, AdminCatalogProductsPageUI.PRODUCT_QUANTITY_DISPLAY);
    }

    public boolean isProductPublished() {
        return isElementDisplayed(driver, AdminCatalogProductsPageUI.PRODUCT_PUBLISH);
    }

    public void selectDropdownFieldByID(String dropdownId, String valueDropdown) {
        waitForElementClickable(driver, AdminCatalogProductsPageUI.SELECT_DROPDOWN_BY_ID, dropdownId);
        selectItemInDefaultDropdown(driver, AdminCatalogProductsPageUI.SELECT_DROPDOWN_BY_ID, valueDropdown, dropdownId);
    }

    public void uncheckCheckboxByID(String checkboxId) {
        waitForElementClickable(driver, AdminCatalogProductsPageUI.SELECT_CHECKBOX_BY_ID, checkboxId);
        uncheckToDefaultCheckboxRadio(driver, AdminCatalogProductsPageUI.SELECT_CHECKBOX_BY_ID, checkboxId);
    }

    public String verifyNoDataInResultTable() {
        waitForElementVisible(driver, AdminCatalogProductsPageUI.NO_DATA_SEARCH_RESULT);
        return getElementText(driver, AdminCatalogProductsPageUI.NO_DATA_SEARCH_RESULT);
    }

    public void checkCheckboxByID(String checkboxId) {
        waitForElementClickable(driver, AdminCatalogProductsPageUI.SELECT_CHECKBOX_BY_ID, checkboxId);
        checkToDefaultCheckboxOrRadio(driver, AdminCatalogProductsPageUI.SELECT_CHECKBOX_BY_ID, checkboxId);
    }

    public void clickToLeftBarSubTypeByName(String leftBarSubType) {
        waitForElementClickable(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
        clickToElement(driver, AdminDashboardPageUI.LEFT_BAR_SUB_TYPE_BY_NAME, leftBarSubType);
    }

    public AdminProductInfoPageObject clickGoSkuButton() {
        waitForElementClickable(driver, AdminCatalogProductsPageUI.GO_SKU_BUTTON);
        clickToElement(driver, AdminCatalogProductsPageUI.GO_SKU_BUTTON);
        return PageGeneratorManager.getAdminProductInfoPage(driver);
    }
}

package com.nopcommerce.admin;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.AdminCatalogProductsPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminProductInfoPageObject;

public class Admin_01_Search extends BaseTest {
    private WebDriver driver;
    String productName;
    AdminLoginPageObject adminLoginPage;
    AdminDashboardPageObject adminDashboardPage;
    AdminCatalogProductsPageObject adminCatalogProductsPage;
    AdminProductInfoPageObject adminProductInfoPage;

    @Parameters({"envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local")  String envName, @Optional("dev")String serverName, @Optional("Chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444")String portNumber, @Optional("Windows")String osName, @Optional("10")String osVersion) {
        driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);

        productName = "Lenovo IdeaCentre 600 All-in-One PC";

        log.info("Pre-condition - Step 01 : Login to this system");
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.enterToEmail("admin@yourstore.com");
        adminLoginPage.enterToPassword("admin");
        adminDashboardPage = adminLoginPage.clickToLoginButton();
    }

    @Test
    public void Search_01_Product_Name(){
        log.info("Search_01 - Step 01 : Click to Catalog");
        adminDashboardPage.clickToLeftBarMainTypeByName("Catalog");

        log.info("Search_01 - Step 02 : Click to Products");
        adminDashboardPage.clickToLeftBarSubTypeByName("Products");
        adminCatalogProductsPage = PageGeneratorManager.getAdminCatalogProductsPage(driver);

        log.info("Search_01 - Step 03 : Enter to search Product name");
        adminCatalogProductsPage.enterToSearchByID("SearchProductName",productName);

        log.info("Search_01 - Step 04 : Click to Search Button");
        adminCatalogProductsPage.clickToSearchButton();

        log.info("Search_01 - Step 05 : Verify number product : 1");
        verifyEquals(adminCatalogProductsPage.verifyNumberProductDisplay(),1);

        log.info("Search_01 - Step 06 : Verify product data display");
        verifyEquals(adminCatalogProductsPage.verifyProductNameDisplay(),productName);
        verifyEquals(adminCatalogProductsPage.verifyProductSkuDisplay(),"LE_IC_600");
        verifyEquals(adminCatalogProductsPage.verifyProductPriceDisplay(),"500");
        verifyEquals(adminCatalogProductsPage.verifyProductQuantityDisplay(),"10000");
        verifyTrue(adminCatalogProductsPage.isProductPublished());
    }

    @Test
    public void Search_02_Product_Parent_Category_Uncheck(){
        log.info("Search_02 - Step 01 : Enter to search Product name");
        adminCatalogProductsPage.enterToSearchByID("SearchProductName",productName);

        log.info("Search_02 - Step 02 : Select category name");
        adminCatalogProductsPage.selectDropdownFieldByID("SearchCategoryId","Computers");

        log.info("Search_02 - Step 03 : Uncheck Search subcategories");
        adminCatalogProductsPage.uncheckCheckboxByID("SearchIncludeSubCategories");

        log.info("Search_02 - Step 04 : Click to Search Button");
        adminCatalogProductsPage.clickToSearchButton();

        log.info("Search_02 - Step 05 : Verify No data in result table");
        verifyEquals(adminCatalogProductsPage.verifyNoDataInResultTable(),"No data available in table");
    }

    @Test
    public void Search_03_Product_Parent_Category_Check(){
        log.info("Search_03 - Step 01 : Enter to search Product name");
        adminCatalogProductsPage.enterToSearchByID("SearchProductName",productName);

        log.info("Search_03 - Step 02 : Select category name");
        adminCatalogProductsPage.selectDropdownFieldByID("SearchCategoryId","Computers");

        log.info("Search_03 - Step 03 : Check Search subcategories");
        adminCatalogProductsPage.checkCheckboxByID("SearchIncludeSubCategories");

        log.info("Search_03 - Step 04 : Click to Search Button");
        adminCatalogProductsPage.clickToSearchButton();

        log.info("Search_03 - Step 05 : Verify number product : 1");
        verifyEquals(adminCatalogProductsPage.verifyNumberProductDisplay(),1);

        log.info("Search_03 - Step 06 : Verify product data display");
        verifyEquals(adminCatalogProductsPage.verifyProductNameDisplay(),productName);
        verifyEquals(adminCatalogProductsPage.verifyProductSkuDisplay(),"LE_IC_600");
        verifyEquals(adminCatalogProductsPage.verifyProductPriceDisplay(),"500");
        verifyEquals(adminCatalogProductsPage.verifyProductQuantityDisplay(),"10000");
        verifyTrue(adminCatalogProductsPage.isProductPublished());
    }

    @Test
    public void Search_04_Product_Child_Category(){
        log.info("Search_04 - Step 01 : Enter to search Product name");
        adminCatalogProductsPage.enterToSearchByID("SearchProductName",productName);

        log.info("Search_04 - Step 02 : Select category name");
        adminCatalogProductsPage.selectDropdownFieldByID("SearchCategoryId","Computers >> Desktops");

        log.info("Search_04 - Step 03 : Uncheck Search subcategories");
        adminCatalogProductsPage.uncheckCheckboxByID("SearchIncludeSubCategories");

        log.info("Search_04 - Step 04 : Click to Search Button");
        adminCatalogProductsPage.clickToSearchButton();

        log.info("Search_04 - Step 05 : Verify number product : 1");
        verifyEquals(adminCatalogProductsPage.verifyNumberProductDisplay(),1);

        log.info("Search_04 - Step 06 : Verify product data display");
        verifyEquals(adminCatalogProductsPage.verifyProductNameDisplay(),productName);
        verifyEquals(adminCatalogProductsPage.verifyProductSkuDisplay(),"LE_IC_600");
        verifyEquals(adminCatalogProductsPage.verifyProductPriceDisplay(),"500");
        verifyEquals(adminCatalogProductsPage.verifyProductQuantityDisplay(),"10000");
    }

    @Test
    public void Search_05_Product_Manufacturer(){
        log.info("Search_05 - Step 01 : Enter to search Product name");
        adminCatalogProductsPage.enterToSearchByID("SearchProductName",productName);

        log.info("Search_05 - Step 02 : Select category name");
        adminCatalogProductsPage.selectDropdownFieldByID("SearchCategoryId","All");

        log.info("Search_05 - Step 03 : Uncheck Search subcategories");
        adminCatalogProductsPage.uncheckCheckboxByID("SearchIncludeSubCategories");

        log.info("Search_05 - Step 04 : Select Manufacturer : Apple");
        adminCatalogProductsPage.selectDropdownFieldByID("SearchManufacturerId","Apple");

        log.info("Search_05 - Step 05 : Click to Search Button");
        adminCatalogProductsPage.clickToSearchButton();

        log.info("Search_05 - Step 06 : Verify No data in result table");
        verifyEquals(adminCatalogProductsPage.verifyNoDataInResultTable(),"No data available in table");
    }

    @Test
    public void Search_06_Product_Sku(){
        log.info("Search_06 - Step 01 : Click to Products");
        adminCatalogProductsPage.clickToLeftBarSubTypeByName("Products");

        log.info("Search_06 - Step 02 : Enter to search Product SKU");
        adminCatalogProductsPage.enterToSearchByID("GoDirectlyToSku","LE_IC_600");

        log.info("Search_06 - Step 03 : Click GO to search Product SKU");
        adminProductInfoPage = adminCatalogProductsPage.clickGoSkuButton();

        log.info("Search_06 - Step 04 : Verify Product details Page is display");
        verifyTrue(adminProductInfoPage.isProductDetailsPageDisplay("Edit product details"));

        log.info("Search_06 - Step 05 : Verify Product Name is display");
        verifyEquals(adminProductInfoPage.verifyProductNameDisplay(),productName);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }
}

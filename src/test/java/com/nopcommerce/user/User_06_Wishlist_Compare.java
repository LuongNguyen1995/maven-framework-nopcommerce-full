package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.*;
import ultilities.DataUtil;

public class User_06_Wishlist_Compare extends BaseTest {
    private WebDriver driver;
    String emailAddress,password, firstName, lastName;
    String productDesktopsName1, productDesktopsName2;
    String productDesktopsPrice1, productDesktopsPrice2;
    String productNotebooksName1, productNotebooksName2, productNotebooksName3,productNotebooksName4,productNotebooksName5;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    UserComputersPageObject userComputersPage;
    UserWishlistPageObject userWishlistPage;
    UserShopingCartPageObject userShopingCartPage;
    UserCompareProductPageObject userCompareProductPage;
    DataUtil fakeData;
    @Parameters({"envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local")  String envName, @Optional("dev")String serverName, @Optional("Chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444")String portNumber, @Optional("Windows")String osName, @Optional("10")String osVersion) {
        driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        fakeData = DataUtil.getData();
        firstName = fakeData.getFirstName();
        lastName = fakeData.getLastName();
        emailAddress = fakeData.getEmailAddress();
        password = fakeData.getPassword();
        productDesktopsName1 = "Lenovo IdeaCentre 600 All-in-One PC";
        productDesktopsName2 = "Build your own computer";

        productNotebooksName1 = "Apple MacBook Pro 13-inch";
        productNotebooksName2 = "Asus N551JK-XO076H Laptop";
        productNotebooksName3 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
        productNotebooksName4 = "HP Spectre XT Pro UltraBook";
        productNotebooksName5 = "Lenovo Thinkpad X1 Carbon Laptop";

        log.info("Pre-condition - Step 01 : Register new account");
        userRegisterPage = userHomePage.clickToRegisterLink();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(password);
        userRegisterPage.clickToRegisterButton();
        verifyEquals(userRegisterPage.registerSucessfulMessage(), "Your registration completed");
        userHomePage = userRegisterPage.clickBannerToHomePage();

        log.info("Pre-condition - Step 02 : Open Computer > Desktops");
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Desktops");

        log.info("Pre-condition - Step 03 : Click to product : Lenovo IdeaCentre 600 All-in-One PC");
        userComputersPage.clickToProductByName(productDesktopsName1);

    }

    @Test
    public void Wishlist_01_Add_Product(){
        log.info("Wishlist 01 - Step 01 : Click Add to Wishlist");
        userComputersPage.clickAddToWishlist();

        log.info("Wishlist 01 - Step 02 : Verify popup add to wishlist successful");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your wishlist");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Wishlist 01 - Step 03 : Click to Wishlist in top bar");
        userWishlistPage = userComputersPage.clickToWishListLinkOnTopBar();

        log.info("Wishlist 01 - Step 03 : Verify add product in Wishlist");
        verifyTrue(userWishlistPage.isProductAddedInWishlist(productDesktopsName1));

        log.info("Wishlist 01 - Step 04 : Click to Wishlist Url for sharing");
        userWishlistPage.clickToWishlistSharingUrl();

        log.info("Wishlist 01 - Step 05 : Verify wishlist title");
        verifyEquals(userWishlistPage.verifyWishListBannerForUser(),"Wishlist of "+firstName+" "+lastName);

        log.info("Wishlist 01 - Step 06 : Verify product add your wishlist");
        verifyTrue(userWishlistPage.isProductAddedInWishlist(productDesktopsName1));
    }

    @Test
    public void Wishlist_02_Add_To_Card(){
        log.info("Wishlist 02 - Step 01 : Back to Wishlist Link");
        userWishlistPage.backToWishlistPage();

        log.info("Wishlist 02 - Step 02 : Click to checkbox product add to card");
        userWishlistPage.clickToCheckboxProductNameInWishlist(productDesktopsName1);

        log.info("Wishlist 02 - Step 03 : Click to add to card");
        userWishlistPage.clickAddToCard();
        userShopingCartPage = PageGeneratorManager.getUserShopingCartPage(driver);

        log.info("Wishlist 02 - Step 04 : Verify display product in shoping cart");
        verifyTrue(userShopingCartPage.isDisplayedProductName(productDesktopsName1));

        log.info("Wishlist 02 - Step 05 : Click to Wish list Link");
        userWishlistPage = userShopingCartPage.clickToWishListLinkOnTopBar();

        log.info("Wishlist 02 - Step 06 : Verify Wishlist empty");
        verifyTrue(userWishlistPage.isWishListEmpty("The wishlist is empty!"));
    }

    @Test
    public void Wishlist_03_Remove_Product(){
        log.info("Wishlist 03 - Step 01 : Click to Computers > Notebook");
        userWishlistPage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Notebooks");

        log.info("Wishlist 03 - Step 02 : Click to product name detail");
        userComputersPage.clickToProductByName(productNotebooksName1);

        log.info("Wishlist 03 - Step 03 : Add to wishlist");
        userComputersPage.clickAddToWishlist();

        log.info("Wishlist 03 - Step 04 : Verify popup add to wishlist successful");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your wishlist");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Wishlist 03 - Step 05 : Click to Wishlist in top bar");
        userWishlistPage = userComputersPage.clickToWishListLinkOnTopBar();

        log.info("Wishlist 03 - Step 06 : Verify add product in Wishlist");
        verifyTrue(userWishlistPage.isProductAddedInWishlist(productDesktopsName1));

        log.info("Wishlist 03 - Step 07 : Click to remote product wishlist by Name");
        userWishlistPage.clickToRemoveProductByName(productNotebooksName1);

        log.info("Wishlist 03 - Step 08 : Verify Wishlist empty");
        verifyTrue(userWishlistPage.isWishListEmpty("The wishlist is empty!"));
    }

    @Test
    public void Wishlist_04_Add_Product_To_Compare(){
        log.info("Wishlist 04 - Step 01 : Click to Computers > Desktops");
        userWishlistPage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Desktops");

        log.info("Wishlist 04 - Step 02 : Save 2 product price");
        userComputersPage.saveProductPriceByName(productDesktopsName1, productDesktopsPrice1);
        userComputersPage.saveProductPriceByName(productDesktopsName2, productDesktopsPrice2);

        log.info("Wishlist 04 - Step 02 : Click to product 1 compare list");
        userComputersPage.clickToCompareProductByName(productDesktopsName1);
        verifyTrue(userComputersPage.isNotiCompareSuccessDisplayed("The product has been added to your product comparison"));
        userComputersPage.clickToCloseNotiCompareSuccess();

        log.info("Wishlist 04 - Step 03 : Click to product 2 compare list");
        userComputersPage.clickToCompareProductByName(productDesktopsName2);
        verifyTrue(userComputersPage.isNotiCompareSuccessDisplayed("The product has been added to your product comparison"));
        userCompareProductPage = userComputersPage.clickToViewDetailCompareProduct();

        log.info("Wishlist 04 - Step 04 : Verify info display on Compare product page");
        verifyTrue(userCompareProductPage.isTotalRemoveButtonProductDisplayed(2));
        verifyTrue(userCompareProductPage.isProductNameDisplayByName(productDesktopsName1));
        verifyTrue(userCompareProductPage.isProductNameDisplayByName(productDesktopsName2));
        verifyTrue(userCompareProductPage.isProductPriceDisplayByName(productDesktopsPrice1));
        verifyTrue(userCompareProductPage.isProductPriceDisplayByName(productDesktopsPrice2));
        verifyTrue(userCompareProductPage.isClearListDisplay());

        log.info("Wishlist 04 - Step 05 : Click to Clear List button");
        userCompareProductPage.clickToClearListButton();

        log.info("Wishlist 04 - Step 06 : Verify Clear compare data");
        verifyEquals(userCompareProductPage.verifyClearCompareProduct(),"You have no items to compare.");
    }

    @Test
    public void Wishlist_05_Recently_Viewed_Products(){
        log.info("Wishlist 05 - Step 01 : Click to Computers > Desktops");
        userWishlistPage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Notebooks");

        log.info("Wishlist 05 - Step 02 : Click to product name detail");
        userComputersPage.clickToProductByName(productNotebooksName1);
        userComputersPage.backToComputerPage();
        userComputersPage.clickToProductByName(productNotebooksName2);
        userComputersPage.backToComputerPage();
        userComputersPage.clickToProductByName(productNotebooksName3);
        userComputersPage.backToComputerPage();
        userComputersPage.clickToProductByName(productNotebooksName4);
        userComputersPage.backToComputerPage();
        userComputersPage.clickToProductByName(productNotebooksName5);
        userComputersPage.backToComputerPage();

        log.info("Wishlist 05 - Step 05 : Verify display 3 products latest");
        verifyTrue(userComputersPage.isRecentlyProductDisplayByName(productNotebooksName5));
        verifyTrue(userComputersPage.isRecentlyProductDisplayByName(productNotebooksName4));
        verifyTrue(userComputersPage.isRecentlyProductDisplayByName(productNotebooksName3));

    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }
}

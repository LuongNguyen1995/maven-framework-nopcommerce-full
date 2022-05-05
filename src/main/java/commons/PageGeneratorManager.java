package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.*;
import pageObjects.user.*;

public class PageGeneratorManager {
    public static PageGeneratorManager getPageGeneratorManager(){
        return new PageGeneratorManager();
    }


    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserMyAccountPageObject getUserMyAccountPage(WebDriver driver) {
        return new UserMyAccountPageObject(driver);
    }

    public static UserComputersPageObject getUserComputersPage(WebDriver driver) {
        return new UserComputersPageObject(driver);
    }

    public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
        return new UserSearchPageObject(driver);
    }

    public static UserWishlistPageObject getUserWishListPage(WebDriver driver) {
        return new UserWishlistPageObject(driver);
    }

    public static UserShopingCartPageObject getUserShopingCartPage(WebDriver driver) {
        return new UserShopingCartPageObject(driver);
    }

    public static UserCompareProductPageObject getUserCompareProductPage(WebDriver driver) {
        return new UserCompareProductPageObject(driver);
    }

    public static UserCheckoutPageObject getUserCheckoutPage(WebDriver driver) {
        return new UserCheckoutPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }

    public static AdminCatalogProductsPageObject getAdminCatalogProductsPage(WebDriver driver) {
        return new AdminCatalogProductsPageObject(driver);
    }

    public static AdminProductInfoPageObject getAdminProductInfoPage(WebDriver driver) {
        return new AdminProductInfoPageObject(driver);
    }

    public static AdminCustomersPageObject getAdminCustomersPage(WebDriver driver) {
        return new AdminCustomersPageObject(driver);
    }
}

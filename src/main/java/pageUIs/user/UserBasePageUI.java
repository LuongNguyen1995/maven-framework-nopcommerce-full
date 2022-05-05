package pageUIs.user;

public class UserBasePageUI {
    public static final String MENU_BY_PAGE_NAME = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
    public static final String MY_ACCOUNT_LINK = "xpath=//a[@class='ico-account']";
    public static final String LOG_OUT_LINK = "xpath=//a[@class='ico-logout']";
    public static final String WISHLIST_LINK = "xpath=//a[@class='ico-wishlist']";
    public static final String SHOPPING_CART_LINK = "xpath=//a[@class='ico-cart']";
    public static final String MINI_SHOPPING_CART_COUNT_ITEM = "xpath=//div[@class='mini-shopping-cart']//div[@class='count']";
    public static final String MINI_SHOPPING_PRODUCT_NAME = "xpath=//div[@class='mini-shopping-cart']//div[@class='name']";
    public static final String MINI_SHOPPING_PRODUCT_INFO = "xpath=//div[@class='mini-shopping-cart']//div[@class='attributes']";
    public static final String MINI_SHOPPING_PRODUCT_QUANTITY = "xpath=//div[@class='mini-shopping-cart']//div[@class='quantity']";
    public static final String MINI_SHOPPING_PRODUCT_PRICE = "xpath=//div[@class='mini-shopping-cart']//div[@class='totals']";
}

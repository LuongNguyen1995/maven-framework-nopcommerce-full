package pageUIs.user;

public class UserWishlistPageUI {
    public static final String PRODUCT_NAME_IN_WISHLIST ="xpath=//a[@class='product-name']";
    public static final String WISHLIST_SHARING_URL ="xpath=//span[@class='share-label']/following-sibling::a";
    public static final String WISHLIST_USER_TITLE ="xpath=//div[@class='page-title']/h1";
    public static final String WISHLIST_PRODUCT_CHECKBOX_BY_NAME ="xpath=//a[text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input";
    public static final String WISHLIST_ADD_TO_CARD_BUTTON ="xpath=//button[@name='addtocartbutton']";
    public static final String WISHLIST_NO_DATA_RESULT ="xpath=//div[@class='no-data']";
    public static final String WISHTLIST_REMOVE_PRODUCT_BUTTON_BY_NAME ="xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
}

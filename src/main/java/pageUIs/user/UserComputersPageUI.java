package pageUIs.user;

public class UserComputersPageUI {
    public static final String PRODUCT_NAME_BY_TEXT = "xpath=//div[@class='item-grid']//a[text()='%s']";
    public static final String ADD_YOUR_REVIEW_BUTTON = "xpath=//a[text()='Add your review']";
    public static final String REVIEW_TITLE_TEXTBOX = "xpath=//input[@id='AddProductReview_Title']";
    public static final String REVIEW_TEXT_TEXTBOX = "xpath=//textarea[@id='AddProductReview_ReviewText']";
    public static final String SUBMIT_REVIEW_BUTTON = "xpath=//button[text()='Submit review']";
    public static final String PRODUCT_REVIEW_SUCCESS_TEXT = "xpath=//div[contains(text(),'Product review is successfully added.')]";
    public static final String CATEGORIES_PRODUCT_BY_NAME = "xpath=//ul[@class='list']//a[contains(text(),'%s')]";
    public static final String NOTEBOOK_SORT_BY_DROPDOWN = "xpath=//select[@id='products-orderby']";
    public static final String NOTEBOOK_DISPLAY_PER_PAGE_DROPDOWN = "xpath=//select[@id='products-pagesize']";
    public static final String NOTEBOOK_RPODUCT_TITLE_SORT = "xpath=//h2[@class='product-title']/a";
    public static final String NOTEBOOK_PRODUCT_PRICE_SORT = "xpath=//span[@class='price actual-price']";
    public static final String NOTEBOOK_CURRENT_PAGE = "xpath=//li[@class='current-page']";
    public static final String NOTEBOOK_NEXT_PAGE_BUTTON = "xpath=//li[@class='next-page']";
    public static final String NOTEBOOK_PREVIOS_PAGE_BUTTON = "xpath=//li[@class='previous-page']";
    public static final String NOTEBOOK_PRODUCT_TITLE_DISPLAY_IN_PAGE = "xpath=//h2[@class='product-title']";
    public static final String PRODUCT_ADD_TO_WISHLIST_BUTTON = "xpath=//div[@class='product-name']/following-sibling::div[@class='overview-buttons']//button[text()='Add to wishlist']";
    public static final String TEXT_BAR_NOTI_SUCCESS = "xpath=//div[@class='bar-notification success']/p";
    public static final String CLOSE_POPUP_BAR_NOTI_SUCCESS = "xpath=//div[@class='bar-notification success']/span[@class='close']";
    public static final String COMPUTER_ADD_TO_COMPARE_BUTTON_BY_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//button[@title='Add to compare list']";
    public static final String COMPUTER_NOTI_COMPARE_SUCCESS = "xpath=//div[@class='bar-notification success']/p";
    public static final String COMPUTER_CLOSE_NOTI_COMPARE_SUCCESS_BUTTON = "xpath=//div[@class='bar-notification success']/span[@class='close']";
    public static final String COMPUTER_COMPARE_ON_NOTI_BUTTON = "xpath=//div[@class='bar-notification success']//a";
    public static final String COMPUTER_PRICE_BY_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//span";
    public static final String COMPUTER_RECENTLY_VIEW_PRODUCTS = "xpath=//strong[text()='Recently viewed products']/parent::div/following-sibling::div//a[@class='product-name']";
    public static final String COMPUTER_SELECT_PROCESSOR = "xpath=//select[@id='product_attribute_1']";
    public static final String COMPUTER_SELECT_RAM = "xpath=//select[@id='product_attribute_2']";
    public static final String COMPUTER_RADIOBOX_BY_NAME = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String COMPUTER_CHECKBOX_BY_NAME = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String COMPUTER_ADD_TO_CART_BUTTON = "xpath=//div[@class='overview']//button[text()='Add to cart']";
    public static final String COMPUTER_PRODUCT_PRICE_AFTER_CONFIG = "xpath=//div[@class='product-price']/span";
    public static final String COMPUTER_UPDATE_PRODUCT_BUTTON = "xpath=//button[text()='Update']";
    public static final String COMPUTER_QUANTITY_PRODUCT_UPDATE = "xpath=//input[@id='product_enteredQuantity_1']";
    public static final String COMPUTER_QUANTITY_PRODUCT = "xpath=//input[@id='product_enteredQuantity_3']";

}

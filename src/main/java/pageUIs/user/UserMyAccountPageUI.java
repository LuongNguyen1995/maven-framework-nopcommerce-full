package pageUIs.user;

public class UserMyAccountPageUI {
    public static final String FEMALE_CHECKBOX = "xpath=//input[@id='gender-female']";
    public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DATE_BY_NAME = "xpath=//select[@name='%s']";
    public static final String SAVE_BUTTON = "xpath=//button[@id='save-info-button']";
    public static final String LEFT_TAB_BY_NAME = "xpath=//div[@class='listbox']//a[contains(text(),'%s')]";
    public static final String ADDRESS_ADD_NEW_BUTTON = "xpath=//button[text()='Add new']";
    public static final String ADDRESS_SAVE_BUTTON = "xpath=//button[text()='Save']";
    public static final String ADDRESS_SELECT_FIELD_BY_ID = "xpath=//select[@id='%s']";
    public static final String ADDRESS_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String ADDRESS_EDITED_BY_CLASSSNAME = "xpath=//li[@class='name']";
    public static final String CHANGE_PASSWORD_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String CHANGE_PASSWORD_CHANGE_BUTTON = "xpath=//button[text()='Change password']";
    public static final String LOGOUT_LINK = "xpath=//a[@class='ico-logout']";
    public static final String BANNER_NOTI_PASSWORD_CHANGE = "xpath=//div[@class='bar-notification success']/span[@class='close']";


    public static final String REVIEW_TITLE = "xpath=//div[@class='review-title']/strong";
    public static final String REVIEW_TEXT_DETAIL = "xpath=//div[@class='review-text']";
    public static final String REVIEW_PRODUCT_NAME = "xpath=//span[@class='user']/a";
    public static final String ORDER_MY_ORDER_NUMBER = "xpath=//div[@class='section order-item']//strong";
    public static final String ORDER_DETAIL_BUTTON = "xpath=//strong[text()='Order Number: %s']/parent::div/following-sibling::div/button[text()='Details']";
    public static final String ORDER_OVER_VIEW_TEXT = "xpath=//div[@class='order-overview']";
    public static final String ORDER_PRODUCT_NAME_TEXT = "xpath=//td[@class='product']//a";
    public static final String ORDER_GIFT_WRAPPING_TEXT = "xpath=//div[@class='selected-checkout-attributes']";
    public static final String ORDER_TOTAL_INFOR_CART_TEXT_BY_NAME = "xpath=//label[text()='%s']/parent::td/following-sibling::td/span";

    public static final String CONFIRM_ORDER_PAYMENT_STATUS_TEXT = "xpath=//li[@class='payment-method-status']/span[@class='value']";
    public static final String CONFIRM_ORDER_SHIPPING_STATUS_TEXT = "xpath=//li[@class='shipping-status']/span[@class='value']";
    public static final String CONFIRM_ORDER_REORDER_BUTTON = "xpath=//button[text()='Re-order']";
}

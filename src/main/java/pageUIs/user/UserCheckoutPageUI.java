package pageUIs.user;

public class UserCheckoutPageUI {
    public static final String SELECT_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
    public static final String INPUT_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String CHECKBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String RADIOBOX_BY_NAME = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String CONTINUE_BUTTON_BY_ID = "xpath=//div[@id='%s']/button[text()='Continue']";
    public static final String CONFIRM_ORDER_CHECKOUT_BUTTON = "xpath=//div[@id='confirm-order-buttons-container']/button[text()='Confirm']";
    public static final String PAYMENT_INFO_DISPLAY = "xpath=//div[@class='section payment-info']";
    public static final String CONFIRM_ORDER_BILLING_ADDRESS_TEXT = "xpath=//strong[text()='Billing Address']/parent::div/following-sibling::ul";
    public static final String CONFIRM_ORDER_PAYMENT_METHOD_TEXT = "xpath=//li[@class='payment-method']/span[@class='value']";

    public static final String CONFIRM_ORDER_SHIPPING_ADDRESS_TEXT = "xpath=//strong[text()='Shipping Address']/parent::div/following-sibling::ul";
    public static final String CONFIRM_ORDER_SHIPPING_METHOD_TEXT = "xpath=//li[@class='shipping-method']/span[@class='value']";
    public static final String CONFIRM_ORDER_PRODUCT_INFO_BY_CLASS_NAME = "xpath=//td[@class='%s']/span";
    public static final String CONFIRM_ORDER_PRODUCT_NAME_DISPLAY = "xpath=//td[@class='product']/a";
    public static final String CART_TOTAL_DISPLAY_BY_CLASS_NAME = "xpath=//tr[@class='order-subtotal']//span";
    public static final String THANK_ORDER = "xpath=//div[@class='page-title']/h1[text()='Thank you']";
    public static final String ORDER_SUCCESS_TEXT = "xpath=//div[@class='page-body checkout-data']//div[@class='title']";
    public static final String ORDER_NUMBER = "xpath=//div[@class='order-number']/strong";


}

package pageUIs.admin;

public class AdminCatalogProductsPageUI {
    public static final String TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String SEARCH_BUTTON = "xpath=//button[@id='search-products']";
    public static final String CHECKBOX_PRODUCT_NUMBER = "xpath=//table[@id='products-grid']//input[@name='checkbox_products']";
    public static final String PRODUCT_NAME_DISPLAY = "xpath=//tr[@class='odd']/td[3]";
    public static final String PRODUCT_SKU_DISPLAY = "xpath=//tr[@class='odd']/td[4]";
    public static final String PRODUCT_PRICE_DISPLAY = "xpath=//tr[@class='odd']/td[5]";
    public static final String PRODUCT_QUANTITY_DISPLAY = "xpath=//tr[@class='odd']/td[6]";
    public static final String PRODUCT_PUBLISH = "xpath=//i[@nop-value='true']";
    public static final String SELECT_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
    public static final String SELECT_CHECKBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String NO_DATA_SEARCH_RESULT = "xpath=//td[@class='dataTables_empty']";
    public static final String GO_SKU_BUTTON = "xpath=//button[@id='go-to-product-by-sku']";
}

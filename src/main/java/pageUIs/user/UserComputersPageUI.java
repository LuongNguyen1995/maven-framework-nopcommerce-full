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
    public static final String NOTEBOOK_RPODUCT_PRICE_SORT = "xpath=//span[@class='price actual-price']";
}

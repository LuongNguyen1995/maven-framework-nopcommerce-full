package pageUIs.user;

public class UserSearchPageUI {
    public static final String SEARCH_RESULT_ERROR ="xpath=//div[@class='warning']";
    public static final String SEARCH_TEXTBOX = "xpath=//input[@id='small-searchterms']";
    public static final String SEARCH_BUTTON_IN_SEARCH_STORE = "xpath=//form[@id='small-search-box-form']//button[text()='Search']";
    public static final String SEARCH_NO_RESULT = "xpath=//div[@class='no-result']";
    public static final String PRODUCT_SEARCH_DISPLAYED = "xpath=//h2[@class='product-title']/a";
    public static final String SEARCH_CHECKBOX_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String DROPDOWN_FIELD_BY_NAME = "xpath=//label[text()='%s']/following-sibling::select";
    public static final String SEARCH_KEYWORD_TEXTBOX = "xpath=//label[text()='Search keyword:']/following-sibling::input";
    public static final String SEARCH_BUTTON_IN_SEARCH_KEYWORD = "xpath=//div[@class='search-input']//button[text()='Search']";
}

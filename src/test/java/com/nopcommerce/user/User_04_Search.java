package com.nopcommerce.user;


import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserSearchPageObject;
import ultilities.DataUtil;

public class User_04_Search extends BaseTest {
    private WebDriver driver;
    String emailAddress,password, firstName, lastName;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    UserSearchPageObject userSearchPage;

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
    }

    @Test
    public void Search_01_Empty_Data(){
        log.info("Search 01 - Step 01 : Click to button Search");
        userHomePage.clickToButtonSearch();

        log.info("Search 01 - Step 02 : Verify alert text");
        verifyEquals(userHomePage.verifyAlertTextEnterKeyword(),"Please enter some keyword");
        userHomePage.acceptSearchAlert();
    }

    @Test
    public void Search_02_Less_Than_3_Charactors(){
        log.info("Search 02 - Step 01 : Input to search less than 3 chars");
        userHomePage.enterToSearchTextBox("ok");

        log.info("Search 02 - Step 02 : Click to button Search");
        userHomePage.clickToButtonSearch();
        userSearchPage = PageGeneratorManager.getUserSearchPage(driver);

        log.info("Search 02 - Step 03 : Verify result search");
        verifyEquals(userSearchPage.verifySearchErrorResult(),"Search term minimum length is 3 characters");
    }

    @Test
    public void Search_03_Data_Not_Exist(){
        log.info("Search 03 - Step 01 : Input to Search data not exist");
        userSearchPage.enterToSearchTextBox("Macbook Pro 2050");

        log.info("Search 03 - Step 02 : Click to button Search");
        userSearchPage.clickToButtonSearch();

        log.info("Search 03 - Step 03 : Verify result search");
        verifyEquals(userSearchPage.verifySearchNoResult(),"No products were found that matched your criteria.");
    }

    @Test
    public void Search_04_Relative(){
        log.info("Search 04 - Step 01 : Input data relative to Search textbox");
        userSearchPage.enterToSearchTextBox("Lenovo");

        log.info("Search 04 - Step 02 : Click to button Search");
        userSearchPage.clickToButtonSearch();

        log.info("Search 04 - Step 03 : Verify only displayed 2 products");
        verifyEquals(userSearchPage.totalProductNameSearchDisplayed(),2);
        verifyTrue(userSearchPage.isProductNameSearchDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));
        verifyTrue(userSearchPage.isProductNameSearchDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void Search_05_Absolute(){
        log.info("Search 05 - Step 01 : Input data absolute to Search textbox");
        userSearchPage.enterToSearchTextBox("Thinkpad X1 Carbon");

        log.info("Search 05 - Step 02 : Click to button Search");
        userSearchPage.clickToButtonSearch();

        log.info("Search 05 - Step 03 : Verify only displayed 1 product");
        verifyEquals(userSearchPage.totalProductNameSearchDisplayed(),1);
        verifyTrue(userSearchPage.isProductNameSearchDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void Search_06_Parent_Categories(){
        log.info("Search 06 - Step 01 : Input data to Search textbox");
        userSearchPage.enterToSearchTextBoxField("Apple Macbook Pro");

        log.info("Search 06 - Step 02 : Click to Advanced search");
        userSearchPage.clickToCheckboxSearchByName("Advanced search");

        log.info("Search 06 - Step 03 : Select Advanced search Category : Computer");
        userSearchPage.selectDropdownFieldByName("Category:", "Computers");

        log.info("Search 06 - Step 04 : Click to Uncheck Automatically search sub categories");
        userSearchPage.clickToUnCheckboxSearchByName("Automatically search sub categories");

        log.info("Search 06 - Step 05 : Click to button Search");
        userSearchPage.clickToButtonSearchKeyword();

        log.info("Search 06 - Step 06 : Verify No product found");
        verifyEquals(userSearchPage.verifySearchNoResult(),"No products were found that matched your criteria.");
    }

    @Test
    public void Search_07_Sub_Categories(){
        log.info("Search 07 - Step 01 : Input data to Search textbox");
        userSearchPage.enterToSearchTextBoxField("Apple Macbook Pro");

        log.info("Search 07 - Step 02 : Click to Advanced search");
        userSearchPage.clickToCheckboxSearchByName("Advanced search");

        log.info("Search 07 - Step 03 : Select Advanced search Category : Computer");
        userSearchPage.selectDropdownFieldByName("Category:", "Computers");

        log.info("Search 07 - Step 04 : Click to Automatically search sub categories");
        userSearchPage.clickToCheckboxSearchByName("Automatically search sub categories");

        log.info("Search 07 - Step 05 : Click to button Search");
        userSearchPage.clickToButtonSearchKeyword();

        log.info("Search 07 - Step 06 : Verify only displayed 1 product");
        verifyEquals(userSearchPage.totalProductNameSearchDisplayed(),1);
        verifyTrue(userSearchPage.isProductNameSearchDisplayed("Apple MacBook Pro 13-inch"));
    }

    @Test
    public void Search_08_Incorrect_Manufacturer(){
        log.info("Search 08 - Step 01 : Input data to Search textbox");
        userSearchPage.enterToSearchTextBoxField("Apple Macbook Pro");

        log.info("Search 08 - Step 02 : Click to Advanced search");
        userSearchPage.clickToCheckboxSearchByName("Advanced search");

        log.info("Search 08 - Step 03 : Select Advanced search Category : Computer");
        userSearchPage.selectDropdownFieldByName("Category:", "Computers");

        log.info("Search 08 - Step 04 : Click to Automatically search sub categories");
        userSearchPage.clickToCheckboxSearchByName("Automatically search sub categories");

        log.info("Search 08 - Step 05 : Select Advanced search Manufacturer : HP");
        userSearchPage.selectDropdownFieldByName("Manufacturer:", "HP");

        log.info("Search 08 - Step 06 : Click to button Search");
        userSearchPage.clickToButtonSearchKeyword();

        log.info("Search 08 - Step 07 : Verify No product found");
        verifyEquals(userSearchPage.verifySearchNoResult(),"No products were found that matched your criteria.");
    }

    @Test
    public void Search_09_Correct_Manufacturer(){
        log.info("Search 09 - Step 01 : Input data to Search textbox");
        userSearchPage.enterToSearchTextBoxField("Apple Macbook Pro");

        log.info("Search 09 - Step 02 : Click to Advanced search");
        userSearchPage.clickToCheckboxSearchByName("Advanced search");

        log.info("Search 09 - Step 03 : Select Advanced search Category : Computer");
        userSearchPage.selectDropdownFieldByName("Category:", "Computers");

        log.info("Search 09 - Step 04 : Click to Automatically search sub categories");
        userSearchPage.clickToCheckboxSearchByName("Automatically search sub categories");

        log.info("Search 09 - Step 05 : Select Advanced search Manufacturer : ");
        userSearchPage.selectDropdownFieldByName("Manufacturer:", "Apple");

        log.info("Search 09 - Step 06 : Click to button Search");
        userSearchPage.clickToButtonSearchKeyword();

        log.info("Search 09 - Step 07 : Verify only displayed 1 product");
        verifyEquals(userSearchPage.totalProductNameSearchDisplayed(),1);
        verifyTrue(userSearchPage.isProductNameSearchDisplayed("Apple MacBook Pro 13-inch"));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }

}

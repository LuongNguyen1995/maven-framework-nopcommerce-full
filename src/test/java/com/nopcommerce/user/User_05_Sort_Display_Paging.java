package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.UserComputersPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import ultilities.DataUtil;

public class User_05_Sort_Display_Paging extends BaseTest {
    private WebDriver driver;
    String emailAddress,password, firstName, lastName;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    UserComputersPageObject userComputersPage;
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

        log.info("Pre-condition - Step 02 : Open Computer > Notebook");
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Notebooks");

    }

    @Test
    public void Sort_01_Name_A_To_Z(){
        log.info("Sort 01 - Step 01 : Select sort by Name : A to Z");
        userComputersPage.clickToSelectSortByText("Name: A to Z");

        log.info("Sort 01 - Step 02 : Verify sort by Name : A to Z");
        verifyTrue(userComputersPage.isProductNameSortAscending());

    }

    //@Test
    public void Sort_02_Name_Z_To_A(){
        //Manual is fail so should not scripting auto
        log.info("Sort 02 - Step 01 : Select sort by Name : Z to A");
        userComputersPage.clickToSelectSortByText("Name: Z to A");

        log.info("Sort 02 - Step 02 : Verify sort by Name : Z to A");
        verifyTrue(userComputersPage.isProductNameSortDescending());
    }

    @Test
    public void Sort_03_Price_Low_To_High(){
        log.info("Sort 03 - Step 01 : Select sort by Price : Low to High");
        userComputersPage.clickToSelectSortByText("Price: Low to High");

        log.info("Sort 03 - Step 02 : Verify sort by Price : Low to High");
        verifyTrue(userComputersPage.isProductPriceSortAscending());
    }

    @Test
    public void Sort_04_Price_High_To_Low(){
        log.info("Sort 04 - Step 01 : Select sort by Price: High to Low");
        userComputersPage.clickToSelectSortByText("Price: High to Low");

        log.info("Sort 04 - Step 02 : Verify sort by Price: High to Low");
        verifyTrue(userComputersPage.isProductPriceSortDescending());
    }

    @Test
    public void Sort_05_Display_3_Pages(){
        log.info("Display 05 - Step 01 : Select 3 products/page");
        userComputersPage.clickToSelectDisplayPerPage("3");

        log.info("Display 05 - Step 02 : Verify 3 products or less than 3 products");
        verifyTrue(userComputersPage.isTotalProductInThisPage(3));

        log.info("Display 05 - Step 03 : Verify button Next page when current page : 1");
        verifyTrue(userComputersPage.isNextButtonDisplayedWhenPageAt("1"));

        log.info("Display 05 - Step 04 : Verify button Previos page when current page : 2");
        verifyTrue(userComputersPage.isPreviosButtonDisplayedWhenPageAt("2"));

    }

    @Test
    public void Sort_06_Display_6_Pages(){
        log.info("Display 06 - Step 01 : Select 6 products/page");
        userComputersPage.clickToSelectDisplayPerPage("6");

        log.info("Display 06 - Step 02 : Verify 6 products or less than 6 products");
        verifyTrue(userComputersPage.isTotalProductInThisPage(6));

        log.info("Display 06 - Step 03 : Verify Not display Next button,previos button");
        verifyTrue(userComputersPage.isNotDisplayedNextButton());
        verifyTrue(userComputersPage.isNotDisplayedPreviosButton());

    }

    @Test
    public void Sort_07_Display_9_Pages(){
        log.info("Display 07 - Step 01 : Select 9 products/page");
        userComputersPage.clickToSelectDisplayPerPage("9");

        log.info("Display 06 - Step 02 : Verify 9 products or less than 9 products");
        verifyTrue(userComputersPage.isTotalProductInThisPage(9));

        log.info("Display 06 - Step 03 : Verify Not display Next button, previos button");
        verifyTrue(userComputersPage.isNotDisplayedNextButton());
        verifyTrue(userComputersPage.isNotDisplayedPreviosButton());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }
}

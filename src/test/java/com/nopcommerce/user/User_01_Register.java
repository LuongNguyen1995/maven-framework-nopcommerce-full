package com.nopcommerce.user;

import commons.BaseTest;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import ultilities.DataUtil;


public class User_01_Register extends BaseTest {

    private WebDriver driver;
    String firstName, lastName, password, emailAddress;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    DataUtil fakeData;
    @Parameters({"envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local")  String envName, @Optional("dev")String serverName, @Optional("Chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444")String portNumber, @Optional("Windows")String osName, @Optional("10")String osVersion){
        driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        fakeData = DataUtil.getData();
        firstName = fakeData.getFirstName();
        lastName = fakeData.getLastName();
        password = fakeData.getPassword();
        emailAddress = fakeData.getEmailAddress();

        log.info("Pre-condition - Step 01 : Open Register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

    }

    @Test
    public void Register_01_Empty_Data(){
        log.info("Register 01 - Step 01: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 01 - Step 02: Verify firstname error");
        verifyEquals(userRegisterPage.firstNameErrorText(), "First name is required.");

        log.info("Register 01 - Step 03: Verify lastname error");
        verifyEquals(userRegisterPage.lastNameErrorText(), "Last name is required.");

        log.info("Register 01 - Step 04: Verify email error");
        verifyEquals(userRegisterPage.emailErrorText(), "Email is required.");

        log.info("Register 01 - Step 05: Verify password error");
        verifyEquals(userRegisterPage.passwordErrorText(), "Password is required.");

        log.info("Register 01 - Step 06: Verify confirm password error");
        verifyEquals(userRegisterPage.confirmPasswordErrorText(), "Password is required.");

    }

    @Test
    public void Register_02_Invalid_Email(){
        log.info("Register 02 - Step 01: Enter to firstname");
        userRegisterPage.enterToFirstNameTextbox(firstName);

        log.info("Register 02 - Step 02: Enter to lastname");
        userRegisterPage.enterToLastNameTextbox(lastName);

        log.info("Register 02 - Step 03: Enter to email invalid");
        userRegisterPage.enterToEmailTextbox("123123123@123@123");

        log.info("Register 02 - Step 02: Enter to password");
        userRegisterPage.enterToPasswordTextbox(password);

        log.info("Register 02 - Step 02: Enter to confirm password");
        userRegisterPage.enterToConfirmPasswordTextbox(password);

        log.info("Register 02 - Step 01: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 02 - Step 04: Verify email error");
        verifyEquals(userRegisterPage.emailErrorText(), "Wrong email");
    }

    @Test
    public void Register_03_Password_Less_6_Charactor(){
        log.info("Register 03 - Step 01: Enter to firstname");
        userRegisterPage.enterToFirstNameTextbox(firstName);

        log.info("Register 03 - Step 02: Enter to lastname");
        userRegisterPage.enterToLastNameTextbox(lastName);

        log.info("Register 03 - Step 03: Enter to email");
        userRegisterPage.enterToEmailTextbox(emailAddress);

        log.info("Register 03 - Step 04: Enter to password less than 6 char");
        userRegisterPage.enterToPasswordTextbox("123");

        log.info("Register 03 - Step 05: Enter to confirm password");
        userRegisterPage.enterToConfirmPasswordTextbox("123");

        log.info("Register 03 - Step 06: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 03 - Step 07: Verify password error");
        verifyEquals(userRegisterPage.passwordErrorText(), "Password must meet the following rules:\n" + "must have at least 6 characters");

    }

    @Test
    public void Register_04_Password_Not_Match_Confirm_Password(){
        log.info("Register 04 - Step 01: Enter to firstname");
        userRegisterPage.enterToFirstNameTextbox(firstName);

        log.info("Register 04 - Step 02: Enter to lastname");
        userRegisterPage.enterToLastNameTextbox(lastName);

        log.info("Register 04 - Step 03: Enter to email");
        userRegisterPage.enterToEmailTextbox(emailAddress);

        log.info("Register 04 - Step 04: Enter to password");
        userRegisterPage.enterToPasswordTextbox(password);

        log.info("Register 04 - Step 05: Enter to confirm password not match password");
        userRegisterPage.enterToConfirmPasswordTextbox("123123");

        log.info("Register 04 - Step 06: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 04 - Step 04: Verify email error");
        verifyEquals(userRegisterPage.confirmPasswordErrorText(), "The password and confirmation password do not match.");
    }

    @Test
    public void Register_05_Sucess(){
        log.info("Register 05 - Step 01: Enter to firstname");
        userRegisterPage.enterToFirstNameTextbox(firstName);

        log.info("Register 05 - Step 02: Enter to lastname");
        userRegisterPage.enterToLastNameTextbox(lastName);

        log.info("Register 05 - Step 03: Enter to email");
        userRegisterPage.enterToEmailTextbox(emailAddress);

        log.info("Register 05 - Step 04: Enter to password");
        userRegisterPage.enterToPasswordTextbox(password);

        log.info("Register 05 - Step 05: Enter to confirm password");
        userRegisterPage.enterToConfirmPasswordTextbox(password);

        log.info("Register 05 - Step 06: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 05 - Step 07: Verify login sucessful");
        verifyEquals(userRegisterPage.registerSucessfulMessage(), "Your registration completed");
    }

    @Test
    public void Register_06_Exist_Email(){
        log.info("Register 06 - Step 01: Log out account");
        userHomePage = userRegisterPage.clickToLogoutLink();

        log.info("Register 06 - Step 02: Click to open register page");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Register 06 - Step 03: Enter to firstname");
        userRegisterPage.enterToFirstNameTextbox(firstName);

        log.info("Register 06 - Step 04: Enter to lastname");
        userRegisterPage.enterToLastNameTextbox(lastName);

        log.info("Register 06 - Step 05: Enter to email");
        userRegisterPage.enterToEmailTextbox(emailAddress);

        log.info("Register 06 - Step 06: Enter to password");
        userRegisterPage.enterToPasswordTextbox(password);

        log.info("Register 06 - Step 07: Enter to confirm password");
        userRegisterPage.enterToConfirmPasswordTextbox(password);

        log.info("Register 06 - Step 08: Click to register button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register 06 - Step 09: Verify error message exist email");
        verifyEquals(userRegisterPage.registerErrorMassageInMainPage(), "The specified email already exists");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }
}

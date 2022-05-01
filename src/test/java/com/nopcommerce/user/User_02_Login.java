package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;
import ultilities.DataUtil;

public class User_02_Login extends BaseTest {

    private WebDriver driver;
    String emailAddress,password, firstName, lastName;

    UserHomePageObject userHomePage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;

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
        userHomePage = userRegisterPage.clickToLogoutLink();

        log.info("Pre-condition - Step 02 : Open Login Page");
        userLoginPage = userHomePage.clickToLoginLink();
    }

    @Test
    public void Login_01_Empty_Data(){
        log.info("Login 01 - Step 01 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 01 - Step 02 : Verify Email error message display");
        verifyEquals(userLoginPage.emailErrorMessage(), "Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email(){
        log.info("Login 02 - Step 01 : Enter to invalid Email");
        userLoginPage.enterToEmailTextbox("123123123");

        log.info("Login 02 - Step 02 : Enter to password");
        userLoginPage.enterToPasswordTextbox(password);

        log.info("Login 02 - Step 03 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 02 - Step 04 : Verify Email error message display");
        verifyEquals(userLoginPage.emailErrorMessage(), "Wrong email");
    }

    @Test
    public void Login_03_Email_Not_Register(){
        log.info("Login 03 - Step 01 : Enter to invalid Email");
        userLoginPage.enterToEmailTextbox("123123123@gmail.com");

        log.info("Login 03 - Step 02 : Enter to password");
        userLoginPage.enterToPasswordTextbox(password);

        log.info("Login 03 - Step 03 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 03 - Step 04 : Verify error massage at main display");
        verifyEquals(userLoginPage.mainErrorMassage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
    }

    @Test
    public void Login_04_Password_Blank(){
        log.info("Login 04 - Step 01 : Enter to Email");
        userLoginPage.enterToEmailTextbox(emailAddress);

        log.info("Login 04 - Step 02 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 04 - Step 03 : Verify error massage at main display");
        verifyEquals(userLoginPage.mainErrorMassage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void Login_05_Password_Wrong(){
        log.info("Login 05 - Step 01 : Enter to Email");
        userLoginPage.enterToEmailTextbox(emailAddress);

        log.info("Login 05 - Step 02 : Enter to invalid password");
        userLoginPage.enterToPasswordTextbox("123123");

        log.info("Login 05 - Step 03 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 05 - Step 04 : Verify error massage at main display");
        verifyEquals(userLoginPage.mainErrorMassage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
    }

    @Test
    public void Login_06_Sucessful(){
        log.info("Login 06 - Step 01 : Enter to Email");
        userLoginPage.enterToEmailTextbox(emailAddress);

        log.info("Login 06 - Step 02 : Enter to password");
        userLoginPage.enterToPasswordTextbox(password);

        log.info("Login 06 - Step 03 : Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login 06 - Step 04 : Verify login sucessful - Title Signin not displayed");
        verifyTrue(userLoginPage.isTitleSignInDisplay());

    }


    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }

}

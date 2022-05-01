package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.*;
import ultilities.DataUtil;


public class User_03_My_Account extends BaseTest {

    private WebDriver driver;
    String emailAddress,password, firstName, lastName, newPassword;
    String editFirstName, gender, editLastName, editEmail, editCompanyName;
    String editDay, editMonth, editYear;
    String editCountry, editState, editCity, editAdd1, editAdd2, editZip, editPhone, editFax;
    String titleProductNameReview, titleReview, reviewTextDetail;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    UserMyAccountPageObject userMyAccountPage;
    UserLoginPageObject userLoginPage;
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
        newPassword = fakeData.getPassword();

        gender = "Female";
        editFirstName = "Automation";
        editLastName= "FC";
        editEmail ="automationfc.vn@gmail.com";
        editCompanyName = "Automation FC";
        editDay = "1"; editMonth = "January"; editYear = "1999";
        editCountry = "Viet Nam"; editState = "Other"; editCity = "Da Nang";
        editAdd1 = "123/04 Le Lai" ; editAdd2 = "234/05 Hai Phong"; editZip = "550000";
        editPhone = "0123456789"; editFax = "0987654321";
        titleProductNameReview = "Build your own computer";
        titleReview = "Product is very good.";
        reviewTextDetail = "Need improve color.";


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

        log.info("Pre-condition 02 - Step 02 : Click to My Account Link");
        userMyAccountPage = userHomePage.clickToMyAccountLink();
    }

    @Test
    public void MyAccount_01_Customer_Info(){
        log.info("MyAccount 01 - Step 01 : Edit Gender");
        userMyAccountPage.clickToGenderFemail();

        log.info("MyAccount 01 - Step 02 : Edit First name");
        userMyAccountPage.enterToEditTextboxByID("FirstName", editFirstName);

        log.info("MyAccount 01 - Step 03 : Edit Last name");
        userMyAccountPage.enterToEditTextboxByID("LastName", editLastName);

        log.info("MyAccount 01 - Step 03 : Edit Date of birth");
        userMyAccountPage.selectDateValueByName("DateOfBirthDay", editDay);
        userMyAccountPage.selectDateValueByName("DateOfBirthMonth", editMonth);
        userMyAccountPage.selectDateValueByName("DateOfBirthYear", editYear);

        log.info("MyAccount 01 - Step 04 : Edit Email");
        userMyAccountPage.enterToEditTextboxByID("Email", editEmail);

        log.info("MyAccount 01 - Step 05 : Edit Company name");
        userMyAccountPage.enterToEditTextboxByID("Company", editCompanyName);

        log.info("MyAccount 01 - Step 06 : Click save button");
        userMyAccountPage.clickToSaveInfoButton();

        log.info("MyAccount 01 - Step 07 : Verify text change in all field");
        verifyTrue(userMyAccountPage.verifyGenderFemaleIsSelected());
        verifyEquals(userMyAccountPage.verifyInfoChangeByID("FirstName"),editFirstName);
        verifyEquals(userMyAccountPage.verifyInfoChangeByID("LastName"),editLastName);
        verifyEquals(userMyAccountPage.verifyInfoDateChangeByName("DateOfBirthDay"),editDay);
        verifyEquals(userMyAccountPage.verifyInfoDateChangeByName("DateOfBirthMonth"),editMonth);
        verifyEquals(userMyAccountPage.verifyInfoDateChangeByName("DateOfBirthYear"),editYear);
        verifyEquals(userMyAccountPage.verifyInfoChangeByID("Email"),editEmail);
        verifyEquals(userMyAccountPage.verifyInfoChangeByID("Company"),editCompanyName);

    }

    @Test
    public void MyAccount_02_Address(){
        log.info("MyAccount 02 - Step 01 : Click to Address tab");
        userMyAccountPage.clickToLeftTabByName("Addresses");

        log.info("MyAccount 02 - Step 02 : Click to Add New button");
        userMyAccountPage.clickToAddNewButton();

        log.info("MyAccount 02 - Step 03 : Edit New Address in My Account");
        userMyAccountPage.editAddressByID("Address_FirstName", editFirstName);
        userMyAccountPage.editAddressByID("Address_LastName", editLastName);
        userMyAccountPage.editAddressByID("Address_Email", editEmail);
        userMyAccountPage.editAddressByID("Address_Company", editCompanyName);
        userMyAccountPage.selectAddressByID("Address_CountryId", editCountry);
        userMyAccountPage.selectAddressByID("Address_StateProvinceId", editState);
        userMyAccountPage.editAddressByID("Address_City", editCity);
        userMyAccountPage.editAddressByID("Address_Address1", editAdd1);
        userMyAccountPage.editAddressByID("Address_Address2", editAdd2);
        userMyAccountPage.editAddressByID("Address_ZipPostalCode", editZip);
        userMyAccountPage.editAddressByID("Address_PhoneNumber", editPhone);
        userMyAccountPage.editAddressByID("Address_FaxNumber", editFax);

        log.info("MyAccount 02 - Step 04 : Click to Save button in Adddress");
        userMyAccountPage.clickToSaveAddressButton();

        log.info("MyAccount 02 - Step 05 : Verify new address after edit");
        verifyEquals(userMyAccountPage.verifyAddressByClassName("name"), editEmail);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("phone"), editPhone);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("fax"), editFax);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("company"), editCompanyName);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("address1"), editAdd1);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("city-state-zip"), editCity+", "+editZip);
        verifyEquals(userMyAccountPage.verifyAddressByClassName("country"), editCountry);
    }

    @Test
    public void MyAccount_03_Change_Password(){
        log.info("MyAccount 03 - Step 01 : Click to Change password tab");
        userMyAccountPage.clickToLeftTabByName("Change password");

        log.info("MyAccount 03 - Step 02 : Enter textbox to Change password field");
        userMyAccountPage.enterToEditChangePasswordFieldByID("OldPassword", password);
        userMyAccountPage.enterToEditChangePasswordFieldByID("NewPassword", newPassword);
        userMyAccountPage.enterToEditChangePasswordFieldByID("ConfirmNewPassword", newPassword);

        log.info("MyAccount 03 - Step 03 : Click to Change password button");
        userMyAccountPage.clickToChangePasswordButton();

        log.info("MyAccount 03 - Step 04 : Click to Close banner password change");
        userMyAccountPage.clickToCloseBannerPasswordChanged();

        log.info("MyAccount 03 - Step 05 : Click to Logout Link");
        userHomePage = userMyAccountPage.clickToLogOutLink();

        log.info("MyAccount 03 - Step 06 : Click to Login Link");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("MyAccount 03 - Step 07 : Login with old password");
        userLoginPage.enterToEmailTextbox(emailAddress);
        userLoginPage.enterToPasswordTextbox(password);
        userLoginPage.clickToLoginButton();

        log.info("MyAccount 03 - Step 08 : Login with old password");
        verifyEquals(userLoginPage.mainErrorMassage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

        log.info("MyAccount 03 - Step 09 : Login with new password");
        userLoginPage.enterToEmailTextbox(emailAddress);
        userLoginPage.enterToPasswordTextbox(newPassword);
        userLoginPage.clickToLoginButton();

        log.info("MyAccount 03 - Step 10 : Verify Login successful");
        verifyTrue(userLoginPage.isTitleSignInDisplay());
    }

    @Test
    public void MyAccount_04_My_Product_Reviews(){
        log.info("MyAccount 04 - Step 01 : Return User Home Page");
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        log.info("MyAccount 04 - Step 02 : Open Computer > Desktop");
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Desktops");

        log.info("MyAccount 04 - Step 03 : Click to product by Name");
        userComputersPage.clickToProductByName("Build your own computer");

        log.info("MyAccount 04 - Step 04 : Click to add your review");
        userComputersPage.clickToAddYourReview();

        log.info("MyAccount 04 - Step 05 : Write your own review");
        userComputersPage.enterToReviewTitle(titleReview);
        userComputersPage.enterToReviewText(reviewTextDetail);
        userComputersPage.clickToSubmitReview();

        log.info("MyAccount 04 - Step 06 : Verify product review added");
        verifyTrue(userComputersPage.verifyProductReviewAdded());

        log.info("MyAccount 04 - Step 07 : Click to My Account");
        userComputersPage.clickToMyAccountLink();
        userMyAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);

        log.info("MyAccount 04 - Step 08 : Click to My product review");
        userMyAccountPage.clickToLeftTabByName("My product reviews");

        log.info("MyAccount 04 - Step 09 : Verify in My product review");
        verifyEquals(userMyAccountPage.verifyReviewTitleDisplay(), titleReview);
        verifyEquals(userMyAccountPage.verifyReviewTextDetailDisplay(), reviewTextDetail);
        verifyEquals(userMyAccountPage.verifyReviewProductNameDisplay(), titleProductNameReview);



    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }

}

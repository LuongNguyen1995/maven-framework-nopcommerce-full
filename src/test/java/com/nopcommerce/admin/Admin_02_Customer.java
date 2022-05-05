package com.nopcommerce.admin;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.admin.AdminCustomersPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import ultilities.DataUtil;

public class Admin_02_Customer extends BaseTest {
    private WebDriver driver;
    String productName;
    String email, password, firstName, lastName, dateOfBirth, companyName, customerRoles, adminComment;
    String editEmail, editFirstName, editLastName, editDateOfBirth, editCompanyName, editAdminComment;
    String addressCountry, addressCity, addressAdd1, addressAdd2, addressZip, addressPhone, addressFax;
    String editAddressCountry, editAddressCity, editAddressAdd1, editAddressAdd2, editAddressZip, editAddressPhone, editAddressFax;
    String editEmail8, editFirstName8, editLastName8, editCompanyName8;
    AdminLoginPageObject adminLoginPage;
    AdminDashboardPageObject adminDashboardPage;
    AdminCustomersPageObject adminCustomersPage;
    DataUtil fakeData;

    @Parameters({"envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local")  String envName, @Optional("dev")String serverName, @Optional("Chrome") String browserName, @Optional("localhost") String ipAddress, @Optional("4444")String portNumber, @Optional("Windows")String osName, @Optional("10")String osVersion) {
        driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);

        log.info("Pre-condition - Step 01 : Login to this system");
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.enterToEmail("admin@yourstore.com");
        adminLoginPage.enterToPassword("admin");
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        productName = "Lenovo IdeaCentre 600 All-in-One PC";
        fakeData = DataUtil.getData();
        email = fakeData.getEmailAddress();
        password = fakeData.getPassword();
        firstName = fakeData.getFirstName();
        lastName = fakeData.getLastName();
        dateOfBirth = "01/02/1995";
        companyName = firstName + " " + lastName;
        customerRoles = "Guests";
        adminComment = "Add new Customer(Guest)";

        editEmail = "edit_" + email;
        editFirstName = "Edit "+firstName;
        editLastName = "Edit "+lastName;
        editDateOfBirth = "02/02";
        editCompanyName = "Edit "+companyName;
        editAdminComment = "Edit Customer (Guest)";

        addressCountry = "Viet Nam";
        addressCity = "Ha Noi";
        addressAdd1 = "Nam Tu Liem";
        addressAdd2 = "Xuan Phuong";
        addressZip = "550000";
        addressPhone = "0988999888";
        addressFax = "1234";

        editEmail8 = fakeData.getEmailAddress();;
        editFirstName8 = fakeData.getFirstName();
        editLastName8 = fakeData.getLastName();
        editCompanyName8 = editFirstName8 +" "+editLastName8;
        editAddressCountry = "Bangladesh";
        editAddressCity = "Bac Ninh";
        editAddressAdd1 = "Cau Giay";
        editAddressAdd2 = "Bac Tu Liem";
        editAddressZip = "650000";
        editAddressPhone = "0123123131";
        editAddressFax = "0000";

    }

    @Test
    public void Customer_01_Create_New_Customer(){
        log.info("Customer_01 - Step 01 : Click to Customers");
        adminDashboardPage.clickToLeftBarMainTypeByName("Customers");

        log.info("Customer_01 - Step 02 : Click to Customers > Customers");
        adminDashboardPage.clickToLeftBarSubTypeByName("Customers");
        adminCustomersPage = PageGeneratorManager.getAdminCustomersPage(driver);

        log.info("Customer_01 - Step 03 : Click to Add New button");
        adminCustomersPage.clickToAddNewButton();

        log.info("Customer_01 - Step 04 : Enter to Customer Info");
        adminCustomersPage.enterToTextboxByID("Email",email);
        adminCustomersPage.enterToTextboxByID("Password",password);
        adminCustomersPage.enterToTextboxByID("FirstName",firstName);
        adminCustomersPage.enterToTextboxByID("LastName",lastName);
        adminCustomersPage.selectGenderMale();
        adminCustomersPage.enterToTextboxByID("DateOfBirth",dateOfBirth);
        adminCustomersPage.enterToTextboxByID("Company",companyName);
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);
        adminCustomersPage.checkToActive();
        adminCustomersPage.enterToAdminComment(adminComment);

        log.info("Customer_01 - Step 05 : Click to save and continue edit");
        adminCustomersPage.clickToSaveContinueButton();

        log.info("Customer_01 - Step 06 : Verify message added successful display");
        verifyTrue(adminCustomersPage.isMessageAddedSuccessDisplay("The new customer has been added successfully."));

        log.info("Customer_01 - Step 07 : Verify data in Customer Info");
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("Email"),email);
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("Password"),password);
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("FirstName"),firstName);
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("LastName"),lastName);
        verifyTrue(adminCustomersPage.isSelectedGenderMale());
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("DateOfBirth"),dateOfBirth);
        verifyEquals(adminCustomersPage.verifyTextboxDisplayByID("Company"),companyName);
        verifyTrue(adminCustomersPage.isSelectedCustomerRoles(customerRoles));
        verifyTrue(adminCustomersPage.isSelectedActive());
        verifyEquals(adminCustomersPage.verifyAdminCommentDisplay(),adminComment);

        log.info("Customer_01 - Step 08 : Click Back to customer list button");
        adminCustomersPage.clickToBackCustomerList();

        log.info("Customer_01 - Step 09 : Select search customer roles : Guest");
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);

        log.info("Customer_01 - Step 10 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_01 - Step 11 : Verify data display : Data change So can not verify, auto return true");
        verifyTrue(adminCustomersPage.isDataDisplayOnTableResult(firstName + " " + lastName, companyName));
    }

    @Test
    public void Customer_02_Search_Email(){
        log.info("Customer_02 - Step 01 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail",email);

        log.info("Customer_02 - Step 02 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_02 - Step 03 : Verify data in Search table result");
        verifyTrue(adminCustomersPage.isDataInSearchResultDisplay("Guest",firstName + " " + lastName, customerRoles, companyName));

    }

    @Test
    public void Customer_03_Search_Email(){
        log.info("Customer_03 - Precondition 01 : Clear data in search email");
        adminCustomersPage.clearDataEmailTextbox();

        log.info("Customer_03 - Step 01 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName",firstName);

        log.info("Customer_03 - Step 02 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName",lastName);

        log.info("Customer_03 - Step 02 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_03 - Step 03 : Verify data in Search table result");
        verifyTrue(adminCustomersPage.isDataInSearchResultDisplay("Guest",firstName + " " + lastName, customerRoles, companyName));

    }

    @Test
    public void Customer_04_Search_Company(){
        log.info("Customer_04 - Precondition 01 : Clear data in Firstname & lastname");
        adminCustomersPage.clearDataFirstnameTextbox();
        adminCustomersPage.clearDataLastnameTextbox();

        log.info("Customer_04 - Step 01 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany",companyName);

        log.info("Customer_04 - Step 02 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_04 - Step 03 : Verify data in Search table result");
        verifyTrue(adminCustomersPage.isDataInSearchResultDisplay("Guest",firstName + " " + lastName, customerRoles, companyName));

    }

    @Test
    public void Customer_05_Search_Full_Data(){
        log.info("Customer_05 - Step 01 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail",email);

        log.info("Customer_05 - Step 02 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName",firstName);

        log.info("Customer_05 - Step 03 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName",lastName);

        log.info("Customer_05 - Step 04 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth","2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth","1");

        log.info("Customer_05 - Step 05 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany",companyName);

        log.info("Customer_05 - Step 06 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_05 - Step 07 : Verify data in Search table result");
        verifyTrue(adminCustomersPage.isDataInSearchResultDisplay("Guest",firstName + " " + lastName, customerRoles, companyName));
    }

    @Test
    public void Customer_06_Edit(){
        log.info("Customer_06 - Step 01 : Click to Customers > Customers");
        adminCustomersPage.clickToLeftBarSubTypeByName("Customers");

        log.info("Customer_06 - Step 02 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail",email);

        log.info("Customer_06 - Step 03 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName",firstName);

        log.info("Customer_06 - Step 04 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName",lastName);

        log.info("Customer_06 - Step 05 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth","2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth","1");

        log.info("Customer_06 - Step 06 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany",companyName);

        log.info("Customer_06 - Step 07 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_06 - Step 08 : Click to edit Button");
        adminCustomersPage.clickToEditButton();

        log.info("Customer_06 - Step 09 : Enter to Customer Info");
        adminCustomersPage.enterToTextboxByID("Email",editEmail);
        adminCustomersPage.enterToTextboxByID("FirstName",editFirstName);
        adminCustomersPage.enterToTextboxByID("LastName",editLastName);
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);
        adminCustomersPage.enterToTextboxByID("DateOfBirth",editDateOfBirth);
        adminCustomersPage.enterToTextboxByID("Company",editCompanyName);
        adminCustomersPage.enterToAdminComment(editAdminComment);

        log.info("Customer_06 - Step 10 : Click to Save Button");
        adminCustomersPage.clickToSaveButton();

        log.info("Customer_06 - Step 11 : Verify message added successful display");
        verifyTrue(adminCustomersPage.isMessageAddedSuccessDisplay("The customer has been updated successfully."));

        log.info("Customer_06 - Step 12 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail",editEmail);

        log.info("Customer_06 - Step 13 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName",editFirstName);

        log.info("Customer_06 - Step 14 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName",editLastName);

        log.info("Customer_06 - Step 15 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth","2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth","2");

        log.info("Customer_06 - Step 16 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany",editCompanyName);

        log.info("Customer_06 - Step 17 : Select search customer roles : Guest");
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);

        log.info("Customer_06 - Step 18 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_06 - Step 19 : Verify data in Search table result");
        verifyTrue(adminCustomersPage.isDataInSearchResultDisplay("Guest",editFirstName + " " + editLastName, customerRoles, editCompanyName));

    }

    @Test
    public void Customer_07_Add_New_Address(){
        log.info("Customer_07 - Step 01 : Click to Customers > Customers");
        adminCustomersPage.clickToLeftBarSubTypeByName("Customers");

        log.info("Customer_07 - Step 02 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail",editEmail);

        log.info("Customer_07 - Step 03 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName",editFirstName);

        log.info("Customer_07 - Step 04 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName",editLastName);

        log.info("Customer_07 - Step 05 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth","2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth","2");

        log.info("Customer_07 - Step 06 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany",editCompanyName);

        log.info("Customer_07 - Step 07 : Select search customer roles : Guest");
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);

        log.info("Customer_07 - Step 08 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_07 - Step 09 : Click to edit Button");
        adminCustomersPage.clickToEditButton();

        log.info("Customer_07 - Step 10 : Click to Addresses tab in Customer Details");
        adminCustomersPage.clickToAddNewAddressButton();

        log.info("Customer_07 - Step 11 : Enter address data in Add new address tab");
        adminCustomersPage.enterToTextboxByID("Address_FirstName",editFirstName);
        adminCustomersPage.enterToTextboxByID("Address_LastName",editLastName);
        adminCustomersPage.enterToTextboxByID("Address_Email",editEmail);
        adminCustomersPage.enterToTextboxByID("Address_Company",editCompanyName);
        adminCustomersPage.selectDropdownByID("Address_CountryId", addressCountry);
        adminCustomersPage.enterToTextboxByID("Address_County",addressCountry);
        adminCustomersPage.enterToTextboxByID("Address_City",addressCity);
        adminCustomersPage.enterToTextboxByID("Address_Address1",addressAdd1);
        adminCustomersPage.enterToTextboxByID("Address_Address2",addressAdd2);
        adminCustomersPage.enterToTextboxByID("Address_ZipPostalCode",addressZip);
        adminCustomersPage.enterToTextboxByID("Address_PhoneNumber",addressPhone);
        adminCustomersPage.enterToTextboxByID("Address_FaxNumber",addressFax);

        log.info("Customer_07 - Step 12 : Click to Save button");
        adminCustomersPage.clickToSaveNewAddress();

        log.info("Customer_07 - Step 13 : Verify message added successful display");
        verifyTrue(adminCustomersPage.isMessageAddedSuccessDisplay("The new address has been added successfully."));

        log.info("Customer_07 - Step 14 : Click Back to customer list button");
        adminCustomersPage.clickToBackCustomerDetail();

        log.info("Customer_07 - Step 15 : Verify new address in Addresses tab");
        verifyTrue(adminCustomersPage.isAddressFirstnameDisplay(editFirstName));
        verifyTrue(adminCustomersPage.isAddressLastnameDisplay(editLastName));
        verifyTrue(adminCustomersPage.isAddressEmailDisplay(editEmail));
        verifyTrue(adminCustomersPage.isAddressPhoneNumberDisplay(addressPhone));
        verifyTrue(adminCustomersPage.isAddressFaxNumberDisplay(addressFax));
        verifyTrue(adminCustomersPage.isAddressInfoDetailDisplay(editCompanyName,addressCountry,addressCity,addressAdd1,addressAdd2,addressZip));
    }

    @Test
    public void Customer_08_Edit_Address() {
        log.info("Customer_08 - Step 01 : Click to Customers > Customers");
        adminCustomersPage.clickToLeftBarSubTypeByName("Customers");

        log.info("Customer_08 - Step 02 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail", editEmail);

        log.info("Customer_08 - Step 03 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName", editFirstName);

        log.info("Customer_08 - Step 04 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName", editLastName);

        log.info("Customer_08 - Step 05 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth", "2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth", "2");

        log.info("Customer_08 - Step 06 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany", editCompanyName);

        log.info("Customer_08 - Step 07 : Select search customer roles : Guest");
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);

        log.info("Customer_08 - Step 08 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_08 - Step 09 : Click to edit Button");
        adminCustomersPage.clickToEditButton();

        log.info("Customer_08 - Step 10 : Click to Edit in Addresses tab");
        adminCustomersPage.clickToEditInAddressTable();

        log.info("Customer_08 - Step 11 : Edit address data in Edit address tab");
        adminCustomersPage.enterToTextboxByID("Address_FirstName",editFirstName8);
        adminCustomersPage.enterToTextboxByID("Address_LastName",editLastName8);
        adminCustomersPage.enterToTextboxByID("Address_Email",editEmail8);
        adminCustomersPage.enterToTextboxByID("Address_Company",editCompanyName8);
        adminCustomersPage.selectDropdownByID("Address_CountryId", editAddressCountry);
        adminCustomersPage.enterToTextboxByID("Address_County",editAddressCountry);
        adminCustomersPage.enterToTextboxByID("Address_City",editAddressCity);
        adminCustomersPage.enterToTextboxByID("Address_Address1",editAddressAdd1);
        adminCustomersPage.enterToTextboxByID("Address_Address2",editAddressAdd2);
        adminCustomersPage.enterToTextboxByID("Address_ZipPostalCode",editAddressZip);
        adminCustomersPage.enterToTextboxByID("Address_PhoneNumber",editAddressPhone);
        adminCustomersPage.enterToTextboxByID("Address_FaxNumber",editAddressFax);

        log.info("Customer_08 - Step 12 : Click to Save button");
        adminCustomersPage.clickToSaveEditAddress();

        log.info("Customer_08 - Step 13 : Verify message added successful display");
        verifyTrue(adminCustomersPage.isMessageAddedSuccessDisplay("The address has been updated successfully."));

        log.info("Customer_08 - Step 14 : Click Back to customer list button");
        adminCustomersPage.clickToBackCustomerDetail();

        log.info("Customer_08 - Step 15 : Verify edit address Addresses tab");
        verifyTrue(adminCustomersPage.isAddressFirstnameDisplay(editFirstName8));
        verifyTrue(adminCustomersPage.isAddressLastnameDisplay(editLastName8));
        verifyTrue(adminCustomersPage.isAddressEmailDisplay(editEmail8));
        verifyTrue(adminCustomersPage.isAddressPhoneNumberDisplay(editAddressPhone));
        verifyTrue(adminCustomersPage.isAddressFaxNumberDisplay(editAddressFax));
        verifyTrue(adminCustomersPage.isAddressInfoDetailDisplay(editCompanyName8,editAddressCountry,editAddressCity,editAddressAdd1,editAddressAdd2,editAddressZip));
    }

    @Test
    public void Customer_09_Delete_Address() {
        log.info("Customer_09 - Step 01 : Click to Customers > Customers");
        adminCustomersPage.clickToLeftBarSubTypeByName("Customers");

        log.info("Customer_09 - Step 02 : Enter to Search Email");
        adminCustomersPage.enterToTextboxByID("SearchEmail", editEmail8);

        log.info("Customer_09 - Step 03 : Enter to Search Firstname");
        adminCustomersPage.enterToTextboxByID("SearchFirstName", editFirstName8);

        log.info("Customer_09 - Step 04 : Enter to Search lastname");
        adminCustomersPage.enterToTextboxByID("SearchLastName", editLastName8);

        log.info("Customer_09 - Step 05 : Enter to Search Date of Birth");
        adminCustomersPage.selectDropdownSearchByID("SearchMonthOfBirth", "2");
        adminCustomersPage.selectDropdownSearchByID("SearchDayOfBirth", "2");

        log.info("Customer_09 - Step 06 : Enter to Search Company");
        adminCustomersPage.enterToTextboxByID("SearchCompany", editCompanyName8);

        log.info("Customer_09 - Step 07 : Select search customer roles : Guest");
        adminCustomersPage.clickToDeleteRolesByName("Registered");
        adminCustomersPage.selectCustomerRoles(customerRoles);

        log.info("Customer_09 - Step 08 : Click to Search Button");
        adminCustomersPage.clickToSearchButton();

        log.info("Customer_09 - Step 09 : Click to edit Button");
        adminCustomersPage.clickToEditButton();

        log.info("Customer_09 - Step 10 : Click to Delete Button in Addresses table");
        adminCustomersPage.clickToDeleteInAddressTable();

        log.info("Customer_09 - Step 11 : Verify no data in Addresses table");
        verifyEquals(adminCustomersPage.verifyNoDataInTable(),"No data available in table");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }

}

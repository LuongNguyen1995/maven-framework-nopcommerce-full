package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.*;
import ultilities.DataUtil;

public class User_07_Order extends BaseTest {
    private WebDriver driver;
    String emailAddress,password, firstName, lastName;
    String firstNameBilling, lastNameBilling, countryBilling, cityBilling, add1Billing, zipBilling, phoneBilling;
    String firstNameShipping, lastNameShipping, countryShipping, cityShipping, add1Shipping, zipShipping, phoneShipping;
    String firstNameBilling7, lastNameBilling7, countryBilling7, cityBilling7, add1Billing7, zipBilling7, phoneBilling7;
    String firstNameShipping7, lastNameShipping7, countryShipping7, cityShipping7, add1Shipping7, zipShipping7, phoneShipping7;
    String orderNumber5, orderNumber6, orderNumber7;
    String cardholderName, cardNumber, cardCode;
    String productName, productPrice;
    String productName4, productName5, productName6;
    String productPriceUpdate;
    String computerProcessor, computerRam, computerHdd, computerOs, computerSoftwareOffice, computerSoftwareAcrobat, computerSoftwareCommander;
    String editComputerProcessor, editComputerRam, editComputerHdd,editComputerOs;
    UserHomePageObject userHomePage;
    UserRegisterPageObject userRegisterPage;
    UserComputersPageObject userComputersPage;
    UserShopingCartPageObject userShopingCartPage;
    UserCheckoutPageObject userCheckoutPage;
    UserMyAccountPageObject userMyAccountPage;
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
        productName = "Build your own computer";
        computerProcessor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
        computerRam = "8GB [+$60.00]";
        computerHdd = "400 GB [+$100.00]";
        computerOs = "Vista Premium [+$60.00]";
        computerSoftwareOffice = "Microsoft Office [+$50.00]";
        computerSoftwareAcrobat = "Acrobat Reader [+$10.00]";
        computerSoftwareCommander = "Total Commander [+$5.00]";

        editComputerProcessor = "2.2 GHz Intel Pentium Dual-Core E2200";
        editComputerRam = "4GB [+$20.00]";
        editComputerHdd = "320 GB";
        editComputerOs = "Vista Home [+$50.00]";

        productName4 = "Lenovo IdeaCentre 600 All-in-One PC";
        productName5 = "Apple MacBook Pro 13-inch";
        productName6 = "Asus N551JK-XO076H Laptop";

        cardholderName = "Nguyen Luong";
        cardNumber = "378282246310005";
        cardCode = "184";

        firstNameBilling = fakeData.getFirstName(); firstNameShipping = fakeData.getFirstName();
        lastNameBilling = fakeData.getLastName(); lastNameShipping = fakeData.getLastName();
        countryBilling = "Bolivia"; countryShipping = "France";
        cityBilling = "Ha Noi"; cityShipping = "Tu Son";
        add1Billing = "7 Ho Thien Quang"; add1Shipping = "1 Pham Van Bach";
        zipBilling = "550000"; zipShipping = "110000";
        phoneBilling = "0987654321"; phoneShipping = "0123456789";

        firstNameBilling7 = fakeData.getFirstName(); firstNameShipping7 = fakeData.getFirstName();
        lastNameBilling7 = fakeData.getLastName(); lastNameShipping7 = fakeData.getLastName();
        countryBilling7 = "Viet Nam"; countryShipping7 = "Uruguay";
        cityBilling7 = "Ho Chi Minh"; cityShipping7 = "Nghe An";
        add1Billing7 = "1 Ngo Tu Do"; add1Shipping7 = "24 Xuan Phuong";
        zipBilling7 = "123000"; zipShipping7 = "145321";
        phoneBilling7 = "0999999999"; phoneShipping7 = "0111111111";

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

        log.info("Pre-condition - Step 02 : Open Computer > Desktops");
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Desktops");

        log.info("Pre-condition - Step 03 : Click to product Build yout own computer");
        userComputersPage.clickToProductByName(productName);
    }

    @Test
    public void Order_01_Add_Product_To_Cart(){
        log.info("Order 01 - Step 01 : Select config for Computer");
        userComputersPage.selectProcessorByName(computerProcessor);
        userComputersPage.selectRamByName(computerRam);
        userComputersPage.selectRadioboxByName(computerHdd);
        userComputersPage.selectRadioboxByName(computerOs);
        userComputersPage.selectCheckboxByName(computerSoftwareOffice);
        userComputersPage.selectCheckboxByName(computerSoftwareAcrobat);
        userComputersPage.selectCheckboxByName(computerSoftwareCommander);

        log.info("Order 01 - Step 02 : Get product price after select config");
        productPrice = userComputersPage.getPriceProductAfterConfig();

        log.info("Order 01 - Step 03 : Click Add to Cart");
        userComputersPage.clickAddToCart();

        log.info("Order 01 - Step 04 : Verify display add to cart success");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your shopping cart");

        log.info("Order 01 - Step 05 : Click to close noti add to cart success");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Order 01 - Step 06 : Verify Shoping cart + 1");
        verifyEquals(userComputersPage.verifyAddProductInShoppingCart(), "Shopping cart (1)");

        log.info("Order 01 - Step 07 : Hover mouse to Shopping Cart");
        userComputersPage.hoverMouseToShoppingCartLink();

        log.info("Order 01 - Step 08 : Verify data display on Shopping Cart popup");
        verifyEquals(userComputersPage.verifyTotalItemInCart(), "There are 1 item(s) in your cart.");
        verifyEquals(userComputersPage.verifyProductNameAddInCart(),productName);
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerProcessor));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerRam));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerRam));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerOs));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerSoftwareOffice));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerSoftwareAcrobat));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerSoftwareCommander));
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart(productPrice));
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart("Quantity: 1"));
        verifyTrue(userComputersPage.isDisplayProductSubTotalAddInCart(productPrice));
    }

    @Test
    public void Order_02_Edit_Product_In_Shopping_Cart(){
        log.info("Order 02 - Step 01 : Click to Shopping Cart");
        userShopingCartPage = userComputersPage.clickToShoppingCartLink();

        log.info("Order 02 - Step 02 : Click to Edit Product");
        userComputersPage = userShopingCartPage.clickToEditProduct();

        log.info("Order 02 - Step 03 : Edit product with new info");
        userComputersPage.selectProcessorByName(editComputerProcessor);
        userComputersPage.selectRamByName(editComputerRam);
        userComputersPage.selectRadioboxByName(editComputerHdd);
        userComputersPage.selectRadioboxByName(editComputerOs);
        userComputersPage.selectCheckboxByName(computerSoftwareOffice);
        userComputersPage.selectUncheckboxByName(computerSoftwareAcrobat);
        userComputersPage.selectUncheckboxByName(computerSoftwareCommander);
        userComputersPage.updateQuantityProduct("2");

        log.info("Order 02 - Step 04 : Verify new price after update");
        productPriceUpdate = userComputersPage.getPriceProductAfterConfig();
        verifyEquals(productPriceUpdate, "$1,320.00");

        log.info("Order 02 - Step 05 : Click to Update button");
        userComputersPage.clickToUpdateButton();

        log.info("Order 02 - Step 06 : Verify display add to cart success");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your shopping cart");

        log.info("Order 02 - Step 07 : Click to close noti add to cart success");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Order 02 - Step 07 : Hover mouse to Shopping Cart");
        userComputersPage.hoverMouseToShoppingCartLink();

        log.info("Order 02 - Step 08 : Verify data display on Shopping Cart popup");
        verifyEquals(userComputersPage.verifyTotalItemInCart(), "There are 1 item(s) in your card.");
        verifyEquals(userComputersPage.verifyProductNameAddInCart(),productName);
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(editComputerRam));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(editComputerHdd));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(editComputerOs));
        verifyTrue(userComputersPage.isDisplayProductInfoAddInCart(computerSoftwareOffice));
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart("$1,320.00"));
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart("Quantity: 2"));
        verifyTrue(userComputersPage.isDisplayProductSubTotalAddInCart("$2,640.00"));
    }

    @Test
    public void Order_03_Remove_From_Cart(){
        log.info("Order 03 - Step 01 : Click to Shopping Cart");
        userShopingCartPage = userComputersPage.clickToShoppingCartLink();

        log.info("Order 03 - Step 02 : Click to remove Product");
        userShopingCartPage.clickToRemoveProduct();

        log.info("Order 03 - Step 03 : Verify : Your Shopping Cart is empty!");
        verifyEquals(userShopingCartPage.verifyShopingCartEmptyText(),"Your Shopping Cart is empty!");

        log.info("Order 03 - Step 04 : Verify : Product name is not display");
        verifyTrue(userShopingCartPage.isProductNameNotDisplay());
    }

    @Test
    public void Order_04_Update_Shopping_Cart(){
        log.info("Order 04 - Step 01 : Open Computer > Desktops");
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Desktops");

        log.info("Order 04 - Step 02 : Click to product name");
        userComputersPage.clickToProductByName(productName4);

        log.info("Order 04 - Step 03 : Change product number is 5");
        userComputersPage.clickToUpdateQuantity("5");

        log.info("Order 04 - Step 04 : Click add to cart");
        userComputersPage.clickAddToCart();

        log.info("Order 04 - Step 05 : Verify display add to cart success");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your shopping cart");

        log.info("Order 04 - Step 06 : Click to close noti add to cart success");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Order 04 - Step 07 : Hover mouse to Shopping Cart");
        userComputersPage.hoverMouseToShoppingCartLink();

        log.info("Order 04 - Step 08 : Verify data display on Shopping Cart popup");
        verifyEquals(userComputersPage.verifyTotalItemInCart(), "There are 5 item(s) in your card.");
        verifyEquals(userComputersPage.verifyProductNameAddInCart(),productName4);
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart("$500.00"));
        verifyTrue(userComputersPage.isDisplayProductQuantityAddInCart("Quantity: 5"));
        verifyTrue(userComputersPage.isDisplayProductSubTotalAddInCart("$2,500.00"));


    }

    @Test
    public void Order_05_Checkout_Order_Payment_Money(){
        log.info("Pre-condition 05 - Step 01 : Click to Shopping Cart");
        userShopingCartPage = userComputersPage.clickToShoppingCartLink();

        log.info("Pre-condition 05 - Step 02 : Click to remove Product");
        userShopingCartPage.clickToRemoveProduct();

        log.info("Pre-condition 05 - Step 03 : Verify : Your Shopping Cart is empty!");
        verifyEquals(userShopingCartPage.verifyShopingCartEmptyText(),"Your Shopping Cart is empty!");

        log.info("Pre-condition 05 - Step 04 : Verify : Product name is not display");
        verifyTrue(userShopingCartPage.isProductNameNotDisplay());

        log.info("Order 05 - Step 01 : Open Computer > Notebooks");
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Notebooks");

        log.info("Order 05 - Step 02 : Click to product name");
        userComputersPage.clickToProductByName(productName5);

        log.info("Order 05 - Step 03 : Click add to cart");
        userComputersPage.clickAddToCart();

        log.info("Order 05 - Step 04 : Verify display add to cart success");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your shopping cart");

        log.info("Order 05 - Step 05 : Click to close noti add to cart success");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Order 05 - Step 06 : Click to Shopping Cart");
        userShopingCartPage = userComputersPage.clickToShoppingCartLink();

        log.info("Order 05 - Step 07 : Click to accept terms");
        userShopingCartPage.clickToAcceptTermsCheckbox();

        log.info("Order 05 - Step 08 : Click to Checkout");
        userCheckoutPage = userShopingCartPage.clickToCheckoutButton();

        log.info("Order 05 - Step 09 : Fill data to Billing address");
        userCheckoutPage.uncheckToCheckboxByID("ShipToSameAddress");
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_FirstName", firstNameBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_LastName", lastNameBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("BillingNewAddress_CountryId", countryBilling);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_City",cityBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Address1",add1Billing);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_ZipPostalCode",zipBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_PhoneNumber",phoneBilling);

        log.info("Order 05 - Step 10 : Click Continue button By ID Billing address");
        userCheckoutPage.clickButtonContinueByID("billing-buttons-container");

        log.info("Order 05 - Step 11 : Select other address in Shipping address");
        userCheckoutPage.selectDropdownByID("shipping-address-select", "New Address");

        log.info("Order 05 - Step 12 : Fill data to Shipping address");
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_FirstName", firstNameShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_LastName", lastNameShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("ShippingNewAddress_CountryId", countryShipping);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_City",cityShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Address1",add1Shipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_ZipPostalCode",zipShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_PhoneNumber",phoneShipping);

        log.info("Order 05 - Step 13 : Click Continue button By ID Shipping address");
        userCheckoutPage.clickButtonContinueByID("shipping-buttons-container");

        log.info("Order 05 - Step 14 : Select Shipping method by Name");
        userCheckoutPage.selectRadioboxByName("Ground ($0.00)");

        log.info("Order 05 - Step 15 : Click Continue button By ID Shipping Method");
        userCheckoutPage.clickButtonContinueByID("shipping-method-buttons-container");

        log.info("Order 05 - Step 16 : Select Payment method by Name");
        userCheckoutPage.selectRadioboxByName("Check / Money Order");

        log.info("Order 05 - Step 17 : Click Continue button By ID Payment Method");
        userCheckoutPage.clickButtonContinueByID("payment-method-buttons-container");

        log.info("Order 05 - Step 18 : Verify Payment information display");
        verifyTrue(userCheckoutPage.isPaymentInfoDisplay());

        log.info("Order 05 - Step 19 : Click Continue button By ID Payment Info");
        userCheckoutPage.clickButtonContinueByID("payment-info-buttons-container");

        log.info("Order 05 - Step 20 : Verify Confirm order");
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(phoneBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(add1Billing));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(cityBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(zipBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(countryBilling));
        verifyTrue(userCheckoutPage.isPaymentMethodDisplay("Check / Money Order"));

        verifyTrue(userCheckoutPage.isShippingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(phoneBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(add1Billing));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(cityBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(zipBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(countryBilling));
        verifyTrue(userCheckoutPage.isShippingMethodDisplay("Ground"));

        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("sku"),"AP_MBP_13");
        verifyEquals(userCheckoutPage.verifyInfoProductName(),productName5);
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("unit-price"),"$1,800.00");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("quantity"),"2");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("subtotal"),"$3,600.00");

        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-subtotal"),"$3,600.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("shipping-cost"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("tax-value"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-total"),"$3,600.00");

        log.info("Order 05 - Step 21 : Click Continue button By ID Confirm order");
        userCheckoutPage.clickButtonConfirmOrderInCheckout();

        log.info("Order 05 - Step 22 : Verify order successful");
        verifyTrue(userCheckoutPage.isThankYouDisplay());
        verifyEquals(userCheckoutPage.verifyOrderSuccessfulDisplayText(),"Your order has been successfully processed!");

        log.info("Order 05 - Step 23 : Get order number");
        orderNumber5 = userCheckoutPage.getOrderNumber();

        log.info("Order 05 - Step 24 : Click to My Account Link");
        userMyAccountPage = userCheckoutPage.clickToMyAccountLink();

        log.info("Order 05 - Step 25 : Click to My Account > Order");
        userMyAccountPage.clickToLeftTabByName("Orders");

        log.info("Order 05 - Step 26 : Verify order number in MyAccount > Order");
        verifyEquals(userMyAccountPage.verifyOrderNumber(), orderNumber5);

        log.info("Order 05 - Step 26 : Click to view detail order");
        userMyAccountPage.clickToViewDetailOrder(orderNumber5);

        log.info("Order 05 - Step 27 : Verify infor order in Order Information detail");
        verifyTrue(userMyAccountPage.isOrderDetailDisplay(orderNumber5));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("Pending"));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("$3,600.00"));

        verifyTrue(userMyAccountPage.isBillingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(phoneBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(add1Billing));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(cityBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(zipBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(countryBilling));
        verifyTrue(userMyAccountPage.isPaymentMethodDisplay("Check / Money Order"));
        verifyTrue(userMyAccountPage.isPaymentStatusDisplay("Pending"));

        verifyTrue(userMyAccountPage.isShippingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(phoneBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(add1Billing));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(cityBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(zipBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(countryBilling));
        verifyTrue(userMyAccountPage.isShippingMethodDisplay("Ground"));
        verifyTrue(userMyAccountPage.isShippingStatusDisplay("Not yet shipped"));

        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("sku"),"AP_MBP_13");
        verifyEquals(userMyAccountPage.verifyProductNameOrder(),productName5);
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("unit-price"),"$1,800.00");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("quantity"),"2");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("total"),"$3,600.00");

        verifyEquals(userMyAccountPage.verifyGiftWrapping(),"Gift wrapping: No");

        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Sub-Total:"),"$3,600.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Shipping:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Tax:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Order Total:"),"$3,600.00");

    }

    @Test
    public void Order_06_Checkout_Order_Payment_Visa(){
        log.info("Order 06 - Step 01 : Open Computer > Notebooks");
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.openMenuPageByText(driver, "Computers");
        userComputersPage = PageGeneratorManager.getUserComputersPage(driver);
        userComputersPage.clickToCategoriesByName("Notebooks");

        log.info("Order 06 - Step 02 : Click to product name");
        userComputersPage.clickToProductByName(productName6);

        log.info("Order 06 - Step 03 : Click add to cart");
        userComputersPage.clickAddToCart();

        log.info("Order 06 - Step 04 : Verify display add to cart success");
        verifyEquals(userComputersPage.verifyNotiAddSucces(),"The product has been added to your shopping cart");

        log.info("Order 06 - Step 05 : Click to close noti add to cart success");
        userComputersPage.clickToCloseNotiAddSuccess();

        log.info("Order 06 - Step 06 : Click to Shopping Cart");
        userShopingCartPage = userComputersPage.clickToShoppingCartLink();

        log.info("Order 06 - Step 07 : Click to accept terms");
        userShopingCartPage.clickToAcceptTermsCheckbox();

        log.info("Order 06 - Step 08 : Click to Checkout");
        userCheckoutPage = userShopingCartPage.clickToCheckoutButton();

        log.info("Order 06 - Step 09 : Select other address in Billing address");
        userCheckoutPage.selectDropdownByID("billing-address-select", "New Address");

        log.info("Order 06 - Step 10 : Fill data to Billing address");
        userCheckoutPage.uncheckToCheckboxByID("ShipToSameAddress");
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_FirstName", firstNameBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_LastName", lastNameBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("BillingNewAddress_CountryId", countryBilling);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_City",cityBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Address1",add1Billing);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_ZipPostalCode",zipBilling);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_PhoneNumber",phoneBilling);

        log.info("Order 06 - Step 11 : Click Continue button By ID Billing address");
        userCheckoutPage.clickButtonContinueByID("billing-buttons-container");

        log.info("Order 06 - Step 12 : Select other address in Shipping address");
        userCheckoutPage.selectDropdownByID("shipping-address-select", "New Address");

        log.info("Order 06 - Step 13 : Fill data to Shipping address");
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_FirstName", firstNameShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_LastName", lastNameShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("ShippingNewAddress_CountryId", countryShipping);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_City",cityShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Address1",add1Shipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_ZipPostalCode",zipShipping);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_PhoneNumber",phoneShipping);

        log.info("Order 06 - Step 14 : Click Continue button By ID Shipping address");
        userCheckoutPage.clickButtonContinueByID("shipping-buttons-container");

        log.info("Order 06 - Step 15 : Select Shipping method by Name");
        userCheckoutPage.selectRadioboxByName("Ground ($0.00)");

        log.info("Order 06 - Step 16 : Click Continue button By ID Shipping Method");
        userCheckoutPage.clickButtonContinueByID("shipping-method-buttons-container");

        log.info("Order 06 - Step 17 : Select Payment method by Name");
        userCheckoutPage.selectRadioboxByName("Credit Card");

        log.info("Order 06 - Step 18 : Click Continue button By ID Payment Method");
        userCheckoutPage.clickButtonContinueByID("payment-method-buttons-container");

        log.info("Order 06 - Step 19 : Fill data in Payment Information");
        userCheckoutPage.selectDropdownByID("CreditCardType", "Visa");
        userCheckoutPage.enterToTextboxByID("CardholderName", cardholderName);
        userCheckoutPage.enterToTextboxByID("CardNumber", cardNumber);
        userCheckoutPage.selectDropdownByID("ExpireMonth", "03");
        userCheckoutPage.selectDropdownByID("ExpireYear", "2025");
        userCheckoutPage.enterToTextboxByID("CardCode", cardCode);

        log.info("Order 06 - Step 20 : Click Continue button By ID Shipping Method");
        userCheckoutPage.clickButtonContinueByID("payment-info-buttons-container");

        log.info("Order 06 - Step 21 : Verify Confirm order");
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(phoneBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(add1Billing));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(cityBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(zipBilling));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(countryBilling));
        verifyTrue(userCheckoutPage.isPaymentMethodDisplay("Credit Card"));


        verifyTrue(userCheckoutPage.isShippingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(phoneBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(add1Billing));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(cityBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(zipBilling));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(countryBilling));
        verifyTrue(userCheckoutPage.isShippingMethodDisplay("Ground"));

        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("sku"),"AS_551_LP");
        verifyEquals(userCheckoutPage.verifyInfoProductName(),productName6);
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("unit-price"),"$1,500.00");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("quantity"),"1");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("subtotal"),"$1,500.00");

        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-subtotal"),"$1,500.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("shipping-cost"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("tax-value"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-total"),"$1,500.00");

        log.info("Order 06 - Step 22 : Wait for some second to order other pruduct");
        userMyAccountPage.waitToOrderOtherProduct();

        log.info("Order 06 - Step 23 : Click Continue button By ID Confirm order");
        userCheckoutPage.clickButtonConfirmOrderInCheckout();

        log.info("Order 06 - Step 24 : Verify order successful");
        verifyTrue(userCheckoutPage.isThankYouDisplay());
        verifyEquals(userCheckoutPage.verifyOrderSuccessfulDisplayText(),"Your order has been successfully processed!");

        log.info("Order 06 - Step 25 : Get order number");
        orderNumber6 = userCheckoutPage.getOrderNumber();

        log.info("Order 06 - Step 26 : Click to My Account Link");
        userMyAccountPage = userCheckoutPage.clickToMyAccountLink();

        log.info("Order 06 - Step 27 : Click to My Account > Order");
        userMyAccountPage.clickToLeftTabByName("Orders");

        log.info("Order 06 - Step 28 : Verify order number in MyAccount > Order");
        verifyEquals(userMyAccountPage.verifyOrderNumber(), orderNumber6);

        log.info("Order 06 - Step 29 : Click to view detail order");
        userMyAccountPage.clickToViewDetailOrder(orderNumber6);

        log.info("Order 06 - Step 30 : Verify infor order in Order Information detail");
        verifyTrue(userMyAccountPage.isOrderDetailDisplay(orderNumber6));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("Pending"));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("$1,500.00"));

        verifyTrue(userMyAccountPage.isBillingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(phoneBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(add1Billing));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(cityBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(zipBilling));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(countryBilling));
        verifyTrue(userMyAccountPage.isPaymentMethodDisplay("Credit Card"));
        verifyTrue(userMyAccountPage.isPaymentStatusDisplay("Pending"));

        verifyTrue(userMyAccountPage.isShippingAddressDisplay(firstNameBilling+" "+lastNameBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(phoneBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(add1Billing));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(cityBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(zipBilling));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(countryBilling));
        verifyTrue(userMyAccountPage.isShippingMethodDisplay("Ground"));
        verifyTrue(userMyAccountPage.isShippingStatusDisplay("Not yet shipped"));

        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("sku"),"AS_551_LP");
        verifyEquals(userMyAccountPage.verifyProductNameOrder(),productName6);
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("unit-price"),"$1,500.00");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("quantity"),"1");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("total"),"$1,500.00");

        verifyEquals(userMyAccountPage.verifyGiftWrapping(),"Gift wrapping: No");

        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Sub-Total:"),"$1,500.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Shipping:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Tax:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Order Total:"),"$1,500.00");

    }

    @Test
    public void Order_07_Re_Order(){
        log.info("Order 07 - Step 01 : Click to re-order");
        userShopingCartPage = userMyAccountPage.clickToReorderButton();

        log.info("Order 07 - Step 02 : Update quantity : 10");
        userShopingCartPage.enterQuantityProduct("10");

        log.info("Order 07 - Step 03 : Click to accept terms");
        userShopingCartPage.clickToAcceptTermsCheckbox();

        log.info("Order 07 - Step 04 : Click to Checkout");
        userCheckoutPage = userShopingCartPage.clickToCheckoutButton();

        log.info("Order 07 - Step 05 : Select other address in Billing address");
        userCheckoutPage.selectDropdownByID("billing-address-select", "New Address");

        log.info("Order 07 - Step 06 : Fill data to Billing address");
        userCheckoutPage.uncheckToCheckboxByID("ShipToSameAddress");
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_FirstName", firstNameBilling7);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_LastName", lastNameBilling7);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("BillingNewAddress_CountryId", countryBilling7);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_City",cityBilling7);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_Address1",add1Billing7);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_ZipPostalCode",zipBilling7);
        userCheckoutPage.enterToTextboxByID("BillingNewAddress_PhoneNumber",phoneBilling7);

        log.info("Order 07 - Step 07 : Click Continue button By ID Billing address");
        userCheckoutPage.clickButtonContinueByID("billing-buttons-container");

        log.info("Order 07 - Step 08 : Select other address in Shipping address");
        userCheckoutPage.selectDropdownByID("shipping-address-select", "New Address");

        log.info("Order 07 - Step 09 : Fill data to Shipping address");
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_FirstName", firstNameShipping7);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_LastName", lastNameShipping7);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Email", emailAddress);
        userCheckoutPage.selectDropdownByID("ShippingNewAddress_CountryId", countryShipping7);
        userCheckoutPage.waitForLoadingCountryDone();
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_City",cityShipping7);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_Address1",add1Shipping7);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_ZipPostalCode",zipShipping7);
        userCheckoutPage.enterToTextboxByID("ShippingNewAddress_PhoneNumber",phoneShipping7);

        log.info("Order 07 - Step 10 : Click Continue button By ID Shipping address");
        userCheckoutPage.clickButtonContinueByID("shipping-buttons-container");

        log.info("Order 07 - Step 11 : Select Shipping method by Name");
        userCheckoutPage.selectRadioboxByName("Next Day Air ($0.00)");

        log.info("Order 07 - Step 12 : Click Continue button By ID Shipping Method");
        userCheckoutPage.clickButtonContinueByID("shipping-method-buttons-container");

        log.info("Order 07 - Step 13 : Select Payment method by Name");
        userCheckoutPage.selectRadioboxByName("Credit Card");

        log.info("Order 07 - Step 14 : Click Continue button By ID Payment Method");
        userCheckoutPage.clickButtonContinueByID("payment-method-buttons-container");

        log.info("Order 07 - Step 15 : Fill data in Payment Information");
        userCheckoutPage.selectDropdownByID("CreditCardType", "Visa");
        userCheckoutPage.enterToTextboxByID("CardholderName", cardholderName);
        userCheckoutPage.enterToTextboxByID("CardNumber", cardNumber);
        userCheckoutPage.selectDropdownByID("ExpireMonth", "03");
        userCheckoutPage.selectDropdownByID("ExpireYear", "2025");
        userCheckoutPage.enterToTextboxByID("CardCode", cardCode);

        log.info("Order 07 - Step 16 : Click Continue button By ID Shipping Method");
        userCheckoutPage.clickButtonContinueByID("payment-info-buttons-container");

        log.info("Order 07 - Step 17 : Verify Confirm order");
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(firstNameBilling7+" "+lastNameBilling7));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(phoneBilling7));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(add1Billing7));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(cityBilling7));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(zipBilling7));
        verifyTrue(userCheckoutPage.isBillingAddressDisplay(countryBilling7));
        verifyTrue(userCheckoutPage.isPaymentMethodDisplay("Credit Card"));


        verifyTrue(userCheckoutPage.isShippingAddressDisplay(firstNameBilling7+" "+lastNameBilling7));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(phoneBilling7));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(add1Billing7));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(cityBilling7));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(zipBilling7));
        verifyTrue(userCheckoutPage.isShippingAddressDisplay(countryBilling7));
        verifyTrue(userCheckoutPage.isShippingMethodDisplay("Next Day Air"));

        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("sku"),"AS_551_LP");
        verifyEquals(userCheckoutPage.verifyInfoProductName(),productName6);
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("unit-price"),"$15,000.00");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("quantity"),"10");
        verifyEquals(userCheckoutPage.verifyInfoProductByClassName("subtotal"),"$15,000.00");

        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-subtotal"),"$15,000.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("shipping-cost"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("tax-value"),"$0.00");
        verifyEquals(userCheckoutPage.verifyInfoInCartTotalByClassName("order-total"),"$15,000.00");

        log.info("Order 07 - Step 18 : Wait for some second to order other pruduct");
        userMyAccountPage.waitToOrderOtherProduct();

        log.info("Order 07 - Step 19 : Click Continue button By ID Confirm order");
        userCheckoutPage.clickButtonConfirmOrderInCheckout();

        log.info("Order 07 - Step 20 : Verify order successful");
        verifyTrue(userCheckoutPage.isThankYouDisplay());
        verifyEquals(userCheckoutPage.verifyOrderSuccessfulDisplayText(),"Your order has been successfully processed!");

        log.info("Order 07 - Step 21 : Get order number");
        orderNumber7 = userCheckoutPage.getOrderNumber();

        log.info("Order 07 - Step 22 : Click to My Account Link");
        userMyAccountPage = userCheckoutPage.clickToMyAccountLink();

        log.info("Order 07 - Step 23 : Click to My Account > Order");
        userMyAccountPage.clickToLeftTabByName("Orders");

        log.info("Order 07 - Step 24 : Verify order number in MyAccount > Order");
        verifyEquals(userMyAccountPage.verifyOrderNumber(), orderNumber7);

        log.info("Order 07 - Step 25 : Click to view detail order");
        userMyAccountPage.clickToViewDetailOrder(orderNumber7);

        log.info("Order 07 - Step 26 : Verify infor order in Order Information detail");
        verifyTrue(userMyAccountPage.isOrderDetailDisplay(orderNumber7));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("Pending"));
        verifyTrue(userMyAccountPage.isOrderDetailDisplay("$15,000.00"));

        verifyTrue(userMyAccountPage.isBillingAddressDisplay(firstNameBilling7+" "+lastNameBilling7));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(phoneBilling7));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(add1Billing7));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(cityBilling7));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(zipBilling7));
        verifyTrue(userMyAccountPage.isBillingAddressDisplay(countryBilling7));
        verifyTrue(userMyAccountPage.isPaymentMethodDisplay("Credit Card"));
        verifyTrue(userMyAccountPage.isPaymentStatusDisplay("Pending"));

        verifyTrue(userMyAccountPage.isShippingAddressDisplay(firstNameBilling7+" "+lastNameBilling7));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(emailAddress));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(phoneBilling7));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(add1Billing7));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(cityBilling7));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(zipBilling7));
        verifyTrue(userMyAccountPage.isShippingAddressDisplay(countryBilling7));
        verifyTrue(userMyAccountPage.isShippingMethodDisplay("Next Day Air"));
        verifyTrue(userMyAccountPage.isShippingStatusDisplay("Not yet shipped"));

        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("sku"),"AS_551_LP");
        verifyEquals(userMyAccountPage.verifyProductNameOrder(),productName6);
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("unit-price"),"$15,000.00");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("quantity"),"10");
        verifyEquals(userMyAccountPage.verifyProductDetailOrderByClassName("total"),"$15,000.00");

        verifyEquals(userMyAccountPage.verifyGiftWrapping(),"Gift wrapping: No");

        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Sub-Total:"),"$15,000.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Shipping:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Tax:"),"$0.00");
        verifyEquals(userMyAccountPage.verifyInfoInCartTotalByName("Order Total:"),"$15,000.00");

    }


    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserAndDriver("local");
    }
}

package commons;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.user.UserBasePageUI;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
    private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver, String pageUrl) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    public void sleepInSecond (long second) {
        try {
            Thread.sleep( second * 1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver,String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(windowTitle)) {
                break;
            }
        }
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("xpath=")  || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            by = By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") ) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else {
            throw new RuntimeException("Locator type is not support!");
        }

        return by;
    }

    public String getDynamicXpath(String locatorType, String... dynamicValue) {
        if(locatorType.startsWith("xpath=")  || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValue);
        }
        return locatorType;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(WebDriver driver, String locatorType){
        highlightElement(driver, locatorType);
        if (driver.toString().contains("internet explorer")) {
            clickToElementByJS(driver, locatorType);
            sleepInSecond(2);
        } else {
            getWebElement(driver, locatorType).click();
        }
    }


    public void highlightElement(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        String originalStyle = element.getAttribute("style");
        String highlightStyle = "border: 3px solid red; border-style: dashed;";
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", highlightStyle);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue){
        highlightElement(driver, locatorType, dynamicValue);
        if (driver.toString().contains("internet explorer")) {
            clickToElementByJS(driver, locatorType, dynamicValue);
            sleepInSecond(2);
        } else {
            getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
        }
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
        highlightElement(driver, locatorType);
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValue) {
        highlightElement(driver, locatorType, dynamicValue);
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        element.clear();
        element.sendKeys(textValue);
    }

    public String getElementText(WebDriver driver, String locatorType){
        return getWebElement(driver, locatorType).getText().trim();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValue){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getText().trim();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem){
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValue){
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        select.selectByVisibleText(textItem);
    }

    public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType){
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValue){
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locatorType){
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdownList(WebDriver driver,String parentXpath, String childXpath, String expectedTextItem) {
        clickToElement(driver,parentXpath);
        sleepInSecond(2);
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(2);
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName){
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValue){
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locatorType, String propertyName){
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    public String getHexaColorFromRgba(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locatorType) {
        return getListWebElement(driver,locatorType).size();
    }

    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValue) {
        return getListWebElement(driver,getDynamicXpath(locatorType, dynamicValue)).size();
    }

    public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
        highlightElement(driver, locatorType);
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            if (driver.toString().contains("internet explorer")) {
                clickToElementByJS(driver, locatorType);
            } else {
                element.click();
            }
        }
    }

    public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValue) {
        highlightElement(driver, locatorType, dynamicValue);
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        if (!element.isSelected()) {
            if (driver.toString().contains("internet explorer")) {
                clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicValue));
            } else {
                element.click();
            }
        }
    }

    public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        highlightElement(driver, locatorType);
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            if (driver.toString().contains("internet explorer")) {
                clickToElementByJS(driver, locatorType);
            } else {
                element.click();
            }
        }
    }



    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        highlightElement(driver, locatorType);
        try {
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        System.out.println("Start time : "+new Date().toString());
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, locator);
        overrideImplicitTimeout(driver, longTimeout);
        if (elements.size()==0) {
            return true;
        } else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
            return true;
        }else {
            return false;
        }
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isSelected();
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue))).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver,locatorType), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)), key).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public WebElement getShadowDOM(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getWebElement(driver, locatorType));
        return element;
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.replace("xpath=", "");
        return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public boolean isPageLoadedSuccess(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0)");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply( WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public boolean isJQueryAjaxLoadedSuccess(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ==0);");
            }
        };
        return explicitWait.until(jQueryLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }


    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        return status;
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        return status;
    }

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, longTimeout);
    }

    public void waitForAllElementInisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
    }

    public void waitForAllElementInisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }




    public void openSubMenuPageByText(WebDriver driver, String pageName, String subMenuPageName) {
        waitForElementClickable(driver, UserBasePageUI.MENU_BY_PAGE_NAME, pageName);
        if (driver.toString().contains("internet explorer")){
            openPageUrl(driver, getElementAttribute(driver, UserBasePageUI.MENU_BY_PAGE_NAME,"href",pageName));
        }else{
            clickToElement(driver, UserBasePageUI.MENU_BY_PAGE_NAME, pageName);
        }

        waitForElementClickable(driver, UserBasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
        if(driver.toString().contains("internet explorer")){
            openPageUrl(driver, getElementAttribute(driver, UserBasePageUI.MENU_BY_PAGE_NAME,"href",subMenuPageName));
        }else{
            clickToElement(driver, UserBasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
        }
    }

    public void openMenuPageByText(WebDriver driver, String pageName) {
        waitForElementClickable(driver, UserBasePageUI.MENU_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserBasePageUI.MENU_BY_PAGE_NAME, pageName);
    }
}

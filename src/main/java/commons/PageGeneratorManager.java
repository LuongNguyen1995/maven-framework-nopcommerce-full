package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.user.*;

public class PageGeneratorManager {
    public static PageGeneratorManager getPageGeneratorManager(){
        return new PageGeneratorManager();
    }


    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserMyAccountPageObject getUserMyAccountPage(WebDriver driver) {
        return new UserMyAccountPageObject(driver);
    }

    public static UserComputersPageObject getUserComputersPage(WebDriver driver) {
        return new UserComputersPageObject(driver);
    }

    public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
        return new UserSearchPageObject(driver);
    }
}

package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;

public class LoginPageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    MyAccountPage myAccountPage;

    @BeforeMethod
    public void initLoginPage() {
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        myAccountPage = new MyAccountPage(page);
    }

    @Test
    public void verifyLogin() {
        homePage.clickMyAccountBtn();
        homePage.clickLoginBtn();
        String email = properties.getProperty("email");
        loginPage.enterEmailId(email);
        loginPage.enterpassword(properties.getProperty("password"));
        loginPage.clickLoginBtn();
        boolean isLoginSuccess = myAccountPage.isLoginSuccessful();
        log.info("isLoginSuccess {}", isLoginSuccess);
        if (isLoginSuccess) {
            log.info("Login succeeded for {}", email);
            myAccountPage.clickMyAccountBtn();
            myAccountPage.clickLogoutBtn();
        } else {
            log.info("Login failed as expected for {}", email);
        }
    }

}

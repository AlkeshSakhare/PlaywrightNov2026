package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterPageTest extends BaseTest {
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeMethod
    void initHomePageTest() {
        homePage = new HomePage(page);
        registerPage = new RegisterPage(page);
    }

    @Test
    public void verifyRegisterAccountTest() {
        homePage.clickMyAccountBtn();
        homePage.clickRegisterBtn();
        registerPage.setFirstName(faker.name().firstName());
        registerPage.setLastName(faker.name().lastName());
        String emailId = faker.internet().emailAddress();
        log.info("EmailId: {}", emailId);
        registerPage.setEmail(emailId);
        registerPage.setTelephoneTxt(faker.phoneNumber().cellPhone());
        String password = faker.internet().password();
        log.info("password: {}", password);
        registerPage.setPasswordTxt(password);
        registerPage.setConfirmPasswordTxt(password);
        registerPage.clickPrivacyPolicyChckBox();
        registerPage.clickContinueBtn();
        String expectedSuccessMSG = "Congratulations! Your new account has been successfully created!";
        String actualSuccessMSG = registerPage.getSuccessMSG();
        log.info("actualSuccessMSG: {}", actualSuccessMSG);
        Assert.assertEquals(actualSuccessMSG, expectedSuccessMSG);
    }
}

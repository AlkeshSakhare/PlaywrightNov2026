package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MyAccountPage extends BasePage {


    private String btnLogout = "//a[contains(text(),'Logout')]";
    private String lblMyAccount = "//h2[contains(text(),'My Account')]";
    private String loginError = ".alert-danger";
    private String myAccountMenu = "span:has-text('My Account')";

    public MyAccountPage(Page page) {
        super(page);
    }

    public boolean isLoginSuccessful() {
        Locator myAccount = page.locator(lblMyAccount);
        Locator loginErrorMsg = page.locator(loginError);

        myAccount.or(loginErrorMsg).first().waitFor(
                new Locator.WaitForOptions()
                        .setTimeout(3000)
        );

        return myAccount.isVisible();
    }

    public void clickLogoutBtn() {
        page.click(btnLogout);
    }

    public void clickMyAccountBtn() {

        page.click(myAccountMenu);
    }
}

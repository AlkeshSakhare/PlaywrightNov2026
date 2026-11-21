package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private String txtEmailId = "#input-email";
    private String txtPassword = "#input-password";
    private String btnLogin = "[value='Login']";

    public LoginPage(Page page) {
        super(page);
    }

    public void enterEmailId(String email) {
        page.fill(txtEmailId, email);
    }

    public void enterpassword(String pass) {
        page.fill(txtPassword, pass);
    }

    public void clickLoginBtn() {
        page.click(btnLogin);
    }
}

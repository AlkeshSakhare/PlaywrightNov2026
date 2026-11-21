package pages;

import com.microsoft.playwright.Page;

public class RegisterPage extends BasePage {

    private String txtLastName = "#input-lastname";
    private String txtEmail = "#input-email";
    private String txtTelephone = "#input-telephone";
    private String txtPassword = "#input-password";
    private String txtConfirmpassword = "#input-confirm";
    private String checkBoxPrivacyPolicy = "[name='agree']";
    private String btnContinue = "[value='Continue']";
    private String msgSuccess = "//*[@id='content']/p";
    private String txtFirstName = "#input-firstname";

    public RegisterPage(Page page) {
        super(page);
    }

    public void setFirstName(String fname) {
        page.fill(txtFirstName, fname);
    }

    public void setLastName(String lname) {
        page.fill(txtLastName, lname);
    }

    public void setEmail(String email) {
        page.fill(txtEmail, email);
    }

    public void setTelephoneTxt(String telephone) {
        page.fill(txtTelephone, telephone);
    }

    public void setPasswordTxt(String pwd) {
        page.fill(txtPassword, pwd);
    }

    public void setConfirmPasswordTxt(String pwd) {
        page.fill(txtConfirmpassword, pwd);
    }

    public void clickPrivacyPolicyChckBox() {
        page.click(checkBoxPrivacyPolicy);
    }

    public void clickContinueBtn() {
        page.click(btnContinue);
    }

    public String getSuccessMSG() {
        return page.locator(msgSuccess).first().textContent();
    }
}

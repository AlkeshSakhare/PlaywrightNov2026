package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/*
 By default Playwright handles JS Alert as cancel to handle it we need to handle like below

        page.onceDialog(dialog -> {
            //accept alert
            dialog.accept();
            //dismiss alert
            dialog.dismiss();
            // capture alert text
            dialog.message();
            //prompt send message
            dialog.accept("Input text message");
        });
 */
public class Day18_JSAlertHandle {
    static Page page;

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
        page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
        // clickOnJS();
        //   clickOnConfirm();
        clickOnPrompt();
    }

    private static void clickOnConfirm() {
        System.out.println("Before click JS: " + page.textContent("#result"));
        // default cancel confirm
        page.click("button:has-text('Click for JS Confirm')");
        System.out.println("After click JS: " + page.textContent("#result"));
        // accept confirm
        page.onceDialog(dialog -> {
            //accept alert
            dialog.accept();
        });

        page.click("button:has-text('Click for JS Confirm')");
        System.out.println("After click JS: " + page.textContent("#result"));
    }

    private static void clickOnPrompt() {
        System.out.println("Before click JS: " + page.textContent("#result"));
        // default cancel prompt
        page.click("button:has-text('Click for JS Prompt')");
        System.out.println("After click JS: " + page.textContent("#result"));
        // accept prompt
        page.onceDialog(dialog -> {
            //accept alert
            dialog.accept("You have accepted");

        });

        page.click("button:has-text('Click for JS Prompt')");
        System.out.println("After click JS: " + page.textContent("#result"));
    }

    private static void clickOnJS() {
        System.out.println("Before click JS: " + page.textContent("#result"));
        page.click("button:has-text('Click for JS Alert')");
        System.out.println("After click JS: " + page.textContent("#result"));
    }
}

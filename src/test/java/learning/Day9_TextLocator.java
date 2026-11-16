package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day9_TextLocator {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://tutorialsninja.com/demo/index.php?route=account/login");
        // will work for visible single element
        String header = page.locator("text=New Customer").textContent();
        System.out.println("header: " + header);
        // sudo text ()
        page.locator("input:has-text('Login')").click();
        System.out.println("title before continue: " + page.title());
        page.getByText("Continue").click();
        System.out.println("title after continue clicked: " + page.title());
        page.goBack();
        System.out.println("title after going back: " + page.title());
        page.close();
        browser.close();
        playwright.close();
    }
}

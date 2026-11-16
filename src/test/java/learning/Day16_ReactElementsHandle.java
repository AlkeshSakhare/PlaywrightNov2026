package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day16_ReactElementsHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.flipkart.com/");
        page.locator("//span[@role='button']").click();
        page.locator("//input[@type='text']").nth(0).fill("Macbook Pro");

        /*
        somehow react support is removed e.g. _react=p[name='email'] >> input
        https://playwright.dev/java/docs/release-notes#breaking-changes-%EF%B8%8F-4

         */
    }
}

package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day10_FrameHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/iframes_advanced");
        String result = page.locator("#message-log").textContent();
        System.out.println("Before click: " + result);
        page.frameLocator("#sandboxed-frame").locator("#send-message").click();
        result = page.locator("#message-log").textContent();
        System.out.println("After click: " + result);
        page.close();
        browser.close();
        playwright.close();
    }
}

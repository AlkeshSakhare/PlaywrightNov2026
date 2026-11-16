package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day12_VisibleElement {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://myntra.com");
        page.waitForFunction("() => document.querySelectorAll('img').length > 0");
        int count = page.locator("img:visible").count();
        System.out.println("Count: " + count);
        int allLinks = page.locator("a").count();
        System.out.println("allLinks: " + allLinks);
        int allVisibleLink = page.locator("a:visible").count();
        System.out.println("allVisibleLink: " + allVisibleLink);
        allVisibleLink = page.locator("a >> visible=true").count();
        System.out.println("allVisibleLink: " + allVisibleLink);

        page.close();
        browser.close();
        playwright.close();

    }
}

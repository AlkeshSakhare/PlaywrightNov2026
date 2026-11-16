package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day11_ShadowDOMHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        // DOM -  Shadow DOM
        page.navigate("https://the-internet.herokuapp.com/shadowdom_advanced");
        String result = page.locator("#nested-result").textContent();
        System.out.println("Before click: " + result);
        page.locator("#outer-card #inner-button").click();
        result = page.locator("#nested-result").textContent();
        System.out.println("After click: " + result);


        // DOM - Shadow DOM -  Shadow DOM
        page.navigate("https://selectorshub.com/iframe-in-shadow-dom/");
        page.locator("#userName").locator("#app2 #pizza").fill("Affordable");
        page.locator("#userName #app2 #pizza").pressSequentially(" testAffordable");
        page.close();
        browser.close();
        playwright.close();
    }
}

package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day23_WindowHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/");
        Page popoup = page.waitForPopup(() -> {
            page.click("//a[contains(@href,'linkedin')]");
        });
        System.out.println("Parent window title: " + page.title());
        System.out.println("Child window title: " + popoup.title());
        popoup.close();
        page.close();
    }
}

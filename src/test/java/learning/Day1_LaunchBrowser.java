package learning;

import com.microsoft.playwright.*;

public class Day1_LaunchBrowser {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        //        launchOptions.setChannel("msedge");
        launchOptions.setChannel("chrome");

        Browser browser = playwright.chromium().launch(launchOptions);

        Page page = browser.newPage();
        page.navigate("https://amazon.com");
        long startTime = System.currentTimeMillis();
        page.waitForFunction("() => document.title.length > 0");
        long endTime = System.currentTimeMillis();
        long waitTime = endTime - startTime;
        System.out.println("Waited for: " + waitTime + " milliseconds");
        System.out.println("Waited for: " + (waitTime / 1000.0) + " seconds");
        String title = page.title();
        System.out.println("Title: " + title);
        browser.close();
        playwright.close();
    }
}

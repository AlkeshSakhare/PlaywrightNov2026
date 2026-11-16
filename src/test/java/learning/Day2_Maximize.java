package learning;


import com.microsoft.playwright.*;

import java.util.Arrays;

public class Day2_Maximize {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        // Launch browser
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();

        launchOptions.setHeadless(false);
        launchOptions.setChannel("chrome");
        launchOptions.setArgs(Arrays.asList("--start-maximized"));

        Browser browser = playwright.chromium().launch(launchOptions);
        // Disable fixed viewport
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.setViewportSize(null);

        BrowserContext context = browser.newContext(contextOptions);
        Page page = context.newPage();
        page.navigate("https://www.flipkart.com");
        System.out.println("Title: " + page.title());

        // Close browser resources
        context.close();
        browser.close();
        playwright.close();
    }
}

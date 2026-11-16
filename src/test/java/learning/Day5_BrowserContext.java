package learning;

import com.microsoft.playwright.*;

/*
BrowserContext isolation is one of Playwright's biggest advantages for testing multiple users independently.
Context means the environment or situation in which something operates.

In Playwright, BrowserContext means an isolated browser session/profile.

Think of it like this:
- Browser = A browser application (Safari, Chrome, etc.).
- BrowserContext = A separate user session inside that browser.
- Page = A tab inside that session.

Each context maintains its own session and storage.

Important concept: BrowserContext isolation
Suppose you log in to Google in Context 1.
- Context 1: Google login session exists.
- Context 2: Google login session does not exist.
- Context 3: Google login session does not exist.
Cookies, local storage and session storage are isolated between these contexts.

This is useful when testing multiple users simultaneously. every new BrowserContext is created as an isolated, incognito-like session by default.
 */

public class Day5_BrowserContext {
    public static void main(String[] args) {

        String url1 = "https://myntra.com";
        String url2 = "https://flipkart.com";
        String url3 = "https://amazon.com";
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context1 = browser.newContext();
        Page p1 = context1.newPage();
        p1.navigate(url1);
        System.out.println("url : " + p1.url());
        System.out.println("Context 1 Title: " + p1.title());
        BrowserContext context2 = browser.newContext();
        Page p2 = context2.newPage();
        p2.navigate(url2);
        System.out.println("url : " + p2.url());
        System.out.println("Context 2 Title: " + p2.title());
        BrowserContext context3 = browser.newContext();
        Page p3 = context3.newPage();
        p3.navigate(url3);
        p3.waitForFunction("() => document.title.length > 0");
        System.out.println("url : " + p3.url());
        System.out.println("Context 3 Title: " + p3.title());

        context1.close();
        context2.close();
        context3.close();

        browser.close();
        playwright.close();
    }
}

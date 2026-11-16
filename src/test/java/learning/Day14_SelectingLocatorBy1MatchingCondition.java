package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Day14_SelectingLocatorBy1MatchingCondition {
    public static void main(String[] args) {
        /*
        Selecting Element Using one of the matching condition
	- comma separted css selector list = playwright.$("#my-text-id , input[id='my-text-id']")
	- xpath union (pipe)  = playwright.$("//*[@*='my-text-id'] | //input[@id='my-text-id']")
         */

        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.selenium.dev/selenium/web/web-form.html");

        //comma separted css selector list
        page.locator("#my-text-id , input[id='my-text-id']").fill("This is css selector comma example");
        page.waitForTimeout(2000);
        // xpath union (pipe)
        page.locator("//*[@*='my-text-id'] | //input[@id='my-text-id']").fill("This is xpath | separated");

        page.close();
        browser.close();
        playwright.close();

    }
}

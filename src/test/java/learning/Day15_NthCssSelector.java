package learning;

import com.microsoft.playwright.*;

public class Day15_NthCssSelector {

    /*
    Nth Element Selector = page.locator("a >> nth=0")
	= nth=0 - >first
	= nth=-1 ->last
    */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.myntra.com/");
        Locator linkList = page.locator("a");
        System.out.println("linkList: " + linkList.count());
        String firstLink = linkList.nth(0).textContent();
        System.out.println("firstLink : " + firstLink);
        String lastLink = linkList.nth(linkList.count() - 1).textContent();
        System.out.println("lastLink : " + lastLink);
        String firstLinkdirect = page.locator("a>>nth=0").textContent();
        System.out.println("firstLinkdirect : " + firstLinkdirect);
        String lastLinkdirect = page.locator("a>>nth=-1").textContent();
        System.out.println("lastLinkdirect : " + lastLinkdirect);
        page.close();
        browser.close();
        playwright.close();
    }
}

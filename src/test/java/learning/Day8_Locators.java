package learning;

import com.microsoft.playwright.*;

public class Day8_Locators {
    public static void main(String[] args) {
        String url = "https://orangehrm.com/";
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate(url);
        //single element
        Locator contactSalesBtn = page.locator("text = Contact Sales");
        contactSalesBtn.nth(1).click();
        // contactSalesBtn = page.locator("//button[contains(text(),'Contact Sales')]");
        //multiple elements
        Locator country = page.locator("#Form_getForm_Country");
        country.click();
        Locator countries = page.locator("#Form_getForm_Country option");
        int count = countries.count();
        System.out.println("No of countries: " + count);
        // countries.allTextContents().forEach(countryelement -> System.out.println(countryelement.trim()));

        for (int i = 0; i < count; i++) {
            System.out.println("Country " + i + " : " + countries.nth(i).textContent().trim());
        }
        page.close();
        browser.close();
        playwright.close();
    }
}

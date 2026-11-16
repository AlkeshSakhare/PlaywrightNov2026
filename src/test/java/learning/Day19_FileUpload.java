package learning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Day19_FileUpload {
    public static void main(String[] args) {
        String fileName = "applogin.json";
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/upload");
        page.setInputFiles("#file-upload", Paths.get(fileName));
        page.waitForTimeout(2000);
        // deselect file
        page.setInputFiles("#file-upload", new Path[0]);
        page.waitForTimeout(2000);
        page.setInputFiles("#file-upload", Paths.get(fileName));
        page.click("#file-submit");
        System.out.println("Uploaded filename: " + page.textContent("#uploaded-files").trim());

        page.close();
        browser.close();
        playwright.close();
    }
}

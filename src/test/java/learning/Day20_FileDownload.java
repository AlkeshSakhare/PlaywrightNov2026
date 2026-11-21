package learning;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class Day20_FileDownload {
    public static void main(String[] args) {
        String downloadfileName = "sample.txt";
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/download");
        Download download = page.waitForDownload(() -> {
            page.click("a:has-text('" + downloadfileName + "')");
        });
        System.out.println("Download status: " + download.failure());
        System.out.println("Download URL: " + download.url());
        String fileName = download.suggestedFilename();
        download.saveAs(Paths.get(fileName));
        page.close();
        browser.close();
        playwright.close();
    }
}

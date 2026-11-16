package learning;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class Day17_AutomaticLogin {
    static BrowserContext context;
    static Page page;
    static Playwright playwright;
    static Browser browser;
    static String path = "applogin.json";

    public static void main(String[] args) {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // loginToApp();
        withoutCredentials();
        page.close();
        browser.close();
        playwright.close();

    }

    static void withoutCredentials() {
        context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(path)));
        page = context.newPage();
        page.navigate("https://tutorialsninja.com/demo/index.php?route=account/login");
    }

    static void loginToApp() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://tutorialsninja.com/demo/index.php?route=account/login");
        page.locator("#input-email").fill("david.dicki@gmail.com");
        page.locator("#input-password").fill("f859m3x0");
        page.locator("//*[@*='submit']").click();
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(path)));
    }
}

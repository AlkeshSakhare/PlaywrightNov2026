package learning;

import com.microsoft.playwright.*;

import java.awt.*;

public class Day21_MaximizeWindow {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        System.out.println("width : " + width + "\t height : " + height);
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        Page page = context.newPage();
        page.navigate("https://google.com");
        page.waitForTimeout(2000);
        page.close();
        context.close();
        browser.close();
        playwright.close();

    }
}

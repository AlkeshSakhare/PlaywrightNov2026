package factory;

import com.microsoft.playwright.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    Properties properties;

    public Page initBrowser(Properties properties) {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        String browserName = properties.getProperty("browser");
        System.out.println("browserName : " + browserName);
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "chrome":
                launchOptions.setChannel("chrome");
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "msedge":
                launchOptions.setChannel("msedge");
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "safari":
            case "webkit":
                browser = playwright.webkit().launch(launchOptions);
                break;
            default:
                System.out.println("Please enter valid browser name");
        }
        context = browser.newContext();
        page = context.newPage();
        String url = properties.getProperty("url");
        System.out.println("url : " + url);
        page.navigate(url);
        return page;
    }

    public Properties initProperties() {
        try {
            properties = new Properties();
            properties.load(new FileReader("src/test/resources/config.properties"));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

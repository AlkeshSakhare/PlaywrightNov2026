package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    static Logger log;
    static Page page;
    static Properties properties;
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Faker faker = new Faker();

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }

    public static String takeScreenshot(String name) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Path screenshotDir = Paths.get(System.getProperty("user.dir"), "screenshots");
        try {
            Files.createDirectories(screenshotDir);
            Path screenshotPath = screenshotDir.resolve(name + "_" + timeStamp + ".png");
            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
            return screenshotPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Screenshot capture failed", e);
        }
    }

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browserName) {
        try {
            properties = new Properties();
            properties.load(new FileReader("src/test/resources/config.properties"));
            log = LogManager.getLogger(this.getClass());
            playwright = Playwright.create();
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
            log.info("browserName : {}", browserName);
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
            log.info("Launching {} browser", browserName);
            String url = properties.getProperty("url");
            log.info("Navigate to {}", url);
            page.navigate(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Parameters({"browser"})
    @AfterTest
    public void tearDown(String browserName) {
        log.info("Closing {} browser", browserName);
        page.context().browser().close();
    }
}

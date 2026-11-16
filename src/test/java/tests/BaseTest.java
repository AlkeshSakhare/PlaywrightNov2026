package tests;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    Page page;
    HomePage homePage;
    Properties properties;

    @BeforeTest
    public void setUp() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initProperties();
        page = playwrightFactory.initBrowser(properties);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}

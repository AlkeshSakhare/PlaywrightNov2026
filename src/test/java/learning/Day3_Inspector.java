package learning;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.Arrays;

public class Day3_Inspector {

    /*
    https://playwright.dev/java/docs/codegen#running-codegen

    command to open codegen (Inspector)
    mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"
     */

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(null));
            Page page = context.newPage();
            page.navigate("https://testautomationpractice.blogspot.com/p/playwrightpractice.html");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Primary Action")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Toggle Button")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Div with button role")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username:")).fill("TestUser");
            page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Accept terms")).check();
            page.getByText("This is an important alert").click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email Address:")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email Address:")).fill("testuser@gmail.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password:")).fill("passwoprd123");
            page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Your Age:")).fill("50");
            page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Standard")).check();
            page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Express")).check();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your full name")).fill("Tester QA");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone number (xxx-xxx-xxxx)")).fill("42452671234236");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type your message here...")).fill("May address is not recommenedede here ");
            page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search products...")).fill("mobile");
            page.close();
        }
    }
}
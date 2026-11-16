package learning;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class Day22_RecordExecutionAsVideo {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("recording/")));
            Page page = context.newPage();
            page.navigate("https://tutorialsninja.com/demo/index.php?route=common/home");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" My Account")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* First Name")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* First Name")).fill("Good");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Last Name")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Last Name")).fill("Day");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* E-Mail")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* E-Mail")).fill("good@day.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Telephone")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Telephone")).fill("48452148451");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Password").setExact(true)).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Password").setExact(true)).fill("Test@123");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Password Confirm")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("* Password Confirm")).fill("Test@123");
            page.getByRole(AriaRole.CHECKBOX).check();
            page.close();
            context.close();
            browser.close();
        }
    }
}
package learning;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class Day4_TraceViewer {
    /* https://playwright.dev/java/docs/trace-viewer

    command to open trace.zip
    mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
    */
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            Page page = context.newPage();
            page.navigate("https://formy-project.herokuapp.com/form");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First name")).fill("Poonam");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last name")).fill("Das");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Job title")).fill("Ladkibazzi");
            page.locator("#radio-button-2").check();
            page.locator("#checkbox-2").check();
            page.getByLabel("Years of experience:").selectOption("4");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("mm/dd/yyyy")).click();
            page.getByRole(AriaRole.COLUMNHEADER, new Page.GetByRoleOptions().setName("November")).click();
            page.getByRole(AriaRole.COLUMNHEADER, new Page.GetByRoleOptions().setName("2026")).click();
            page.getByRole(AriaRole.COLUMNHEADER, new Page.GetByRoleOptions().setName("-2029")).click();
            page.getByRole(AriaRole.COLUMNHEADER, new Page.GetByRoleOptions().setName("-2090")).click();
            page.getByRole(AriaRole.COLUMNHEADER, new Page.GetByRoleOptions().setName("-2900")).click();
            page.getByText("1900").click();
            page.getByText("1890").click();
            page.getByText("1895").click();
            page.getByText("Jul").click();
            page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("1").setExact(true)).nth(1).click();
            page.getByText("College").click();
            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }
    }
}
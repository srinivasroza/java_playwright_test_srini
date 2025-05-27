package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            BrowserContext context = browser.newContext();
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            Page page = context.newPage();
            page.navigate("https://playwright.dev/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get started")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Node.js")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Java").setExact(true)).click();
            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("pom.xml")).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("What's nextDirect link to")).click();
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
         }
    }
}
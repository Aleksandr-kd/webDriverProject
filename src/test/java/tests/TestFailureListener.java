package tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import utils.AllureScreenshotUtils;


public class TestFailureListener implements TestWatcher {

    private final AllureScreenshotUtils screenshotUtils = new AllureScreenshotUtils();

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseSuite baseSuite) {
            WebDriver driver = baseSuite.getDriver();
            screenshotUtils.takeScreenshot(driver, " Ошибка: " + context.getDisplayName());

            baseSuite.close();
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context){
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseSuite baseSuite){
            baseSuite.close();
        }
    }
}


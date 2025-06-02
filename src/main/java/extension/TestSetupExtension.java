package extension;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import modules.GuiceDtoModule;
import modules.GuicePageModule;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import utils.AllureScreenshotUtils;


public class TestSetupExtension implements BeforeEachCallback, AfterTestExecutionCallback {

    private final AllureScreenshotUtils screenshotUtils = new AllureScreenshotUtils();

    private Injector injector;

    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        driver = new WebDriverFactory().getDriver();
        this.injector = Guice.createInjector(new GuicePageModule(driver), new GuiceDtoModule());
        injector.injectMembers(context.getTestInstance().get());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            screenshotUtils.takeScreenshot(driver, "Error: " + context.getDisplayName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

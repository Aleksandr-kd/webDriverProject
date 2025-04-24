package tests;

import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public abstract class BaseSuite {

    protected WebDriver driver = null;

    @BeforeEach
    public void init() {
        driver = new WebDriverFactory().getDriver();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

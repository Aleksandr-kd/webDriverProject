package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Waiters;

import java.util.List;

public class AbsCommon {

    protected WebDriver driver = null;
    protected Waiters waiters;
    protected Actions actions;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        this.actions = new Actions(driver);
    }

    public WebElement $(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> $$(By locator){
        return driver.findElements(locator);
    }
}

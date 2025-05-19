package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbsBasePage {

    protected WebDriver driver = null;
    private String path = "";
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы")
    public void open() {
        driver.get(baseUrl + path);
    }
}

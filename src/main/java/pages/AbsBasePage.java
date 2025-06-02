package pages;

import common.AbsCommon;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbsBasePage<T> extends AbsCommon {

    private String path = "";
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы")
    public T open() {
        driver.get(baseUrl + path);

        return (T) this;
    }
}

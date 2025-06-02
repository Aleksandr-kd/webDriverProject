package pages;

import annotations.Path;
import common.AbsCommon;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbsBasePage<T> extends AbsCommon {

    private String path = "";
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String getPath() {
        Class<T> clazz = (Class<T>) this.getClass();
        if (clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        }
        throw new RuntimeException(String.format("Path on class %s not found", clazz.getName()));
    }


    @Step("Открытие страницы")
    public T open() {
        driver.get(baseUrl + getPath());

        return (T) this;
    }
}

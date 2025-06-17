package pages;

import annotations.Path;
import dto.User;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;


@Path("/form.html")
public class RegistrationFormPage extends AbsBasePage<RegistrationFormPage> {
    public RegistrationFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirm_password")
    private WebElement confirmPassword;

    @FindBy(id = "birthdate")
    private WebElement birthDate;

    @FindBy(id = "language_level")
    private WebElement languageLevel;

    @FindBy(css = "[id='output']")
    private WebElement output;

    @Step("Ввести имя пользователя: {nameRegistration}")
    public void setName(String nameRegistration) {
        name.click();
        name.sendKeys(nameRegistration);
    }

    @Step("Ввести email пользователя")
    public void setEmail(String emailRegistration) {
        email.sendKeys(emailRegistration);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(String passwordRegistration) {
        password.sendKeys(passwordRegistration);
    }

    @Step("Повторить пароль пользователя")
    public void setPasswordRepeat(String passwordTwoRegistration) {
        confirmPassword.sendKeys(passwordTwoRegistration);
    }

    @Step("Повторить неверный пароль пользователя")
    public void setPasswordRepeatFalse(String passwordTwoRegistration) {
        confirmPassword.sendKeys(passwordTwoRegistration);
    }

    @Step("Установить дату рождения пользователя")
    public void setBirthDate(String birthdateUser) {
        setDate(birthdateUser);
    }

    @Step("Установить дату")
    public void setDate(String date) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];",
                birthDate,
                date
        );
    }

    @Step("Установить уровень языка пользователя")
    public void setLanguageLevel(String setLanguageUser) {
        languageLevel.click();
        languageLevel.sendKeys(setLanguageUser);
        languageLevel.click();
    }

    @Step("Зарегистрировать пользователя")
    public RegistrationFormPage userRegistration() {
        $(By.cssSelector("form#registrationForm input[type='submit']")).click();
        return this;
    }

    @Step("Получение текста после регистрации")
    public String getTextRegistration() {
        return output.getText();
    }

    @Step("Отображение alert ошибки")
    public boolean isPasswordErrorDisplayed() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Закрыть alert")
    public RegistrationFormPage closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    @Step("Текст alert ошибки")
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Step("Заполнение формы регистрации")
    public RegistrationFormPage formRegistrationTrue(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPasswordRepeat(user.getPassword());

        setBirthDate(user.getData());
        setLanguageLevel(user.getLanguageLevel().getRussianName());
        return this;
    }

    @Step("Заполнение формы регистрации")
    public RegistrationFormPage formRegistrationFalse(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPasswordRepeatFalse(user.getPassword() + "password");
        setBirthDate(user.getData());
        setLanguageLevel(user.getLanguageLevel().getRussianName());
        return this;
    }

    @Step("Проверка регистрации")
    public RegistrationFormPage checkRegistration(String title) {
        assertThat(getTextRegistration().replace("\n", " "))
                .isEqualTo(title.replace("\n", " "));
        return this;
    }

    @Step("Проверка ввода повторного пароля")
    public RegistrationFormPage checkRegistrationFalse(String title) {
        assertThat(getAlertText())
                .isEqualTo(title);
        return this;
    }

    @Step("Отображения уведомления об ошибки")
    public RegistrationFormPage passwordErrorDisplayed() {
        assertThat(isPasswordErrorDisplayed())
                .isFalse();
        return this;
    }
}
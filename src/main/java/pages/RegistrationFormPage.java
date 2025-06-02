package pages;

import dto.User;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;


public class RegistrationFormPage extends AbsBasePage<RegistrationFormPage> {
    public RegistrationFormPage(WebDriver driver) {
        super(driver, "/form.html");
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

    @Step("Установить дату рождения пользователя")
    public void setBirthDate(String birthdateUser) {
        setDate(birthDate, birthdateUser);
    }

    @Step("Установить дату")
    public void setDate(WebElement element, String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6);

        element.click();
        element.clear();

        new Actions(driver)
                .sendKeys(element, day)
                .sendKeys(month)
                .sendKeys(year)
                .sendKeys(Keys.TAB)
                .perform();
    }

    @Step("Установить уровень языка пользователя")
    public void setLanguageLevel(String setLanguageUser) {
        languageLevel.click();
        languageLevel.sendKeys(setLanguageUser);
        languageLevel.click();
    }

    @Step("Зарегистрировать пользователя")
    public RegistrationFormPage userRegistration() {
        driver.findElement(By.cssSelector("form#registrationForm input[type='submit']")).click();
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
    public RegistrationFormPage formRegistration(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPasswordRepeat(user.getPassword());
        setBirthDate(user.getData());
        setLanguageLevel(user.getLanguageLevel().getRussianName());
        setPasswordRepeat(user.getPassword() + "password");
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
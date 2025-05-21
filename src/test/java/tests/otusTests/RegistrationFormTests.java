package tests.otusTests;

import dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.RegistrationFormPage;
import extension.TestSetupExtension;
import utils.LanguageLevelUtils;
import utils.PasswordUtils;

import java.time.format.DateTimeFormatter;


@ExtendWith(TestSetupExtension.class)
public class RegistrationFormTests {


    @Test
    @Tag("autotest")
    @DisplayName("Проверка регистрации.")
    public void testCorrectPassword(WebDriver driver) {

        User user = new User();
        String name = user.getName();
        String email = user.getEmail();
        String birthDate = user.getData().format(DateTimeFormatter.ISO_DATE);
        String language = user.getEnglishLevel();
        String password = user.getPassword();
        String passwordRepeat = user.getPassword();

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();

        String birthDateFormatted = registrationFormPage.dataRegistration(birthDate);
        LanguageLevelUtils languageLevelUtils = new LanguageLevelUtils();
        String languageLevelFormatted = languageLevelUtils.convert(language);

        String expectedText = String.format("Имя пользователя: %s Электронная почта: %s Дата рождения: %s " +
                "Уровень языка: %s", name, email, birthDate, languageLevelFormatted);

        registrationFormPage.formRegistration(name,
                email,
                password,
                passwordRepeat,
                birthDateFormatted,
                language
        );
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistration(expectedText);
    }


    @Test
    @Tag("autotest")
    @DisplayName("Проверка валидации пароля.")
    public void testNotCorrectPassword(WebDriver driver) {

        User user = new User();
        PasswordUtils passwordUtils = new PasswordUtils();
        String name = user.getName();
        String email = user.getEmail();
        String birthDate = user.getData().format(DateTimeFormatter.ISO_DATE);
        String language = user.getEnglishLevel();
        String password = user.getPassword();
        String passwordRepeatFalse = passwordUtils.passwordRepeat(password);
        String alertText = "Пароли не совпадают!";

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();
        registrationFormPage.formRegistration(name,
                email,
                password,
                passwordRepeatFalse,
                birthDate,
                language);
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistrationFalse(alertText);
        registrationFormPage.closeAlert();
        registrationFormPage.passwordErrorDisplayed();
    }
}
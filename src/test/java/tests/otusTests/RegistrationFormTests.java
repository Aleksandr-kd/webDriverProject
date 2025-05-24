package tests.otusTests;

import dto.User;
import extension.TestSetupExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.RegistrationFormPage;

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
        String birthDate = user.getData();
        String language = user.getLanguageLevel().getRussianName();
        String password = user.getPassword();
        String passwordRepeat = user.getPassword();

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();

        String birthDateFormatted = user.getDataFaker().format(DateTimeFormatter.ISO_DATE);
        String languageLevelFormatted = user.getLanguageLevel().getEnglishName();

        String expectedText = String.format("Имя пользователя: %s Электронная почта: %s Дата рождения: %s " +
                "Уровень языка: %s", name, email, birthDateFormatted, languageLevelFormatted);

        registrationFormPage.formRegistration(name,
                email,
                password,
                passwordRepeat,
                birthDate,
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
        String name = user.getName();
        String email = user.getEmail();
        String birthDate = user.getData();
        String language = user.getLanguageLevel().getRussianName();
        String password = user.getPassword();
        String passwordRepeatFalse = password + "password";
        String alertText = "Пароли не совпадают!";

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();
        registrationFormPage.formRegistration(name,
                email,
                password,
                passwordRepeatFalse,
                birthDate,
                language
        );
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistrationFalse(alertText);
        registrationFormPage.closeAlert();
        registrationFormPage.passwordErrorDisplayed();
    }
}
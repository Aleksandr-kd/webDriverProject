package tests.otusTests;

import dto.User;
import extension.TestSetupExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.RegistrationFormPage;

import java.time.format.DateTimeFormatter;


@ExtendWith(TestSetupExtension.class)
public class RegistrationFormTests {

    @Inject
    private RegistrationFormPage registrationFormPage;

    @Inject
    private User user;

    @Test
    @Tag("autotest")
    @DisplayName("Проверка регистрации.")
    public void testCorrectPassword() {

        String name = user.getName();
        String email = user.getEmail();

        registrationFormPage
                .open();

        String birthDateFormatted = user.getDataFaker().format(DateTimeFormatter.ISO_DATE);
        String languageLevelFormatted = user.getLanguageLevel().getEnglishName();

        String expectedText = String.format("Имя пользователя: %s Электронная почта: %s Дата рождения: %s " +
                "Уровень языка: %s", name, email, birthDateFormatted, languageLevelFormatted);

        registrationFormPage
                .formRegistration(user)
                .userRegistration()
                .checkRegistration(expectedText);
    }


    @Test
    @Tag("autotest")
    @DisplayName("Проверка валидации пароля.")
    public void testNotCorrectPassword() {

        registrationFormPage
                .open()
                .formRegistration(user)
                .userRegistration()
                .checkRegistrationFalse("Пароли не совпадают!")
                .closeAlert()
                .passwordErrorDisplayed();
    }
}
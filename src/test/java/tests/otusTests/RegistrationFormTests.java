package tests.otusTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.RegistrationFormPage;
import tests.BaseSuite;
import tests.TestFailureListener;


public class RegistrationFormTests extends BaseSuite {


    @Test
    @Tag("autotest")
    @Execution(ExecutionMode.CONCURRENT)
    @ExtendWith(TestFailureListener.class)
    @DisplayName("Тест в режиме fullscreen. Проверка регистрации.")
    public void testCorrect() throws InterruptedException {
        String name = "ОТУС";
        String email = System.getProperty("login");
        String birthDate = "15-12-1995";
        String language = "Продвинутый";
        String password = System.getProperty("password");
        String passwordRepeat = System.getProperty("password.repeat");

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();

        String birthDateFormatted = registrationFormPage.dataRegistration(birthDate);
        String expectedText = String.format("Имя пользователя: %s Электронная почта: %s Дата рождения: %s " +
                "Уровень языка: advanced", name, email, birthDateFormatted);

        registrationFormPage.formRegistration(name, email, password, passwordRepeat, birthDate, language);
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistration(expectedText);
    }


    @Test
    @Tag("autotest")
    @Execution(ExecutionMode.CONCURRENT)
    @ExtendWith(TestFailureListener.class)
    @DisplayName("Тест в режиме fullscreen. Проверка валидации пароля.")
    public void testNotCorrect() throws InterruptedException {
        String name = "ОТУС";
        String email = System.getProperty("login");
        String birthDate = "15-12-1995";
        String language = "Средний";
        String password = System.getProperty("password");
        String passwordRepeatFalse = System.getProperty("password.false");
        String alertText = "Пароли не совпадают!";

        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.open();
        registrationFormPage.formRegistration(name, email, password, passwordRepeatFalse, birthDate, language);

        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistrationFalse(alertText);
        registrationFormPage.closeAlert();
        registrationFormPage.passwordErrorDisplayed();
    }
}
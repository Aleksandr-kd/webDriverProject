package tests.otusTests;

import dto.User;
import extension.TestSetupExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.TrainingPage;


@ExtendWith(TestSetupExtension.class)
public class WebDriverTests {

    @Inject
    private TrainingPage trainingPage;

    @Inject
    private User user;

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия верной страницы")
    public void pageValidation() {
        String enterText = user.getName();

        trainingPage
                .open()
                .pageTitleShouldBeSameAs("Тренажёр для оттачивания навыков работы с Selenium")
                .textInputFieldMustBeSameAs(enterText);
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия модального окна")
    public void modalWindowValidation() {
        trainingPage
                .open()
                .openModal()
                .checkModalWindow("Вы открыли модальное окно. Нажмите на крестик или в любое место вне окна" +
                        ", чтобы закрыть его.")
                .closeModal();
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка формы")
    public void formValidation() {
        String name = user.getName();
        String email = user.getEmail();

        String text = String.format("Форма отправлена с именем: %s и email: %s", name, email);

        trainingPage
                .open()
                .openingModalWindow(name, email)
                .messageGreenBackground(text);
    }
}
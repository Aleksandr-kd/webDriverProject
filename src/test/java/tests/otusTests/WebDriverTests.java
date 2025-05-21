package tests.otusTests;

import dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.TrainingPage;
import extension.TestSetupExtension;


@ExtendWith(TestSetupExtension.class)
public class WebDriverTests {

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия верной страницы")
    public void pageValidation(WebDriver driver) {

        User user = new User();
        String enterText = user.getName();

        String title = "Тренажёр для оттачивания навыков работы с Selenium";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();

        trainingPage.pageTitleShouldBeSameAs(title);
        trainingPage.textInputFieldMustBeSameAs(enterText);
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия модального окна")
    public void modalWindowValidation(WebDriver driver) {
        String text = "Вы открыли модальное окно. Нажмите на крестик или в любое место вне окна, чтобы закрыть его.";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();

        trainingPage.openModal();
        trainingPage.openingModalWindow(text);
        trainingPage.closeModal();
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка формы")
    public void formValidation(WebDriver driver) {

        User user = new User();
        String name = user.getName();
        String email = user.getEmail();

        String text = String.format("Форма отправлена с именем: %s и email: %s", name, email);

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();
        trainingPage.openingModalWindow(name, email);
        trainingPage.messageGreenBackground(text);
    }
}
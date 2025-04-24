package tests.otusTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TrainingPage;
import tests.BaseSuite;

;

public class WebDriverTests extends BaseSuite {

    @Test
    @Tag("test")
//    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("Тест в режиме HEADLESS")
    public void testOne() {
        String enterText = "ОТУС";
        String title = "Тренажёр для оттачивания навыков работы с Selenium";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();
//        trainingPage.pageTitleShouldBeSameAs(FilterData.FULLSCREEN.getName());

        trainingPage.pageTitleShouldBeSameAs(title);
        trainingPage.textInputFieldMustBeSameAs(enterText);
    }


    @Test
    @Tag("test")
//    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("Тест в режиме KIOSK")
    public void testTwo() {
        String text = "Вы открыли модальное окно. Нажмите на крестик или в любое место вне окна, чтобы закрыть его.";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();

        trainingPage.openingModalWindow(text);
        trainingPage.closeModal();
    }

    @Test
    @Tag("test")
//    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("Тест в режиме FULLSCREEN")
    public void testThree() {
        String name = "Александ";
        String email = "alexander@mail.ru";
        String text = String.format("Форма отправлена с именем: %s и email: %s", name, email);

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();
        trainingPage.openingModalWindow(name, email);
        trainingPage.messageGreenBackground(text);
    }
}
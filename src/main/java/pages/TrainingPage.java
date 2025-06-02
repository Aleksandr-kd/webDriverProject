package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;


public class TrainingPage extends AbsBasePage<TrainingPage> {
    public TrainingPage(WebDriver driver) {
        super(driver, "/training.html");
    }

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    @FindBy(id = "textInput")
    private WebElement textInput;

    @FindBy(id = "openModalBtn")
    private WebElement openModalButton;

    @FindBy(id = "closeModal")
    private WebElement closeModalButton;

    @FindBy(css = "div.modal-content p")
    private WebElement textModalContent;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(css = "button[type='submit']")
    private WebElement send;

    @FindBy(id = "messageBox")
    private WebElement messageBox;

    public String getSimulatorPageText() {
        return pageHeader.getText();
    }

    @Step("Вводим в поле ввода текст ОТУС")
    public String setTextInput(String text) {
        textInput.sendKeys(text);
        return text;
    }

    @Step("Открыть модальное окно")
    public void openModal() {
        openModalButton.click();
    }

    @Step("Закрыть модальное окно")
    public TrainingPage closeModal() {
        closeModalButton.click();
        return this;
    }

    @Step("Текст модального окна")
    public String getTextModal() {
        return textModalContent.getText();
    }

    @Step("Ввод имени")
    public void enterName(String textName) {
        name.sendKeys(textName);
    }

    @Step("Ввод почты")
    public void enterEmail(String textEmail) {
        email.sendKeys(textEmail);
    }

    @Step("Нажать отправить")
    public void clickSend() {
        send.click();
    }

    @Step("Получение текста на зеленом поле")
    public String getTextGreen() {
        return messageBox.getText();
    }

    @Step("Ввести имя и почту")
    public TrainingPage openingModalWindow(String name, String email) {
        enterName(name);
        enterEmail(email);
        clickSend();
        return this;
    }

    @Step("Проверка работы модального окна")
    public TrainingPage checkModalWindow(String text) {
        assertThat(getTextModal())
                .isEqualTo(text);
        return this;
    }

    @Step("Проверка сообщения на зелёном фоне")
    public TrainingPage messageGreenBackground(String text) {
        assertThat(getTextGreen())
                .isEqualTo(text);
        return this;
    }

    @Step("Проверка открытия нужной страницы")
    public TrainingPage pageTitleShouldBeSameAs(String title) {
        assertThat(getSimulatorPageText())
                .isEqualTo(title);
        return this;
    }

    @Step("Проверка ввода текст")
    public TrainingPage textInputFieldMustBeSameAs(String text) {
        assertThat(setTextInput(text))
                .isEqualTo(text);
        return this;
    }
}
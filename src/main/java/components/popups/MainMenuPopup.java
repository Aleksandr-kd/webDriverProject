package components.popups;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public class MainMenuPopup extends AbsCommon implements IPopup<MainMenuPopup> {
    public MainMenuPopup(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainMenuPopup popupShouldBeVisible() {
        return null;
    }

    @Override
    public MainMenuPopup popupShouldNotBeVisible() {
        return null;
    }
}

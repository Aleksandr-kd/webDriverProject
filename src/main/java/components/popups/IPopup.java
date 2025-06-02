package components.popups;

public interface IPopup<T> {

    T popupShouldBeVisible();
    T popupShouldNotBeVisible();
}

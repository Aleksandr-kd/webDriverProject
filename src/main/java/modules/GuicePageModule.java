package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import pages.RegistrationFormPage;
import pages.TrainingPage;

public class GuicePageModule extends AbstractModule {

    private WebDriver driver;

    public GuicePageModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public RegistrationFormPage registrationFormPage(){
        return new RegistrationFormPage(driver);
    }

    @Provides
    @Singleton
    public TrainingPage trainingPage(){
        return new TrainingPage(driver);
    }
}

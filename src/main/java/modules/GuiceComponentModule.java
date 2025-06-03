package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.blocks.NewsBlock;
import org.openqa.selenium.WebDriver;

public class GuiceComponentModule extends AbstractModule {
    private WebDriver driver;

    public GuiceComponentModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public NewsBlock newsBlock(){
        return new NewsBlock(driver);
    }

}

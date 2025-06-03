package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dto.User;

public class GuiceDtoModule extends AbstractModule {

    @Provides
    public User getUser() {
        return new User();
    }
}

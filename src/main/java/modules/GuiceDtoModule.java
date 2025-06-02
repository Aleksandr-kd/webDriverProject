package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dto.User;

public class GuiceDtoModule extends AbstractModule {

    @Provides
    public User getUser() {
        return new User();
    }
}

package shekar.com.snakeview.injection.component;

import dagger.Component;
import javax.inject.Singleton;
import shekar.com.snakeview.data.DataManager;
import shekar.com.snakeview.injection.module.AppModule;
import shekar.com.snakeview.injection.module.NetworkModule;
import shekar.com.snakeview.utils.NetworkUtils;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
})
public interface ApplicationComponent {
    DataManager getDataManager();
    NetworkUtils getNetworkUtils();
}

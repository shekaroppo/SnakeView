package shekar.com.snakeview.injection.component;

import dagger.Component;
import shekar.com.snakeview.injection.module.ActivityModule;
import shekar.com.snakeview.injection.scope.ActivityScope;
import shekar.com.snakeview.ui.trends.MainActivity;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class
        })
public interface ActivityComponent {
    void inject(MainActivity twitterTrendsActivity);
}

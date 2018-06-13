package shekar.com.snakeview;

import android.app.Application;
import shekar.com.snakeview.injection.component.ApplicationComponent;
import shekar.com.snakeview.injection.component.DaggerApplicationComponent;
import shekar.com.snakeview.injection.module.AppModule;

public class TwitterTrendsApplication extends Application {

  private ApplicationComponent component;

  public ApplicationComponent getComponent() {
    if (component == null) {
      component = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }
    return component;
  }
}


package shekar.com.snakeview.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import shekar.com.snakeview.TwitterTrendsApplication;
import shekar.com.snakeview.injection.scope.ApplicationContext;

@Module
public class AppModule {
    private TwitterTrendsApplication application;

    public AppModule(TwitterTrendsApplication app) {
        application = app;
    }

  @Provides
  @ApplicationContext
  public Context provideContext(){
      return application.getApplicationContext();
  }
}

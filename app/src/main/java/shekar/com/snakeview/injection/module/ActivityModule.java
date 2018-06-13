package shekar.com.snakeview.injection.module;

import dagger.Module;
import dagger.Provides;
import shekar.com.snakeview.data.DataManager;
import shekar.com.snakeview.injection.scope.ActivityScope;
import shekar.com.snakeview.ui.trends.TwitterTrendsAdapter;
import shekar.com.snakeview.ui.trends.TwitterTrendsPresenter;
import shekar.com.snakeview.utils.NetworkUtils;

@Module
public class ActivityModule {

    @Provides
    @ActivityScope
    public TwitterTrendsAdapter provideTwitterTrendsAdapter() {
        return new TwitterTrendsAdapter();
    }

    @Provides
    @ActivityScope
    public TwitterTrendsPresenter provideTwitterTrendsPresenter(DataManager dataManager,NetworkUtils networkUtils) {
        return new TwitterTrendsPresenter(dataManager,networkUtils);
    }
}

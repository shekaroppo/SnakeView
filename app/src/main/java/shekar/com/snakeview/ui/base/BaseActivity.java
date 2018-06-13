package shekar.com.snakeview.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import shekar.com.snakeview.TwitterTrendsApplication;
import shekar.com.snakeview.injection.component.ActivityComponent;
import shekar.com.snakeview.injection.component.DaggerActivityComponent;
import shekar.com.snakeview.injection.module.ActivityModule;

public abstract class BaseActivity<B extends Presenter> extends AppCompatActivity {
  protected ActivityComponent component;

  @Inject protected B presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onComponentCreated(getComponent());
    presenter.attachView((MvpView) this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  public ActivityComponent getComponent() {
    if (component == null) {
      component = DaggerActivityComponent.builder()
          .applicationComponent(((TwitterTrendsApplication) getApplication()).getComponent())
          .activityModule(new ActivityModule())
          .build();
    }
    return component;
  }
  protected abstract void onComponentCreated(ActivityComponent component);
}

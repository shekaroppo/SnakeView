package shekar.com.snakeview.ui.base;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

  private T mMvpView;

  @Override public void attachView(T mvpView) {
    mMvpView = mvpView;
  }

  @Override public void detachView() {
    mMvpView = null;
  }

  protected T getMvpView() {
    return mMvpView;
  }
}


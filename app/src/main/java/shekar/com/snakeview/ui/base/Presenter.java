package shekar.com.snakeview.ui.base;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}

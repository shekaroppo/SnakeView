package shekar.com.snakeview.ui.trends;

import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import shekar.com.snakeview.data.DataManager;
import shekar.com.snakeview.data.model.ImageModel;
import shekar.com.snakeview.ui.base.BasePresenter;
import shekar.com.snakeview.utils.Constants;
import shekar.com.snakeview.utils.NetworkUtils;

public class TwitterTrendsPresenter extends BasePresenter<TwitterTrendsMvpView> {

  private final NetworkUtils networkUtils;
  private DataManager dataManager;

  public TwitterTrendsPresenter(DataManager dataManager, NetworkUtils networkUtils) {
    this.dataManager = dataManager;
    this.networkUtils = networkUtils;
  }

  public void loadTwitterTrends() {
    if (networkUtils.isConnected()) {
      getMvpView().showProgress();
     dataManager.getImages()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<ImageModel>() {
            @Override public void onCompleted() {
            }

            @Override public void onError(Throwable e) {
              getMvpView().showError(Constants.GENERIC_ERROR);
            }

            @Override public void onNext(ImageModel imageModels) {
              getMvpView().showContent(imageModels);
            }
          });
    } else {
      getMvpView().showError(Constants.NO_INTERNET_ERROR);
    }
  }
}

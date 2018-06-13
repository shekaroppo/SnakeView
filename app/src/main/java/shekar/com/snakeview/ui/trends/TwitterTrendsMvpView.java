package shekar.com.snakeview.ui.trends;

import shekar.com.snakeview.data.model.ImageModel;
import shekar.com.snakeview.ui.base.MvpView;

public interface TwitterTrendsMvpView extends MvpView {
  void showContent(ImageModel data);
}

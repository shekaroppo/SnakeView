package shekar.com.snakeview.ui.trends;

import java.util.List;

public interface TrendsListener {
  void onFailure();
  void onSuccess(List<String> result);
}

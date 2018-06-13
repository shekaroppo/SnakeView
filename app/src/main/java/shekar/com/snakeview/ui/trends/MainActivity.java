package shekar.com.snakeview.ui.trends;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import javax.inject.Inject;
import shekar.com.snakeview.R;
import shekar.com.snakeview.data.model.ImageModel;
import shekar.com.snakeview.databinding.ActivityTwitterTrendsBinding;
import shekar.com.snakeview.injection.component.ActivityComponent;
import shekar.com.snakeview.ui.base.BaseActivity;
import shekar.com.snakeview.ui.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;
import shekar.com.snakeview.ui.trends.LayoutManager.SpannedGridLayoutManager;
import shekar.com.snakeview.utils.InsetDecoration;

public class MainActivity extends BaseActivity<TwitterTrendsPresenter> implements
    TwitterTrendsMvpView {

  @Inject TwitterTrendsAdapter adapter;
  private ActivityTwitterTrendsBinding binding;
  private ItemTouchHelper mItemTouchHelper;

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_twitter_trends);
    setUI();
    presenter.loadTwitterTrends();
  }

  private void setUI() {
    binding.recyclerView.setHasFixedSize(true);
    final SpannedGridLayoutManager gridLayoutManager = new SpannedGridLayoutManager(new SpannedGridLayoutManager.GridSpanLookup() {
      @Override public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
        if (position == 0) {
          return new SpannedGridLayoutManager.SpanInfo(2, 2);
        } else {
          return new SpannedGridLayoutManager.SpanInfo(1, 1);
        }
      }
    },3,1f);
   // final RecyclerView.LayoutManager customLayoutManager=new CustomLayoutManager();
    binding.recyclerView.setLayoutManager(gridLayoutManager);
    binding.recyclerView.addItemDecoration(new InsetDecoration(this));     ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
    //mItemTouchHelper = new ItemTouchHelper(callback);
    //mItemTouchHelper.attachToRecyclerView(binding.recyclerView);
    binding.recyclerView.setAdapter(adapter);
    binding.commonErrorView.errorView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        presenter.loadTwitterTrends();
      }
    });
  }

  @Override public void showContent(ImageModel data) {
    binding.recyclerView.setVisibility(View.VISIBLE);
    binding.commonLoadingView.loadingView.setVisibility(View.GONE);
    binding.commonErrorView.errorView.setVisibility(View.GONE);
    adapter.setData(data);
  }

  @Override public void showProgress() {
    binding.recyclerView.setVisibility(View.GONE);
    binding.commonLoadingView.loadingView.setVisibility(View.VISIBLE);
    binding.commonErrorView.errorView.setVisibility(View.GONE);
  }

  @Override public void showError(String errorMessage) {
    binding.recyclerView.setVisibility(View.GONE);
    binding.commonLoadingView.loadingView.setVisibility(View.GONE);
    binding.commonErrorView.errorView.setVisibility(View.VISIBLE);
    binding.commonErrorView.errorView.setText(errorMessage);
  }

}

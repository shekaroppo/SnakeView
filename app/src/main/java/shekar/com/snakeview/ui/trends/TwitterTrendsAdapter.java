package shekar.com.snakeview.ui.trends;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import shekar.com.snakeview.R;
import shekar.com.snakeview.data.model.ImageModel;
import shekar.com.snakeview.data.model.Item;
import shekar.com.snakeview.databinding.ListItemBinding;
import shekar.com.snakeview.ui.itemtouchhelperdemo.helper.ItemTouchHelperAdapter;
import shekar.com.snakeview.utils.LayoutGravity;
import shekar.com.snakeview.utils.LayoutInfoLookup;
import shekar.com.snakeview.utils.SpanCount;

import static shekar.com.snakeview.utils.SpanCount.ONE;
import static shekar.com.snakeview.utils.SpanCount.TWO;

public class TwitterTrendsAdapter extends RecyclerView.Adapter<TwitterTrendsAdapter.ViewHolder> implements ItemTouchHelperAdapter {
  private final LayoutInfoLookup layoutInfoLookup = new LayoutInfoLookup() {
    @Override public SpanCount getRowSpan(int position) {
      if (position == 0) {
        return TWO;
      } else {
        return ONE;
      }
    }

    @Override public SpanCount getColumnSpan(int position) {
      if (position == 0) {
        return TWO;
      } else {
        return ONE;
      }
    }

    @Override public boolean useViewSize(int position) {
      return false;
    }

    @Override public LayoutGravity getGravity(int position) {
      return LayoutGravity.RIGHT;
    }
  };
  private List<Item> data = new ArrayList<>();

  public void setData(ImageModel data) {
    this.data = data.getItems();
    notifyDataSetChanged();
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Glide.with(holder.binder.image.getContext()).load(data.get(position).getImageUrlString()).into(holder.binder.image);
    holder.binder.position.setText(position + "");
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @Override public void onItemDismiss(int position) {
    data.remove(position);
    notifyItemRemoved(position);
  }

  @Override public boolean onItemMove(int fromPosition, int toPosition) {
    Collections.swap(data, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  public LayoutInfoLookup getLayoutInfoLookup() {
    return layoutInfoLookup;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding binder;

    ViewHolder(View view) {
      super(view);
      binder = DataBindingUtil.bind(view);
    }
  }
}
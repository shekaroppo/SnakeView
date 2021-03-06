package shekar.com.snakeview.ui.trends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayoutManager extends RecyclerView.LayoutManager {
  private int mFirstPosition;

  @Override public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  @Override public void scrollToPosition(int position) {
    super.scrollToPosition(position);
  }

  @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    final int parentBottom = getHeight() - getPaddingBottom();
    final View oldTopView = getChildCount() > 0 ? getChildAt(0) : null;
    int oldTop = getPaddingTop();
    if (oldTopView != null) {
      oldTop = oldTopView.getTop();
    }

    detachAndScrapAttachedViews(recycler);

    int top = oldTop;
    int bottom;
    final int left = getPaddingLeft();
    final int right = getWidth() - getPaddingRight();
    final int count = state.getItemCount();
    for (int i = 0; mFirstPosition + i < count && top < parentBottom; i++, top = bottom) {
      View v = recycler.getViewForPosition(mFirstPosition + i);
      addView(v, i);
      measureChildWithMargins(v, 0, 0);
      bottom = top + getDecoratedMeasuredHeight(v);
      layoutDecorated(v, left, top, right, bottom);
    }
  }

  @Override public boolean canScrollVertically() {
    return true;
  }

  @Override public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
    if (getChildCount() == 0) {
      return 0;
    }
    int scrolled = 0;
    final int left = getPaddingLeft();
    final int right = getWidth() - getPaddingRight();
    if (dy < 0) {
      while (scrolled > dy) {
        final View topView = getChildAt(0);
        final int hangingTop = Math.max(-getDecoratedTop(topView), 0);
        final int scrollBy = Math.min(scrolled - dy, hangingTop);
        scrolled -= scrollBy;
        offsetChildrenVertical(scrollBy);
        if (mFirstPosition > 0 && scrolled > dy) {
          mFirstPosition--;
          View v = recycler.getViewForPosition(mFirstPosition);
          addView(v, 0);
          measureChildWithMargins(v, 0, 0);
          final int bottom = getDecoratedTop(topView);
          final int top = bottom - getDecoratedMeasuredHeight(v);
          layoutDecorated(v, left, top, right, bottom);
        } else {
          break;
        }
      }
    } else if (dy > 0) {
      final int parentHeight = getHeight();
      while (scrolled < dy) {
        final View bottomView = getChildAt(getChildCount() - 1);
        final int hangingBottom =
            Math.max(getDecoratedBottom(bottomView) - parentHeight, 0);
        final int scrollBy = -Math.min(dy - scrolled, hangingBottom);
        scrolled -= scrollBy;
        offsetChildrenVertical(scrollBy);
        if (scrolled < dy && state.getItemCount() > mFirstPosition + getChildCount()) {
          View v = recycler.getViewForPosition(mFirstPosition + getChildCount());
          final int top = getDecoratedBottom(getChildAt(getChildCount() - 1));
          addView(v);
          measureChildWithMargins(v, 0, 0);
          final int bottom = top + getDecoratedMeasuredHeight(v);
          layoutDecorated(v, left, top, right, bottom);
        } else {
          break;
        }
      }
    }
    recycleViewsOutOfBounds(recycler);
    return scrolled;
  }
  private void recycleViewsOutOfBounds(RecyclerView.Recycler recycler) {
    final int childCount = getChildCount();
    final int parentWidth = getWidth();
    final int parentHeight = getHeight();
    boolean foundFirst = false;
    int first = 0;
    int last = 0;
    for (int i = 0; i < childCount; i++) {
      final View v = getChildAt(i);
      if (v.hasFocus() || (getDecoratedRight(v) >= 0 &&
          getDecoratedLeft(v) <= parentWidth &&
          getDecoratedBottom(v) >= 0 &&
          getDecoratedTop(v) <= parentHeight)) {
        if (!foundFirst) {
          first = i;
          foundFirst = true;
        }
        last = i;
      }
    }
    for (int i = childCount - 1; i > last; i--) {
      removeAndRecycleViewAt(i, recycler);
    }
    for (int i = first - 1; i >= 0; i--) {
      removeAndRecycleViewAt(i, recycler);
    }
    if (getChildCount() == 0) {
      mFirstPosition = 0;
    } else {
      mFirstPosition += first;
    }
  }

}

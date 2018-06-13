package shekar.com.snakeview.utils;

import android.content.Context;
import android.util.AttributeSet;

public class SquareImageview extends android.support.v7.widget.AppCompatImageView {
    public SquareImageview(Context context) {
        super(context);
    }

    public SquareImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
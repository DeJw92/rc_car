package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mikolaj on 03.01.15.
 */
public class VerticalControlBar extends ControlBar {

    public VerticalControlBar(Context context) {
        super(context);
    }

    public VerticalControlBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalControlBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
    }

    protected void onDraw(Canvas c) {
        c.rotate(270);
        c.translate(-getHeight(), 0);

        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                setProgress(getMax() - (int) (getMax() * event.getY() / getHeight()));
                updateProgressColor();
                onSizeChanged(getWidth(), getHeight(), 0, 0);

                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}

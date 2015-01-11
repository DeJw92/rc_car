package android.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mikolaj on 06.01.15.
 */
public class ControlBar extends SeekBar {
    private static final ColorDrawable redFill = new ColorDrawable(0xffdb3535);
    private static final ColorDrawable blueFill = new ColorDrawable(0xff5db6f6);

    private boolean isRedSet = false;

    public ControlBar(Context context) {
        super(context);
//        updateProgressColor();
    }

    public ControlBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        updateProgressColor();
    }

    public ControlBar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        updateProgressColor();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                updateProgressColor();
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public void updateProgressColor() {
        double progress = this.getProgress();
        double max = this.getMax();
        if( progress/max > 0.5) {
            if(isRedSet) {
                this.setProgressDrawable(redFill);
                isRedSet = false;
            }
        }
        else {
            if(!isRedSet) {
                this.setProgressDrawable(blueFill);
                isRedSet = true;
            }
        }
    }
}

package com.lucio.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by GoldMidai on 2016/9/7.
 */
public class NoDoubleClickButton extends Button {
    public NoDoubleClickButton(Context context) {
        super(context);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private  long lastClickTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long time = System.currentTimeMillis();
                if (time - lastClickTime < 1000) {
                    return false;
                }
                lastClickTime = time;
        }

        return super.onTouchEvent(event);
    }
}

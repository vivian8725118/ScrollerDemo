package com.vivian.scrollerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 16/7/4.
 */

public class ScrollerView extends LinearLayout {
    private int lastX = 0;
    private int X = 0;

    private int leftBorder;
    private int rightBorder;

    public ScrollerView(Context context) {
        super(context);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        setOrientation(HORIZONTAL);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                X = (int) event.getX();
                int delta = lastX - X;
                Log.e("ScrollerView","X="+X);
                Log.e("ScrollerView","delta="+delta);

                if (getScrollX() + delta <= leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + delta+getWidth() >= rightBorder) {
                    scrollTo(rightBorder-getWidth(), 0);
                    return true;
                } else {
                    scrollBy(delta, 0);
                }

                lastX = X;//把这句放到 move 里边会滑动的比较顺畅,放到 up 里边会滑的很快
                Log.e("ScrollerView","lastX="+lastX);
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }
}

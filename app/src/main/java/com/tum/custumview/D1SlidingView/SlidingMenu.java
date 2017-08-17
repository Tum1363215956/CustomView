package com.tum.custumview.D1SlidingView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Tumbleweeds on 2017/8/17.
 */

public class SlidingMenu extends ViewGroup{

    private View mLeftView;
    private View mContentView;
    private int mLeftWidth;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        mLeftView = getChildAt(0);
        mContentView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int leftMeasureSpec = MeasureSpec.makeMeasureSpec(mLeftWidth,MeasureSpec.EXACTLY);
        mLeftView.measure(leftMeasureSpec,heightMeasureSpec);

        mContentView.measure(widthMeasureSpec,heightMeasureSpec);

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //左侧布局
        int leftWith = mLeftView.getMeasuredWidth();
        int leftHeight = mLeftView.getMeasuredHeight();

        int lleft = -leftWith;
        int ltop = 0;
        int lright = 0;
        int lbottom = leftHeight;
        mLeftView.layout(lleft,ltop,lright,lbottom);

        //右侧布局
        mContentView.layout(0,0,mContentView.getMeasuredWidth(),mContentView.getMeasuredHeight());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}

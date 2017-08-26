package com.tum.custumview.D1SlidingView;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

/**
 * Created by Tumbleweeds on 2017/8/17.
 */

public class SlidingMenu extends ViewGroup{

    private View mLeftView;
    private View mContentView;
    private int mLeftWidth;

    private int mDownX,mDownY;

    private int mBaseX;//参考点

    private Scroller mScroller;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onFinishInflate() {
        mLeftView = getChildAt(0);
        mContentView = getChildAt(1);

        LayoutParams mLeftViewLayoutParams = mLeftView.getLayoutParams();
        mLeftWidth = mLeftViewLayoutParams.width;
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TGA","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("TGA","onInterceptTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {
                    // 水平方向移动
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TGA","onTouchEvent");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                int diffX = (int) (event.getX()- mDownX);
                int scrollX = getScrollX()-diffX;
                if(scrollX<0 && scrollX < -mLeftWidth){
                    scrollTo(-mLeftWidth,0);
                }else if(scrollX > 0){
                    scrollTo(0,0);
                }else {
                    scrollBy(-diffX, 0);
                }
                mDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                int curX = getScrollX();
                int middle = -mLeftWidth/2;
                sweepMenu(curX<=middle);
                break;
        }
        return true;
    }

    private void sweepMenu(boolean open) {

        int startX = 0,startY = 0;
        int dx = 0,dy = 0;
        startX = getScrollX();
        if(open){
            dx = -(startX + mLeftWidth);
        }else{
            dx = -startX;
        }

        mScroller.startScroll(startX,startY,dx,dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),0);
            invalidate();
        }
    }
}

package com.tum.custumview.D3ViewAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.tum.custumview.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class D3AnimationActivity extends AppCompatActivity {

    private Button mBtnAlpha,mBtnScale,mBtnRotate,mBtnTrans,mBtnSet;
    private TextView mTvAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        initListener();
    }

    private void initListener() {
        RxView.clicks(mBtnScale)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        xmlScaleAnimation();
                    }
                });
        RxView.clicks(mBtnAlpha)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        xmlAlphaAnimation();
                    }
                });
        RxView.clicks(mBtnRotate)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        xmlRotateAnimation();
                    }
                });
        RxView.clicks(mBtnTrans)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        xmlTranslateAnimation();
                    }
                });
        RxView.clicks(mBtnSet)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        xmlSetAnimation();
                    }
                });
    }

    private void initView() {
        mBtnAlpha = (Button)findViewById(R.id.btn_animation_alpha);
        mBtnScale = (Button)findViewById(R.id.btn_animation_scale);
        mBtnRotate = (Button)findViewById(R.id.btn_animation_rotate);
        mBtnTrans = (Button)findViewById(R.id.btn_animation_trans);
        mTvAnimation = (TextView)findViewById(R.id.tv_animation_view);
        mBtnSet = (Button)findViewById(R.id.btn_animation_set);
    }

    //=========================== viewd动画 ========================================
    private void viewScaleAnimation(){
        ScaleAnimation sa = new ScaleAnimation(0,2,0,2);
        sa.setDuration(1000);
        mTvAnimation.startAnimation(sa);
    }

    //=========================== xml动画 ==========================================
    private void xmlScaleAnimation(){
        Animation scaleAnimation = AnimationUtils.loadAnimation(D3AnimationActivity.this
            ,R.anim.scale);
        mTvAnimation.startAnimation(scaleAnimation);
    }

    private void xmlAlphaAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        mTvAnimation.startAnimation(animation);
    }
    private void xmlRotateAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        mTvAnimation.startAnimation(animation);
    }

    private void xmlTranslateAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
        mTvAnimation.startAnimation(animation);
    }

    private void xmlSetAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.set);
        mTvAnimation.startAnimation(animation);
    }

}

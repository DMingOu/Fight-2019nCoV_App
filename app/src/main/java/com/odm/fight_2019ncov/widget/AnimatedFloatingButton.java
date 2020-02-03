package com.odm.fight_2019ncov.widget;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @description: 带动画的悬浮按钮
 * @author: ODM
 * @date: 2020/2/3
 */
public class AnimatedFloatingButton extends FloatingActionButton {

    ViewPropertyAnimator animator ;
    boolean isAnimating = false;

    Animator.AnimatorListener  animatorListener;

    public AnimatedFloatingButton(@NonNull Context context) {
        super(context);
        LogUtils.d("第一层构造");
        initAnimation();
    }

    /**
     * 静态添加View时的findViewById 直接使用此构造方法
     * @param context
     * @param attrs
     */
    public AnimatedFloatingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LogUtils.d("第二层构造");
        initAnimation();
    }

    /**
     * 动态添加View，最终调用的 构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public AnimatedFloatingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtils.d("第三层构造");
        initAnimation();
    }

    private void initAnimation(){
        //初始化 ViewPropertyAnimator
        if (animator == null) {
            animator = this.animate();
            animator = this.animate();
            animator.setDuration(300);
            animator.setInterpolator(new LinearInterpolator());
        }

        if(animatorListener == null) {
            //初始化 动画监听事件
            animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimating = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimating = false;
                    setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAnimating = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
        }

    }

    /**
     * 先可视性可见-->展示动画
     * 默认动画是 缩放动画 放大
     */
    public void showAnimation(){
        if(animator != null && getVisibility() != View.VISIBLE) {
            setVisibility(View.VISIBLE);
            animator.scaleX(1.0f)
                    .scaleY(1.0f)
                    .alpha(1.0f)
                    .setListener(null);
        } else {
                LogUtils.e("None：animator != null && getVisibility() != View.VISIBLE ");
            }
    }

    /**
     * 先展示动画-->可视性隐藏
     * 默认动画是 缩放动画 缩小
     */
    public void hideAnimation(){
        if(animator != null && animatorListener!= null && getVisibility()== View.VISIBLE) {
            if(! isAnimating) {
                animator.scaleX(0.0f)
                        .scaleY(0.0f)
                        .alpha(0.0f)
                        .setListener(animatorListener);
            }
        }else {
            LogUtils.e("None：animator != null && animatorListener!= null && getVisibility()== View.VISIBLE ");
        }

    }





}

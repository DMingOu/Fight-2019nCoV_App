package com.odm.fight_2019ncov.widget;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;


/**
 * @description: 带动画的悬浮按钮
 * @author: ODM
 * @date: 2020/2/3
 */
public class AnimatedFloatingButton extends FloatingActionButton {

    private static final String TAG = "AnimatedFloatingButton";

    /**
     * 内置的动画类型
     */
    public enum  AnimationType {
        /**
         * 缩放
         */
        SCALE,
        /**
         * 平移,向下隐藏，向上出场
         */
        TRANSLATION_DOWN_UP,
        /**
         * 平移,向上隐藏，向下出场
         */
        TRANSLATION_UP_DOWN,
        /**
         * 无动画效果
         */
        NONE
    }


    /**
     * 默认动画类型
     */
    AnimationType animationType;
    /**
     * 默认动画
     */
    ViewPropertyAnimator animator ;
    /**
     * 扩展:自定义动画效果
     */
    Animator customShowAnimator;
    Animator customHideAnimator;

    /**
     * 默认动画：判断正在动画的控制变量
     */
    boolean isAnimating = false;

    Animator.AnimatorListener  hideAnimatorListener;
    Animator.AnimatorListener  showAnimatorListener;

    /**
     * 上下文
     */
    Context mContext;

    /**
     * 屏幕的宽度，高度像素大小
     */
    int mScreenHeight;
    int mScreenWidth;

    /**
     * 构造方法
     * @param context
     */
    public AnimatedFloatingButton(@NonNull Context context) {
        super(context);
        mContext = context;
        initAnimation();
    }

    /**
     * 静态添加View时的findViewById 直接使用此构造方法
     * @param context
     * @param attrs
     */
    public AnimatedFloatingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
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
        mContext = context;
        initAnimation();
    }


    private void initAnimation(){
        //初始化默认的动画
        initDefaultAnimator();

        //默认无动画效果
        if(animationType == null) {
            animationType = AnimationType.NONE;
        }
        if(hideAnimatorListener == null) {
            //初始化 隐藏动画监听事件
            hideAnimatorListener = new Animator.AnimatorListener() {
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
        if(showAnimatorListener == null) {
            //初始化 展示动画监听事件
            showAnimatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimating = true;
                    setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimating = false;
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


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimation();
    }

    /**
     * 先令控件可见-->展示动画
     * 默认动画是 缩放动画 放大
     */
    public void showAnimation(){
        if(customShowAnimator !=  null && ! customShowAnimator.isStarted()  ) {
            customShowAnimator.start();
            return;
        }
        if(showAnimatorListener != null && getVisibility() != View.VISIBLE) {
            //调用自带的默认动画
            if(customShowAnimator == null ) {
                if(animator == null) {
                    initDefaultAnimator();
                }
                animator.setListener(showAnimatorListener);
                executeShowAnimationByType(animationType);
            }
        }

    }

    /**
     * 先展示动画-->可视性隐藏
     * 默认动画是 缩放动画 缩小
     */
    public void hideAnimation(){
        if(customHideAnimator !=  null && ! customHideAnimator.isStarted()) {
            customHideAnimator.start();
            return;
        }
        if(hideAnimatorListener!= null && getVisibility() == View.VISIBLE) {
            //调用自带的默认动画
            if(customHideAnimator == null ) {
                if(animator == null) {
                    initDefaultAnimator();
                }
                animator.setListener(hideAnimatorListener);
                executeHideAnimationByType(animationType);
            }

        }

    }

    /**
     * 取消动画效果
     */
    public void cancelAnimation(){
        if(animator != null && isAnimating) {
            animator.cancel();
        }
        if(customShowAnimator != null && customShowAnimator.isStarted()) {
            customShowAnimator.cancel();
        }
        if(customHideAnimator !=  null && customHideAnimator.isStarted()) {
            customHideAnimator.cancel();
        }
    }



    /**
     * 获取屏幕像素数据
     * note： getXXX 方法 获取到的是px像素
     */
    private void getScreenInfo(){
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mScreenHeight = metrics.heightPixels;
        mScreenWidth = metrics.widthPixels;
    }

    private  int getScreenHeight() {
        if(mScreenHeight == 0){
            getScreenInfo();
        }
        return mScreenHeight;
    }

    private  int getScreenWidth() {
        if(mScreenWidth == 0){
            getScreenInfo();
        }
        return mScreenWidth;
    }

    /**
     * 外部调用，设置动画类型
     * @param animationType 动画类型
     */
    public void setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
    }



    /**
     * 初始化 默认动画的属性
     */
    private void initDefaultAnimator(){
        if(animator == null) {
            animator = this.animate();
            animator.setDuration(500);
            animator.setInterpolator(new LinearInterpolator());
        }
    }



    /**
     * 根据动画类型执行 展示动画
     * @param type 动画类型
     */
    private void executeShowAnimationByType(AnimationType type) {
        if(! isAnimating) {
            switch (type){
                case SCALE:
                    showScale();
                    break;
                case TRANSLATION_DOWN_UP:
                    upShowTranslation();
                    break;
                case TRANSLATION_UP_DOWN:
                    downShowTranslation();
                case NONE:
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 根据动画类型执行 隐藏动画
     * @param type 动画类型
     */
    private void executeHideAnimationByType(AnimationType type) {
        if(! isAnimating) {
            switch (type){
                case SCALE:
                    hideScale();
                    break;
                case TRANSLATION_DOWN_UP:
                    downHideTranslation();
                    break;
                case TRANSLATION_UP_DOWN:
                    upHideTranslation();
                case NONE:
                    break;
                default:
                    break;
            }
        }
    }



    /**
     * 放大出场
     */
    private void showScale(){
        animator.scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f);
    }

    /**
     * 缩小隐藏
     */
    private void hideScale() {
        animator.scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f);
    }

    /**
     * 向上显示
     * 无需设置向上平移值，设置View.VISIBLE 可自动平移到原来的位置
     */
    private void upShowTranslation(){
        animator.translationX(0.0f)
                .translationY( 0.0f )
                .alpha(1.0f);
    }

    /**
     * 向下隐藏
     */
    private void downHideTranslation(){
        float y = getScreenHeight() - getTop();
        animator.translationX(0.0f)
                .translationY( y )
                .alpha(1.0f);
    }

    /**
     * 向下出场
     * 无需设置向上平移值，设置View.VISIBLE 可自动平移到原来的位置
     */
    private void  downShowTranslation(){
        animator.translationX(0.0f)
                .translationY( 0.0f )
                .alpha(1.0f);
    }

    /**
     * 向上隐藏
     */
    private void upHideTranslation (){
        float y = getBottom();
        animator.translationX(0.0f)
                .translationY( -y )
                .alpha(1.0f);
    }



    /**
     * 设置自定义动画
     * @param showAnim  出场控件动画
     * @param hideAnim  隐藏控件动画
     */
    public void setCustomAnimator(@NotNull Animator showAnim , @NotNull Animator hideAnim) {
            this.customShowAnimator = showAnim;
            this.customHideAnimator = hideAnim;
    }



}

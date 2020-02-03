package com.odm.fight_2019ncov.widget

import android.animation.Animator
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.blankj.utilcode.util.LogUtils


/**
 * @description: View 动画工具类，每次指定一个View
 * @author: ODM
 * @date: 2020/1/28
 */
class ViewAnimatorHelper {

    private var isAnimating = false
    private var viewPropertyAnimator: ViewPropertyAnimator? = null
    private var view: View? = null

    /**
     * 在 onStart 生命周期绑定
     */
    fun bindView(view: View?) {
        if (view == null) throw NullPointerException("The view should not be null") as Throwable
        this.view = view
        if (viewPropertyAnimator == null) {
            viewPropertyAnimator = view.animate()
            viewPropertyAnimator?.duration = 300
            viewPropertyAnimator?.interpolator = LinearOutSlowInInterpolator()
        }
    }

    /**
     * onStop 解除绑定
     */
    fun unBindView() {
        this.view = null
        this.viewPropertyAnimator = null
    }

    /**
     * 缩放动画，
     */
    fun showAndScale() {
        view?.visibility = View.VISIBLE
        viewPropertyAnimator?.scaleX(1.0f)?.scaleY(1.0f)?.alpha(1.0f)?.setListener(null)
    }

    fun hideAndScale() {
        viewPropertyAnimator?.scaleX(0.0f)?.scaleY(0.0f)?.alpha(0.0f)?.setListener(animationListener)
    }

    private val animationListener: Animator.AnimatorListener =
        object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                isAnimating = true
            }

            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
                view?.visibility = View.INVISIBLE

            }

            override fun onAnimationCancel(animation: Animator) {
                isAnimating = false
            }

            override fun onAnimationRepeat(animation: Animator) {}
        }

    fun isAnimating(): Boolean {
        return isAnimating
    }
}
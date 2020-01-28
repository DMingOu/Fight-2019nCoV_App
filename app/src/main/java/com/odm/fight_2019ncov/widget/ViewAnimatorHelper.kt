package com.odm.fight_2019ncov.widget

import android.animation.Animator
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator


/**
 * @description: View 动画工具类
 * @author: ODM
 * @date: 2020/1/28
 */
class ViewAnimatorHelper {

    private var isAnimating = false
    private var viewPropertyAnimator: ViewPropertyAnimator? = null
    private var view: View? = null
    fun bindView(view: View?) {
        if (view == null) throw NullPointerException("The view should not be null")
        this.view = view
        if (viewPropertyAnimator == null) {
            viewPropertyAnimator = view.animate()
            viewPropertyAnimator?.duration = 300
            viewPropertyAnimator?.interpolator = LinearOutSlowInInterpolator()
        }
    }

    fun showFloatActionButton() {
        view?.visibility = View.VISIBLE
        viewPropertyAnimator?.scaleX(1.0f)?.scaleY(1.0f)?.alpha(1.0f)?.setListener(null)
    }

    fun hideFloatActionButton() {
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
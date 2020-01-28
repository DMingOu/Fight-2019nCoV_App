package com.odm.fight_2019ncov.widget

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils


/**
 * @description: 滚动列表自定义布局管理器(自定义SmoothScroll 方法)
 * @author: ODM
 * @date: 2020/1/28
 */
class SmoothScrollLayoutManager(context: Context?) : LinearLayoutManager(context) {
    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val smoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView.context) {
                // 返回：滑过1px时经历的时间(ms)。
                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return 50f / displayMetrics.densityDpi
                }

                override fun getHorizontalSnapPreference(): Int {
                    return SNAP_TO_START //具体见源码注释
                }

                override fun getVerticalSnapPreference(): Int {
                    return SNAP_TO_START //具体见源码注释
                }
            }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }
}
package com.odm.fight_2019ncov.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @description: 网页装载容器Activity
 * @author: ODM
 * @date: 2020/1/26
 */
abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //锁定旋转屏幕--只能竖屏使用
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initViews()
    }

    abstract fun initViews()

    /**
     * 返回页面布局的ID
     *
     * @return the layout id
     */
    abstract val layoutId: Int

}
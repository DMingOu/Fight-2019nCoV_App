package com.odm.fight_2019ncov.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chaychan.library.BottomBarLayout
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseActivity
import com.odm.fight_2019ncov.ui.main.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    var bottomNavigation : BottomBarLayout ?= null

    private val viewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setFragmentByPosition(0)
    }

    override fun initViews() {
        bottomNavigation = findViewById(R.id.bbl_main)
        bottomNavigation?.setOnItemSelectedListener { bottomBarItem, i, i2 ->
            if (viewModel.lastFragmentIndex != i2) {
                //切换页面
                setFragmentByPosition(i2)
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    fun setFragmentByPosition(position: Int) {

        val ft = supportFragmentManager.beginTransaction()
        //设置进场、退场动画
//        ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out, R.anim.push_left_out, R.anim.push_right_out)
        ft.addToBackStack(null)
        val targetFragment : Fragment = viewModel.mFragments?.get(position) ?: Fragment()
        val lastFragment : Fragment = viewModel.mFragments?.get(viewModel.lastFragmentIndex ?: 0) ?: Fragment()
        ft.hide(lastFragment)
        //如果目标Fragment已经添加，则remove掉重新加入
        if (targetFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .remove(targetFragment)
                .commitNow()
        }
        ft.replace(R.id.frl_container, targetFragment)
            .show(targetFragment)
            .commitAllowingStateLoss()
        //立刻执行操作
        supportFragmentManager.executePendingTransactions()
        viewModel.lastFragmentIndex = position

    }

    /**
     * 处理返回键事件 ： 直接退出App
     */
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}

package com.odm.fight_2019ncov.ui.main

import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.odm.fight_2019ncov.base.BaseViewModel
import com.odm.fight_2019ncov.ui.LatestNews.LatestNewsFragment
import com.odm.fight_2019ncov.ui.situation.SituationFragment

/**
 * @description: 主页面ViewModel
 * @author: ODM
 * @date: 2020/1/27
 */
class MainViewModel : BaseViewModel() {


    //Fragment对象列表
    var mFragments: MutableList<Fragment>? = null
    //目前展示Fragment的位置
    var lastFragmentIndex = 0

    init {
        initFragmentData()
    }





    /**
     * 初始化Fragment对象列表
     */
    fun initFragmentData() {
        mFragments = ArrayList()
        mFragments?.add(LatestNewsFragment())
        mFragments?.add(SituationFragment())

    }
}
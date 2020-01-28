package com.odm.fight_2019ncov.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.odm.fight_2019ncov.R

/**
 * @description: Fragment 基类
 * @author: ODM
 * @date: 2020/1/26
 */
abstract class BaseFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        return view
    }

    /**
     * 初始化页面的ViewModel和DataBinding
     */
//     fun initViewDataBinding(inflater: LayoutInflater, container: ViewGroup?)



//    abstract val isBackToFinish : Boolean

    abstract fun initViews()
    /**
     * 返回页面布局的ID
     *
     * @return the layout id
     */
    abstract val layoutId: Int
}
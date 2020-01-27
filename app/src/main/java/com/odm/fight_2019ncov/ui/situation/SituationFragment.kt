package com.odm.fight_2019ncov.ui.situation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.ybq.android.spinkit.SpinKitView
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseFragment
import org.koin.android.ext.android.inject

/**
 * @description: 情况 页面层
 * @author: ODM
 * @date: 2020/1/27
 */
class SituationFragment : BaseFragment() {

    var rvAreaSet : RecyclerView ?= null
    var rvAdapter  : AreaSituationAdapter ?= null
    var loading : SpinKitView?= null
    private val viewModel : SituationViewModel by  inject()

    override val layoutId: Int
        get() = R.layout.fragment_situation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_situation, container, false)
        viewModel.getAllInfoRaw()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initObservableLiveData()
    }

    override fun initViews() {
        loading = activity?.findViewById(R.id.loading_situation)
        if(loading?.visibility != View.VISIBLE) {
            loading?.visibility = View.VISIBLE
        }

        rvAreaSet = activity?.findViewById(R.id.rv_situation_area)
        rvAdapter = AreaSituationAdapter()
        rvAreaSet?.layoutManager = LinearLayoutManager(requireContext())
        rvAreaSet?.adapter = rvAdapter
        rvAdapter?.animationEnable = true
//        rvAdapter?.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)
    }



    private fun initObservableLiveData() {

        viewModel.apply {
            areaSituation.observe(this@SituationFragment, Observer {
                if(rvAdapter == null)  return@Observer
                rvAdapter?.setNewData(it.toMutableList())
//                rvAdapter?.notifyDataSetChanged()
                loading?.visibility = View.GONE
            })
        }
    }


    }


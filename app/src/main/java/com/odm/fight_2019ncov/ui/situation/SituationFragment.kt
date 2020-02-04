package com.odm.fight_2019ncov.ui.situation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.animation.BaseAnimation
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
//        viewModel.getAllInfoRaw()
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
        rvAdapter?.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)
    }



    @SuppressLint("SetTextI18n")
    private fun initObservableLiveData() {

        viewModel.apply {
            staticSituation.observe(this@SituationFragment , Observer {
                if(rvAdapter == null)  return@Observer
                //全国整体情况
                val countryHeaderView : View = layoutInflater.inflate(R.layout.item_situation_country, rvAreaSet, false)
                val tvRefreshTime : TextView = countryHeaderView.findViewById(R.id.tv_time_item_situation_country)
                tvRefreshTime.text = " 更新时间：\n      ${TimeUtils.millis2String(it.modifyTime)}"
                val tvNumber: TextView = countryHeaderView.findViewById(R.id.tv_number_item_situation_country)
                tvNumber.text = " 全国：\n     确诊${it?.confirmedCount}例  疑似${it?.suspectedCount}例  治愈${it?.curedCount}例  死亡${it?.deadCount}例"
                val tvPassWay : TextView = countryHeaderView.findViewById(R.id.tv_passWay_item_situation_country)
                tvPassWay.text = " 传播途径：\n     经呼吸道飞沫传播，亦可通过接触传播，存在粪-口传播可能性"
                val ivMapCountry : ImageView = countryHeaderView.findViewById(R.id.iv_map_situation_country)
                Glide.with(this@SituationFragment)
                    .load(it.imgUrl)
                    .into(ivMapCountry)

                rvAdapter?.addHeaderView(countryHeaderView)
                //关闭 Loading动画
                loading?.animation?.cancel()
                loading?.visibility = View.INVISIBLE
            })
        }
        viewModel.apply {
            areaSituation.observe(this@SituationFragment, Observer {
                if(rvAdapter == null)  return@Observer
                rvAdapter?.setNewData(it.toMutableList())
            })
        }
    }


    }


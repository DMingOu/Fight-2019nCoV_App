package com.odm.fight_2019ncov.ui.LatestNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseFragment
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject

/**
 * @description: 24小时内最新消息 页面
 * @author: ODM
 * @date: 2020/1/26
 */
class LatestNewsFragment : BaseFragment() {

    var ivCheer : ImageView ?= null
    var rvNews : RecyclerView ?= null
    var rvAdapter : LatestNewsAdapter ?= null

    //依赖注入获取ViewModel实例
     val viewModel: LatestNewsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lateset_news, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initObservableLiveData()
    }

    /**
     * 初始化控件
     * 不可以在onCreateView中调用否则导致异常 E/RecyclerView: No adapter attached; skipping layout
     */
    override fun initViews() {
        ivCheer = activity?.findViewById(R.id.iv_banner)
        rvNews = activity?.findViewById(R.id.rv_latest_news)
        //初始化 RecyclerView 的适配器
        LogUtils.e("初始化Adapter")
        rvAdapter = LatestNewsAdapter(mutableListOf())
        rvNews?.layoutManager = LinearLayoutManager(this.context)
        rvNews?.adapter = rvAdapter
        rvAdapter?.setOnItemClickListener { adapter, view, position ->
            //Todo 启动WebView网页
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_lateset_news


    private fun initObservableLiveData() {
        //对ViewModel的数据进行观察，方式1
//        viewModel.bannerList.observe(this , Observer {
//            tvData?.text = it?.get(1)?.url
//        } )
        //对ViewModel的数据进行观察，方式2
        viewModel.apply {
            newsList.observe(this@LatestNewsFragment, Observer {
                if(rvAdapter == null)  LogUtils.e("rvAdapter 为空")
//                rvAdapter?.data = it.toMutableList()
                rvAdapter?.addData(it.toMutableList())
                Log.e(tag,it.toMutableList().toString())
                rvAdapter?.notifyDataSetChanged()
                LogUtils.e("刷新数据")
            })
        }
    }









}






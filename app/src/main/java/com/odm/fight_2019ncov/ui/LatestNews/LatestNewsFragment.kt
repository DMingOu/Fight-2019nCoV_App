package com.odm.fight_2019ncov.ui.LatestNews

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.odm.fight_2019ncov.Constants
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseFragment
import com.odm.fight_2019ncov.ui.AgentWebActivity
import com.odm.fight_2019ncov.ui.WebContainerActivity
import com.odm.fight_2019ncov.widget.AnimatedFloatingButton
import com.odm.fight_2019ncov.widget.SmoothScrollLayoutManager
import com.odm.fight_2019ncov.widget.ViewAnimatorHelper
import org.koin.android.ext.android.inject


/**
 * @description: 实时事件 页面
 * @author: ODM
 * @date: 2020/1/26
 */
class LatestNewsFragment : BaseFragment() {

    //首页横幅（暂时以单张图片代替）
    var ivCheer : ImageView ?= null
    //新闻滚动列表
    var rvNews : RecyclerView ?= null
    //滚动列表适配器
    var rvAdapter : LatestNewsAdapter ?= null

    //加载动画
    var loading : SpinKitView ?= null

    //依赖注入获取ViewModel实例
    val viewModel: LatestNewsViewModel by inject()

    //置顶功能悬浮按钮
    var btnTop : AnimatedFloatingButton ?= null

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
        loading = activity?.findViewById(R.id.spin_kit)
        if(loading?.visibility != View.VISIBLE) {
            loading?.visibility = View.VISIBLE
        }

        btnTop = activity?.findViewById(R.id.afb_top)

        ivCheer = activity?.findViewById(R.id.iv_banner)

        rvNews = activity?.findViewById(R.id.rv_latest_news)

        //初始化 RecyclerView 的适配器
        rvAdapter = LatestNewsAdapter(mutableListOf())
        rvNews?.layoutManager = SmoothScrollLayoutManager(activity)
        rvNews?.addOnScrollListener(onScrollListener)

        rvNews?.adapter = rvAdapter
        rvAdapter?.animationEnable = true
        rvAdapter?.setOnItemClickListener { adapter, view, position ->
            openWebViewActivity(rvAdapter?.getItem(position)?.sourceUrl ?: "")
        }
        //动态添加RecyclerView 的头布局
        val bannerHeaderView : View = layoutInflater.inflate(R.layout.item_latest_news_header, rvNews, false)
        rvAdapter?.addHeaderView(bannerHeaderView)


        //初始化置顶按钮的点击事件
        btnTop = activity?.findViewById(R.id.afb_top)
        btnTop?.setOnClickListener {
            rvNews?.smoothScrollToPosition(0)
        }

    }

    override val layoutId: Int
        get() = R.layout.fragment_lateset_news


    private fun initObservableLiveData() {

        //对ViewModel的数据进行观察，方式2
        viewModel.apply {
            newsList.observe(this@LatestNewsFragment, Observer {
                if(rvAdapter == null)  return@Observer
                rvAdapter?.setNewData(it.toMutableList())
                loading?.animation?.cancel()
                loading?.visibility = View.INVISIBLE
            })
        }
    }

    private fun openWebViewActivity( url : String) {
        val intent = Intent()
        intent.putExtra(Constants.WEB_URL , url)
        intent.setClass(requireContext() , AgentWebActivity::class.java)
        startActivity(intent)
    }

    private val onScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) {
                    btnTop?.hideAnimation()
                } else {
                    btnTop?.showAnimation()
                }
            }
        }









}






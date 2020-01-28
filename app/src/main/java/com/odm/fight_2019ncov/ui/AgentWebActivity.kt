package com.odm.fight_2019ncov.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.just.agentweb.AgentWeb
import com.odm.fight_2019ncov.Constants
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseActivity


class AgentWebActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_agentweb_container

    var urlString : String  ?= null
    lateinit var mAgentWeb : AgentWeb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        val sIntent = intent
        urlString = sIntent.getStringExtra(Constants.WEB_URL)
        initViews()
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb.webLifeCycle.onDestroy()
    }

    override fun initViews() {
       mAgentWeb =  AgentWeb.with(this) //传入Activity
            .setAgentWebParent(
                getAgentWebParent(),
                FrameLayout.LayoutParams(-1, -1)
            ) //传入AgentWeb 的父控件
            .useDefaultIndicator(R.color.colorAccent) // 使用默认进度条,使用默认进度条颜色
            .createAgentWeb()
            .ready()
            .go(urlString)
    }

    override fun onBackPressed() {
        if (! mAgentWeb.back()){
            super.onBackPressed()
            this.finish();
        }
    }


    fun getAgentWebParent(): ViewGroup {
        return this.findViewById(R.id.frL_agentweb_container)
     }




}

package com.odm.fight_2019ncov.ui

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.odm.fight_2019ncov.Constants
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseActivity

class WebContainerActivity : BaseActivity() {


    var mLayout : FrameLayout?= null
    var mWebView : WebView?= null
    var mWebSettings: WebSettings?= null

    var urlString : String  ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_container)
        val sIntent = intent
        urlString = sIntent.getStringExtra(Constants.WEB_URL)
        initViews()
    }

    override fun initViews() {
        mLayout = findViewById(R.id.frl_web_container)

        //动态添加WebView对象
        val parms = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        mWebView = WebView(applicationContext)
        mWebView?.layoutParams = parms
        mLayout?.addView(mWebView)
        initWebView(urlString)
    }

    override val layoutId: Int
        get() = R.layout.activity_web_container


    fun initWebView(url : String ?){
        mWebSettings = mWebView?.settings
        //允许WebView启用Js
        mWebSettings?.javaScriptEnabled = true
        mWebView?.loadUrl(url)

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebView?.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                println("开始加载")
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                println("结束加载")
                super.onPageFinished(view, url)
            }
        })

        //设置WebChromeClient类
        mWebView?.setWebChromeClient(object : WebChromeClient() {
            //获取网站标题
            override fun onReceivedTitle(
                view: WebView,
                title: String
            ) {
                println("标题在这里  $title")
            }

            //获取加载进度
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                if (newProgress < 100) {
                    val progress = "$newProgress%"
//                    loading.setText(progress)
                } else if (newProgress == 100) {
                    val progress = "$newProgress%"
//                    loading.setText(progress)
                }
            }
        })


    }

    //点击返回上一页面而不是退出浏览器
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView?.canGoBack() == true) {
            mWebView?.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        //销毁WebView
        mWebView?.let {
            it.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            it.clearHistory()

            (it.parent as ViewGroup).removeView(it)
            it.destroy()
            println("销毁WebView")
        }
        mWebView = null
    }
}

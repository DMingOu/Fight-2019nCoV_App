package com.odm.fight_2019ncov.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.base.BaseActivity

class WebContainerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_container)
    }

    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val layoutId: Int
        get() = R.layout.activity_web_container
}

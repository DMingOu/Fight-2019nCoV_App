package com.odm.fight_2019ncov.model.net

import android.content.Context
import android.net.ConnectivityManager

/**
 * @description: 网络请求工具类
 * @author: ODM
 * @date: 2020/1/27
 */

class NetWorkUtils {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}
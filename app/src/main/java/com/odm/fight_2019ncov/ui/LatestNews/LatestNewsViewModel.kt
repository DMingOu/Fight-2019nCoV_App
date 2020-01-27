package com.odm.fight_2019ncov.ui.LatestNews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.odm.fight_2019ncov.base.BaseViewModel
import com.odm.fight_2019ncov.model.entity.LatestNews
import java.io.Reader
import java.lang.reflect.Type


/**
 * @description: 实时最新消息 ViewModel
 * @author: ODM
 * @date: 2020/1/27
 */
class LatestNewsViewModel (private val repository : LatestNewsRepository) : BaseViewModel() {

    //viewModel内部更新的MutableLiveData
    private val _newsList  =  MutableLiveData<List<LatestNews>>()
    //供外部调用的LiveData
    val newsList : LiveData<List<LatestNews>> = _newsList


    companion object{
        const val tag = "MainViewModel"
    }

    init {
        //初始化ViewModel时自动拉取数据
        getLatestNewsRaw()
    }


    fun getLatestNews() {

/*        launch{
            val result = repository.suspendGetLatestNewsData()
            if(result  is ApiResult.Success) {
                _newsList.value = result.data
                LogUtils.e("获取  新闻数据")
            }
            if(result is ApiResult.Error) {
                LogUtils.e("获取  新闻数据 失败 "  + result.exception.message)
                LogUtils.e(result.toString())
            }
        }*/
    }

    fun getLatestNewsRaw() {
            launch {
                val result = repository.suspendGetLatestNewsRawData()
                val reader: Reader = result.body()!!.charStream()
                if(result.raw().isSuccessful) {
                    val listType: Type =  object : TypeToken<List<LatestNews>>() {}.type
                    val list: List<LatestNews> = Gson().fromJson(reader, listType)
                    _newsList.value  =  list
                } else {
                    LogUtils.e("请求失败")
                }
            }
    }

}
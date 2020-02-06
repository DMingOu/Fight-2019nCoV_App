package com.odm.fight_2019ncov.ui.situation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.odm.fight_2019ncov.base.BaseViewModel
import com.odm.fight_2019ncov.model.entity.*
import com.odm.fight_2019ncov.model.net.ApiResult
import com.odm.fight_2019ncov.model.net.ApiService
import java.io.Reader
import java.lang.reflect.Type

/**
 * @description: 情况模块 ViewModel层
 * @author: ODM
 * @date: 2020/1/27
 */
class SituationViewModel (private val repository: SituationRepository) : BaseViewModel(){


    //viewModel内部更新的MutableLiveData
    private val _areaSituationList  =  MutableLiveData<List<GetAreaStat>>()
    //供外部调用的LiveData
    val areaSituation : LiveData<List<GetAreaStat>> = _areaSituationList
    //整体情况
    private val _staticSituation = MutableLiveData<GetStatisticsService>()
    val staticSituation : LiveData<GetStatisticsService> = _staticSituation

    init {
        getAllInfoRaw()
    }


/*    fun getSituationAll() {
        launch{
            val result = repository.suspendGetAllData()
            if(result  is ApiResult.Success) {
                _areaSituationList.value = result.data.getAreaStat
//                LogUtils.e(_areaSituationList.value.toString())
            }
            if(result is ApiResult.Error) {
                LogUtils.e("获取  all 数据 失败 "  + result.exception.message)
                LogUtils.e(result.toString())
            }
        }
    }*/


    private fun getAllInfoRaw() {
        launch {
            val result = repository.suspendAllRawData()
            val reader: Reader = result.body()!!.charStream()
            if(result.raw().isSuccessful) {
                val allInfoObject : AllSituation = Gson().fromJson(reader, ApiService.allInfoType)
                _areaSituationList.value = allInfoObject.getAreaStat
                _staticSituation.value = allInfoObject.getStatisticsService
            } else {
                ToastUtils.showShort("请求数据出现异常")
            }
        }
    }

/*    fun getRumorData(){
        launch {
            val result = repository.suspendGetRumorListData()
            if(result is ApiResult.Success) {
                val rumorList = result.data
                LogUtils.d(rumorList)
            }
            if(result is ApiResult.Error){
                LogUtils.d("谣言数据转换失败  " + result.exception.message)
            }
        }
    }*/


}
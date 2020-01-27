package com.odm.fight_2019ncov.ui.situation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.odm.fight_2019ncov.base.BaseViewModel
import com.odm.fight_2019ncov.model.entity.AllSituation
import com.odm.fight_2019ncov.model.entity.AreaSituation
import com.odm.fight_2019ncov.model.entity.GetAreaStat
import com.odm.fight_2019ncov.model.entity.LatestNews
import com.odm.fight_2019ncov.model.net.ApiResult
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

    init {
//        getSituationAll()
    }


    fun getSituationAll() {
        launch{
            val result = repository.suspendGetAllData()
            if(result  is ApiResult.Success) {
                _areaSituationList.value = result.data.data.getAreaStat
//                LogUtils.e(_areaSituationList.value.toString())
            }
            if(result is ApiResult.Error) {
                LogUtils.e("获取  all 数据 失败 "  + result.exception.message)
                LogUtils.e(result.toString())
            }
        }
    }


    fun getAllInfoRaw() {
        launch {
            val result = repository.suspendAllRawData()
            val reader: Reader = result.body()!!.charStream()
            if(result.raw().isSuccessful) {
                val objType: Type =  object : TypeToken<AllSituation>() {}.type
                val allInfoObject : AllSituation = Gson().fromJson(reader, objType)
                _areaSituationList.value = allInfoObject.getAreaStat
            } else {
                LogUtils.e("请求失败")
            }
        }
    }

}
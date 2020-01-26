package com.odm.fight_2019ncov.ui.LatestNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.odm.fight_2019ncov.base.BaseRepository
import com.odm.fight_2019ncov.base.BaseViewModel
import com.odm.fight_2019ncov.model.entity.LatestNews
import com.odm.fight_2019ncov.model.net.ApiResult

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
        const val tag = "SecondViewModel"
    }

    init {
        //初始化ViewModel时自动拉取Banner数据
        getBannerData()
    }


    fun getBannerData() {
//        viewModelScope.launch {
//            val result = repository.suspendGetBannerData()
//            if(result  is Result.Success) {
//                _bannerList.value = result.data
//            }
//        }
        launch{
            val result = repository.suspendGetLatestNewsData()
            if(result  is ApiResult.Success) {
                _newsList.value = result.data

            }
        }
    }

}
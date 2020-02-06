package com.odm.fight_2019ncov.ui.situation

import com.odm.fight_2019ncov.base.BaseRepository
import com.odm.fight_2019ncov.model.net.ApiService
import com.odm.fight_2019ncov.model.net.NetRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @description: 情况模块 Repository层
 * @author: ODM
 * @date: 2020/1/27
 */
class SituationRepository : BaseRepository() {

/*    suspend fun suspendGetAllData() = withContext(Dispatchers.IO) {
        safeApiRequest(request = {
            executeResponse(
                response = NetRetrofitClient.service.getAllInformation(),
                errorBlock = { println("网络出错了")} ) } ,
        errorMessage = "")
    }*/

    suspend fun suspendAllRawData(): Response<ResponseBody> = withContext(Dispatchers.IO) {
        NetRetrofitClient.service.getAllInfoRaw(ApiService.BASE_URL + ApiService.GET_ALL).execute()
    }

/*    suspend fun suspendGetRumorListData() = withContext(Dispatchers.IO){
        safeApiRequest(request = {
            executeResponse(
                response = NetRetrofitClient.service.getRumorList(),
                errorBlock = {println("网络出错了") })
            }
            ,errorMessage = "" )
    }*/
}
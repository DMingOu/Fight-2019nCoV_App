package com.odm.fight_2019ncov.ui.situation

import com.odm.fight_2019ncov.base.BaseRepository
import com.odm.fight_2019ncov.model.net.ApiService
import com.odm.fight_2019ncov.model.net.NetRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @description: 情况模块 Repository层
 * @author: ODM
 * @date: 2020/1/27
 */
class SituationRepository : BaseRepository() {

    suspend fun suspendGetAllData() = withContext(Dispatchers.IO) {
        safeApiRequest(request = { executeResponse(response = NetRetrofitClient.service.getAllInformation(), errorBlock = { println("网络出错了")} ) } ,
        errorMessage = "")

    }

    suspend fun suspendAllRawData() = withContext(Dispatchers.IO) {
        NetRetrofitClient.service.getLatestNewsRaw(ApiService.BASE_URL + "/data/all").execute()
    }
}
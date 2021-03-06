package com.odm.fight_2019ncov.ui.LatestNews

import com.blankj.utilcode.util.LogUtils
import com.odm.fight_2019ncov.base.BaseRepository
import com.odm.fight_2019ncov.model.net.ApiService
import com.odm.fight_2019ncov.model.net.NetRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @description: 实时最新消息 Repository 类
 * @author: ODM
 * @date: 2020/1/27
 */
class LatestNewsRepository : BaseRepository() {

/*    suspend fun suspendGetLatestNewsData() = withContext(Dispatchers.IO) {

        safeApiRequest(request = { executeResponse(response = NetRetrofitClient.service.getLatestNews() , errorBlock = { println("网络出错了")} ) } ,
            errorMessage = "")
    }*/

    suspend fun suspendGetLatestNewsRawData(): Response<ResponseBody> = withContext(Dispatchers.IO) {
        NetRetrofitClient.service.getLatestNewsRaw(ApiService.BASE_URL + ApiService.GET_TIMELINE).execute()
    }
}
package com.odm.fight_2019ncov.model.net

/**
 * @description: ApiService 接口类
 * @author: ODM
 * @date: 2020/1/26
 */

import com.odm.fight_2019ncov.model.entity.AreaSituation
import com.odm.fight_2019ncov.model.entity.LatestNews
import com.odm.fight_2019ncov.model.entity.OverallSituation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    companion object {
        const val BASE_URL = "http://49.232.173.220:3001"
    }

//    @GET("/article/list/{page}/json")
//    suspend fun getHomeArticles(@Path("page") page: Int): ApiResponse<ArticleList>

    /**
     * 获取24小时内最新消息（实时）
     */
    @GET("/data/getTimelineService")
    suspend fun getLatestNews() : ApiResponse<List<LatestNews>>

    /**
     * 获取对应地区的疫情情况
     */
    @GET("/data/getAreaStat/{provice}")
    suspend fun getAreaSituation( @Path("provice") provice : String) : ApiResponse<AreaSituation>

    /**
     * 获取整体的疫情情况
     */
    @GET("/data/getStatisticsService")
    suspend fun getOverallSituation() : ApiResponse<OverallSituation>


    @Streaming
    @GET
    //接口方法 suspend 会导致下载长时间阻塞，无法接收回调
    fun downLoad(@Url url: String) : Call<ResponseBody>


}
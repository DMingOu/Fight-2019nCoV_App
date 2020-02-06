package com.odm.fight_2019ncov.model.net

/**
 * @description: ApiService 接口类
 * @author: ODM
 * @date: 2020/1/26
 */

import com.google.gson.reflect.TypeToken
import com.odm.fight_2019ncov.model.entity.AllSituation
import com.odm.fight_2019ncov.model.entity.AreaSituation
import com.odm.fight_2019ncov.model.entity.Rumor
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.lang.reflect.Type

interface ApiService {

    companion object {
        const val BASE_URL = "http://49.232.173.220:3001"
        const val GET_ALL = "/data/all"
        const val GET_TIMELINE = "/data/getTimelineService"
        val rumorType : Type  = object : TypeToken<Rumor>() {}.type
        val allInfoType: Type = object : TypeToken<AllSituation>() {}.type
    }



    /**
     * 获取24小时内最新消息（实时）Call 不可阻塞
     */
     @Streaming
     @GET
     fun getLatestNewsRaw(@Url url: String) : Call<ResponseBody>

    /**
     * 获取全类型全部数据
     */
    @Streaming
    @GET
    fun getAllInfoRaw(@Url url: String) : Call<ResponseBody>


    /**
     * 获取对应地区的疫情情况
     */

    @GET("/data/getAreaStat/{provice}")
    suspend fun getAreaSituation( @Path("provice") provice : String) : ApiResponse<AreaSituation>


    /**
     * 获取全部信息
     */
    @GET("/data/all")
    suspend fun getAllInformation() : ApiResponse<AllSituation>


    @Streaming
    @GET
    //接口方法 suspend 会导致下载长时间阻塞，无法接收回调
    fun downLoad(@Url url: String) : Call<ResponseBody>


    @GET("/data/getIndexRumorList")
    suspend fun getRumorList( ) : ApiResponse<Collection<Rumor>>

}
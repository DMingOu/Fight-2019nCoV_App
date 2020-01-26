package com.odm.fight_2019ncov.model.entity
import com.google.gson.annotations.SerializedName


/**
 * @description: 实时消息 实体类
 * @author: ODM
 * @date: 2020/1/26
 */
data class LatestNews(
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("infoSource")
    val infoSource: String,
    @SerializedName("modifyTime")
    val modifyTime: Long,
    @SerializedName("provinceId")
    val provinceId: String,
    @SerializedName("provinceName")
    val provinceName: String,
    @SerializedName("pubDate")
    val pubDate: Long,
    @SerializedName("pubDateStr")
    val pubDateStr: String,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String
)


package com.odm.fight_2019ncov.model.entity
import com.google.gson.annotations.SerializedName


/**
 * @description: 辟谣实体类
 * @author: ODM
 * @date: 2020/2/6
 */
data class Rumor(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mainSummary")
    val mainSummary: String,
    @SerializedName("rumorType")
    val rumorType: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String
)
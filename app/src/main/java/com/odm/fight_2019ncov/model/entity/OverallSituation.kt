package com.odm.fight_2019ncov.model.entity
import com.google.gson.annotations.SerializedName


/**
 * @description: 整体情况 实体类
 * @author: ODM
 * @date: 2020/1/26
 */

data class OverallSituation(
    @SerializedName("countRemark")
    val countRemark: String,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("dailyPic")
    val dailyPic: String,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("generalRemark")
    val generalRemark: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("infectSource")
    val infectSource: String,
    @SerializedName("modifyTime")
    val modifyTime: Long,
    @SerializedName("passWay")
    val passWay: String,
    @SerializedName("remark1")
    val remark1: String,
    @SerializedName("remark2")
    val remark2: String,
    @SerializedName("remark3")
    val remark3: String,
    @SerializedName("remark4")
    val remark4: String,
    @SerializedName("remark5")
    val remark5: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("virus")
    val virus: String
)
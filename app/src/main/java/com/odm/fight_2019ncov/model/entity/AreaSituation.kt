package com.odm.fight_2019ncov.model.entity
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName


/**
 * @description: 地区情况 实体类
 * @author: ODM
 * @date: 2020/1/26
 */
data class AreaSituation(
    @SerializedName("cities")
    val cities: List<City>,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("confirmedCount")
    val confirmedCount: Int,
    @SerializedName("curedCount")
    val curedCount: Int,
    @SerializedName("deadCount")
    val deadCount: Int,
    @SerializedName("provinceName")
    val provinceName: String,
    @SerializedName("provinceShortName")
    val provinceShortName: String,
    @SerializedName("suspectedCount")
    val suspectedCount: Int
)

data class City(
    @SerializedName("cityName")
    val cityName: String,
    @SerializedName("confirmedCount")
    val confirmedCount: Int,
    @SerializedName("curedCount")
    val curedCount: Int,
    @SerializedName("deadCount")
    val deadCount: Int,
    @SerializedName("suspectedCount")
    val suspectedCount: Int
) : BaseExpandNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null
}
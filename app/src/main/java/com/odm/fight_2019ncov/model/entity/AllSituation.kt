package com.odm.fight_2019ncov.model.entity
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.annotations.SerializedName


/**
 * @description: 所有信息 实体类
 * @author: ODM
 * @date: 2020/1/27
 */
data class AllSituation(

    @SerializedName("getAreaStat")
    val getAreaStat: List<GetAreaStat>,
    @SerializedName("getEntries")
    val getEntries: List<GetEntry>,
    @SerializedName("getIndexRecommendList")
    val getIndexRecommendList: List<GetIndexRecommend>,
    @SerializedName("getIndexRumorList")
    val getIndexRumorList: List<GetIndexRumor>,
    @SerializedName("getListByCountryTypeService1")
    val getListByCountryTypeService1: List<GetByCountryTypeService1>,
    @SerializedName("getListByCountryTypeService2")
    val getListByCountryTypeService2: List<GetByCountryTypeService2>,
    @SerializedName("getPV")
    val getPV: Int,
    @SerializedName("getStatisticsService")
    val getStatisticsService: GetStatisticsService,
    @SerializedName("getTimelineService")
    val getTimelineService: List<GetTimelineService>,
    @SerializedName("getWikiList")
    val getWikiList: GetWikiList,
    @SerializedName("showPuppeteerUA")
    val showPuppeteerUA: String
)

data class GetAreaStat  (
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
) : BaseExpandNode() {

    override val childNode: MutableList<BaseNode>?
        get() = cities.toMutableList() as MutableList<BaseNode>
}



data class GetEntry(
    @SerializedName("configName")
    val configName: String,
    @SerializedName("configNo")
    val configNo: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("linkUrl")
    val linkUrl: String
)

data class GetIndexRecommend(
    @SerializedName("contentType")
    val contentType: Int,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("linkUrl")
    val linkUrl: String,
    @SerializedName("modifyTime")
    val modifyTime: Long,
    @SerializedName("operator")
    val `operator`: String,
    @SerializedName("recordStatus")
    val recordStatus: Int,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("title")
    val title: String
)

data class GetIndexRumor(
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

data class GetByCountryTypeService1(
    @SerializedName("cityName")
    val cityName: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("confirmedCount")
    val confirmedCount: Int,
    @SerializedName("countryType")
    val countryType: Int,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("curedCount")
    val curedCount: Int,
    @SerializedName("deadCount")
    val deadCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modifyTime")
    val modifyTime: Long,
    @SerializedName("operator")
    val `operator`: String,
    @SerializedName("provinceId")
    val provinceId: String,
    @SerializedName("provinceName")
    val provinceName: String,
    @SerializedName("provinceShortName")
    val provinceShortName: String,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("suspectedCount")
    val suspectedCount: Int,
    @SerializedName("tags")
    val tags: String
)

data class GetByCountryTypeService2(
    @SerializedName("cityName")
    val cityName: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("confirmedCount")
    val confirmedCount: Int,
    @SerializedName("countryType")
    val countryType: Int,
    @SerializedName("createTime")
    val createTime: Long,
    @SerializedName("curedCount")
    val curedCount: Int,
    @SerializedName("deadCount")
    val deadCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modifyTime")
    val modifyTime: Long,
    @SerializedName("operator")
    val `operator`: String,
    @SerializedName("provinceId")
    val provinceId: String,
    @SerializedName("provinceName")
    val provinceName: String,
    @SerializedName("provinceShortName")
    val provinceShortName: String,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("suspectedCount")
    val suspectedCount: Int,
    @SerializedName("tags")
    val tags: String
)

data class GetStatisticsService(
    @SerializedName("abroadRemark")
    val abroadRemark: String,
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
    val virus: String,
    //数字类
    @SerializedName("curedCount")
    val curedCount: Int ,
    @SerializedName("deadCount")
    val deadCount: Int,
    @SerializedName("confirmedCount")
    val confirmedCount: Int,
    @SerializedName("suspectedCount")
    val suspectedCount: Int
)

data class GetTimelineService(
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

data class GetWikiList(
    @SerializedName("pageNum")
    val pageNum: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("total")
    val total: Int
)

data class Result(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("linkUrl")
    val linkUrl: String,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("title")
    val title: String
)
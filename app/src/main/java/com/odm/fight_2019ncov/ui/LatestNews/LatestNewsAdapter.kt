package com.odm.fight_2019ncov.ui.LatestNews

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.model.entity.LatestNews
import com.orhanobut.logger.Logger

/**
 * @description: 实时新闻 列表适配器
 * @author: ODM
 * @date: 2020/1/27
 */
class LatestNewsAdapter (data : MutableList<LatestNews> ?)
    : BaseQuickAdapter <LatestNews , BaseViewHolder>(R.layout.item_news , data) {

    override fun convert(helper: BaseViewHolder, item: LatestNews?) {
        if (item == null) {
            Logger.d("item  null")
            return
        }
        helper.setText(R.id.tv_title_item_news , item.title)
        helper.setText(R.id.tv_infoSource_item_news,item.infoSource)
        helper.setText(R.id.tv_provinceName_item_news ,item.provinceName)
        helper.setText(R.id.tv_summary_item_news ,item.summary)
        helper.setText(R.id.tv_pubDateStr_item_news ,item.pubDateStr)
    }
}
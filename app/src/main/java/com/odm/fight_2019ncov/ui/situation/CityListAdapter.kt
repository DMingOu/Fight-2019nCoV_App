package com.odm.fight_2019ncov.ui.situation

import androidx.annotation.Nullable
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.odm.fight_2019ncov.R
import com.odm.fight_2019ncov.model.entity.City
import org.jetbrains.annotations.NotNull


/**
 * @description: 城市情况列表适配器
 * @author: ODM
 * @date: 2020/1/27
 */
class CityListAdapter : BaseNodeProvider() {
    override val itemViewType: Int
        get() = 2

    override val layoutId: Int
        get() = R.layout.item_situation_city

    override fun convert(helper: BaseViewHolder, data: BaseNode?) {
        val entity: City ? = data as City?
        //赋值操作
        helper.setText(R.id.tv_cityName_item_situation_city , entity?.cityName)
        helper.setText(R.id.tv_citySituation_item_situation_city ,"确诊${entity?.confirmedCount}例 " +
                "疑似${entity?.suspectedCount}例 治愈${entity?.curedCount}例 死亡${entity?.deadCount}例")
    }
}
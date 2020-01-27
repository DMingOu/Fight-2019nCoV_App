package com.odm.fight_2019ncov.ui.situation

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.odm.fight_2019ncov.model.entity.City
import com.odm.fight_2019ncov.model.entity.GetAreaStat


/**
 * @description: 地区情况列表 适配器
 * @author: ODM
 * @date: 2020/1/27
 */
class AreaSituationAdapter : BaseNodeAdapter() {

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node: BaseNode? = data[position]
        if (node is GetAreaStat) {
            return 1
        } else if (node is City) {
            return 2
        }
        return -1
    }


    companion object {
        const val EXPAND_COLLAPSE_PAYLOAD = 110
    }

    init {
        addNodeProvider(ProvinceListAdapter())
        addNodeProvider(CityListAdapter())
    }
}
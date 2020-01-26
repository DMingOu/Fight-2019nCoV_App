package com.odm.fight_2019ncov.model.net

/**
 * @description: Api 返回结果类
 * @author: ODM
 * @date: 2020/1/27
 */

data class ApiResponse<out T>(
    val errorCode : Int,
    val errorMsg : String,
    val data : T
 )
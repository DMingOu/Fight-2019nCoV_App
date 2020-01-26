package com.odm.fight_2019ncov.model.net

/**
 * @description: Api 调用返回结果判断 密封类
 * @author: ODM
 * @date: 2020/1/27
 */

sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
package com.odm.fight_2019ncov.base

import com.blankj.utilcode.util.LogUtils
import com.odm.fight_2019ncov.model.net.ApiResponse
import com.odm.fight_2019ncov.model.net.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * @description: Repository 基类
 * @author: ODM
 * @date: 2020/1/27
 */

open class BaseRepository {


    /**
     * 将网络异常捕捉包装成IOException的网络调用方法
     * request:具体的请求方法 executeResponse
     * return：Result类型
     */
    suspend fun <T : Any> safeApiRequest(request: suspend () -> ApiResult<T>, errorMessage: String): ApiResult<T> {
        return try {
            request()
        } catch (exception: Exception) {
            // 当调用API抛出一个异常,,将它转换为一个IOException
            LogUtils.e(exception.message.toString())
            ApiResult.Error(
                IOException(
                    errorMessage,
                    exception
                )
            )
        }
    }

    /**
     * errorCode == -1 要针对特定的业务
     * 根据状态码，协程执行success代码块和error代码块
     * 必须传入参数为ApiResponse类型
     * return: Result类型
     */
    suspend fun <T : Any> executeResponse(response: ApiResponse<T>,
                                          successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null)
            : ApiResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                ApiResult.Error(
                    IOException(
                        response.errorMsg
                    )
                )
            } else {
                successBlock?.let { it() }
                ApiResult.Success(response.data)
            }
        }
    }


}
package com.odm.fight_2019ncov.model.net

import com.odm.fight_2019ncov.App
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File

/**
 * @description: 网络请求客户端类
 * @author: ODM
 * @date: 2020/1/27
 */

object NetRetrofitClient : BaseRetrofitClient() {

    //实际调用实例
    val service by lazy { getService(
        ApiService::class.java,
        ApiService.BASE_URL
    ) }
    //缓存
    val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
    val cacheSize = 10 * 1024 * 1024L // 10 MiB
    val cache = Cache(
        httpCacheDirectory,
        cacheSize
    )

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        builder.cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetWorkUtils.isNetworkAvailable(
                        App.CONTEXT
                    )
                ) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!NetWorkUtils.isNetworkAvailable(
                        App.CONTEXT
                    )
                ) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // 缓存4周28天时间
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
    }
}
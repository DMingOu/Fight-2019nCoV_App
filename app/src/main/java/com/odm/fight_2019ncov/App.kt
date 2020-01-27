package com.odm.fight_2019ncov

import android.app.Application
import android.content.Context
import com.odm.fight_2019ncov.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.io.File
import kotlin.properties.Delegates

/**
 * @description: Application 实现类
 * @author: ODM
 * @date: 2020/1/26
 */
class App : Application() {


    companion object{
        var CONTEXT : Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext

        //Koin依赖注入
        startKoin {
            androidLogger()
            androidContext(CONTEXT)
            //androidFileProperties()
            modules(AppModule)
        }

        //为App创建好Download目录
        File(Constants.DOWNLOAD_PATH).mkdirs()
    }


}
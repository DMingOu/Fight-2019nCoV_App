package com.odm.fight_2019ncov.di

import com.odm.fight_2019ncov.ui.MainViewModel
import com.odm.fight_2019ncov.ui.LatestNews.LatestNewsRepository
import com.odm.fight_2019ncov.ui.LatestNews.LatestNewsViewModel
import com.odm.fight_2019ncov.ui.situation.SituationRepository
import com.odm.fight_2019ncov.ui.situation.SituationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @description: Koin 全局注入Module
 * @author: ODM
 * @date: 2020/1/27
 */

//get()获取Repository
val viewModelModule = module {
    viewModel { LatestNewsViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { SituationViewModel(get())  }
}

val repositoryModule = module {
    single { LatestNewsRepository() }
    single { SituationRepository() }
}

val AppModule = listOf(viewModelModule, repositoryModule)
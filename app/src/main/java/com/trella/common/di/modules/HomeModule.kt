/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 3:12 PM
 *
 */

package com.trella.common.di.modules


import android.content.Context
import com.trella.data.local.ShipmentsDao
import com.trella.data.local.ShipmentsDatabase
import com.trella.common.schedulers.SchedulerProvider
import com.trella.data.HomeApi
import com.trella.data.ShipmentsMemorySource
import com.trella.ui.home.GetShipmentsUseCase
import com.trella.ui.home.HomeRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class HomeModule {

    @Singleton
    @Provides
    internal fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(
        HomeApi::class.java
    )

    @Singleton
    @Provides
    internal fun provideShipmentsDao(context: Context): ShipmentsDao =
        ShipmentsDatabase.getInstance(context).shipmentsDao()

    @Singleton
    @Provides
    internal fun provideShipmentsMemory(): ShipmentsMemorySource = ShipmentsMemorySource()

    @Singleton
    @Provides
    internal fun provideHomeRepository(
        homeApi: HomeApi,
        shipmentsDao: ShipmentsDao,
        shipmentsMemorySource: ShipmentsMemorySource
    ): HomeRepository = HomeRepository(homeApi, shipmentsDao,shipmentsMemorySource)

    @Provides
    internal fun provideShipmentsUseCase(homeRepository: HomeRepository) =
        GetShipmentsUseCase(homeRepository, SchedulerProvider())

}

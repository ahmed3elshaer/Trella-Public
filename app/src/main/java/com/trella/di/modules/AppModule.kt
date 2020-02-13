/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 3:12 PM
 *
 */

package com.trella.di.modules

import android.content.Context
import com.trella.common.Application
import com.trella.common.schedulers.BaseSchedulerProvider
import com.trella.common.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideSchedulers(): BaseSchedulerProvider = SchedulerProvider()
}

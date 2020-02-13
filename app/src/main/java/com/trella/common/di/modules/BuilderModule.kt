/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 3:12 PM
 *
 */

package com.trella.common.di.modules

import com.trella.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun bindMainActivity(): MainActivity


}

/*
 * *
 *  * Created by Ahmed Elshaer on 10/28/19 5:37 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 10/28/19 5:31 AM
 *
 */

package com.trella.common.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderTest : BaseSchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}

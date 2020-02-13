/*
 * *
 *  * Created by Ahmed Elshaer on 10/28/19 5:18 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 10/28/19 5:16 AM
 *
 */

package com.trella.common.schedulers

import io.reactivex.Scheduler

interface BaseSchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}

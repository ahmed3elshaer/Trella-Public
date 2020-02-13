/*
 * *
 *  * Created by Ahmed Elshaer on 10/26/19 4:17 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 10/26/19 4:17 AM
 *
 */

package com.trella.common.location

import android.app.Activity
import android.content.Intent
import android.location.Location
import com.trella.common.location.LocationApi
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class RxLocationExt {
    private var locationBehaviorSubject = BehaviorSubject.create<Location>()
    private var locationApi: LocationApi? = null
    fun locations(activity: Activity, isRealtime: Boolean): Observable<Location> {
        locationApi = LocationApi(
            activity,
            isRealtime = isRealtime,
            callbacks = object : LocationApi.Callbacks {
                override fun onSuccess(location: Location) {
                    locationBehaviorSubject.onNext(location)
                }

                override fun onFailed(locationFailedEnum: LocationApi.LocationFailedEnum) {
                    locationBehaviorSubject.onError(Throwable(locationFailedEnum.name))
                }
            })
        return locationBehaviorSubject
            .retryWhen {
                it.flatMap { error ->
                    if (error.message !in LocationApi.LocationFailedEnum.values().map { it.name })
                        locationBehaviorSubject.onError(error)
                    locationBehaviorSubject = BehaviorSubject.create<Location>()
                    locationBehaviorSubject
                }
            }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        locationApi?.onActivityResult(requestCode, resultCode, data)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        locationApi?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun stopLocationUpdates() = locationApi?.stopLocationUpdates()
}

/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 3:12 PM
 *
 */

package com.trella.data.remote

import com.trella.data.models.ShipmentRemote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("/marketplace")
    fun getShipments(@Query("lat") latitude: Double? = null, @Query("lng") longitude: Double? = null): Observable<List<ShipmentRemote>>
}

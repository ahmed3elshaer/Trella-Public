/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 3:12 PM
 *
 */

package com.trella.data.remote

import com.trella.data.models.Shipments
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApi {
    @GET("/marketplace")
    fun getShipments(): Observable<List<Shipments>>
}

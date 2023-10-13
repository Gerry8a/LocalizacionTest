package com.gochoa.localizaciontest.service

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(): Flow<Location>
    fun getLatitude(): Double
    fun getLongitude(): Double

    class LocationException(message: String): Exception()
}
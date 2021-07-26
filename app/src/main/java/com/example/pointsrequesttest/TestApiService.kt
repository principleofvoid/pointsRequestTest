package com.example.pointsrequesttest

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApiService {
    @GET("/api/test/points")
    fun getPoints(@Query("count") count: Int): Single<PointsResponse>
}
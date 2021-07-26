package com.example.pointsrequesttest

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApiService {
    @GET("/api/test/points")
    fun getPoints(@Query("count") count: Int): Single<PointsResponse>
}

interface DebugService {
    @GET("/get")
    fun get(@Query("foo1") foo1: String, @Query("foo2") foo2: String): Single<TestResponse>
}
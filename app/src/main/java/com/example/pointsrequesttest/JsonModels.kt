package com.example.pointsrequesttest

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("x")
    val x: Float,
    @SerializedName("y")
    val y: Float
)

data class PointsResponse(
    @SerializedName("points")
    val points: List<Point>
)

data class Args(
    @SerializedName("foo1")
    val foo1: String,
    @SerializedName("foo2")
    val foo2: String
)

data class Headers(
    @SerializedName("x-forwarded-proto")
    val xForwardedProto: String,
    @SerializedName("host")
    val host: String,
    @SerializedName("accept")
    val accept: String,
    @SerializedName("accept-encoding")
    val acceptEncoding: String,
    @SerializedName("cache-control")
    val cacheControl: String,
    @SerializedName("postman-token")
    val postmanToken: String,
    @SerializedName("user-agent")
    val userAgent: String,
    @SerializedName("x-forwarded-port")
    val xForwardedPort: String
)

data class TestResponse(
    @SerializedName("args")
    val args: Args,
    @SerializedName("headers")
    val headers: Headers,
    @SerializedName("url")
    val url: String,
)
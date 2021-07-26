package com.example.pointsrequesttest

import android.app.Application

class App : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}
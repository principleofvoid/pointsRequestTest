package com.example.pointsrequesttest

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DaggerModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hr-challenge.interactivestandard.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): TestApiService {
        return retrofit.create(TestApiService::class.java)
    }
}

@Singleton
@Component(modules = [DaggerModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: GraphFragment)
}
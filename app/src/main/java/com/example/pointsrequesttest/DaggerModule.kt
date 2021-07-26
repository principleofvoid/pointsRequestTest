package com.example.pointsrequesttest

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DaggerModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hr-challenge.interactivestandard.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://postman-echo.com")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): TestApiService {
        return retrofit.create(TestApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideDebugApi(retrofit: Retrofit): DebugService {
//        return retrofit.create(DebugService::class.java)
//    }
}

@Singleton
@Component(modules = [DaggerModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: StartFragment)
    fun inject(fragment: GraphFragment)
}
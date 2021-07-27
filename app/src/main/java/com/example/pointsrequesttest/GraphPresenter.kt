package com.example.pointsrequesttest

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GraphPresenter @Inject constructor(
    private val apiService: TestApiService
) : BasePresenter<GraphFragment>() {

    fun requestPoints(count: Int) {
        view?.showProgress()
        apiService.getPoints(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //onSuccess
                { pointsResponse ->
                    view?.showPoints(pointsResponse.points.sortedBy { point -> point.x })
                },
                //onError
                { error ->
                    val errorMessage = if (error is HttpException) {
                        error.response()?.errorBody()?.string() ?: "Unknown http error"
                    } else {
                        error.localizedMessage ?: "Unknown error"
                    }
                    view?.showError(errorMessage)
                })
    }
}
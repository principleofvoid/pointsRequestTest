package com.example.pointsrequesttest

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GraphPresenter @Inject constructor(
    private val apiService: TestApiService
) {
    private var view: GraphFragment? = null

    fun attachView(view: GraphFragment) {
        this.view = view
    }

    fun requestPoints(count: Int) {
        view?.showProgress()
        apiService.getPoints(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //onSuccess
                {
                    Log.d("[DEBUG]", "onSuccess")
                    view?.showPoints(it.points)
                },
                //onError
                {
                    Log.d("[DEBUG]", "onError")
                    view?.showError(it.localizedMessage ?: "Unknown error")
                })
    }

    fun detachView() {
        view = null
    }
}
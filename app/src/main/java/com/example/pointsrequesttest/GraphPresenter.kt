package com.example.pointsrequesttest

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
        apiService.getPoints(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //onSuccess
                {
                    view?.showPoints(it.points)
                },
                //onError
                {
                })
    }

    fun detachView() {
        view = null
    }
}
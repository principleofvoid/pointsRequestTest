package com.example.pointsrequesttest

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartPresenter @Inject constructor() : BasePresenter<StartFragment>() {

    fun initGraph(pointsCountInput: String?) {
        if (pointsCountInput.isNullOrEmpty()) {
            view?.showCountError(R.string.error_empty_points_count)
        } else {
            val pointsCount = pointsCountInput.toInt()
            if (pointsCount <= 0) {
                view?.showCountError(R.string.error_points_count_zero)
            } else {
                view?.goToGraph(pointsCount)
            }
        }
    }
}
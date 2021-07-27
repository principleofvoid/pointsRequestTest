package com.example.pointsrequesttest

open class BasePresenter<T> {
    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}
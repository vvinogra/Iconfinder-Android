package com.github.vvinogra.iconfinderandroid.data.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class IconFinderSchedulers @Inject constructor() {
    fun io(): Scheduler = Schedulers.io()

    fun main(): Scheduler = AndroidSchedulers.mainThread()

    fun computation(): Scheduler = Schedulers.computation()
}
package com.maku.interviewandroidtest.common.utils

import android.util.Log
import timber.log.Timber

class ReleaseLogTree : Timber.Tree() {

    override fun log(
        priority: Int, tag: String?, message: String,
        throwable: Throwable?
    ) {
        if (priority == Log.DEBUG || priority == Log.VERBOSE || priority == Log.INFO) {
            return
        }

        if (priority == Log.ERROR) {
            if (throwable == null) {
                // you can add Firebase analytics here.
                Timber.e(message)
            } else {
                Timber.e(throwable, message)
            }
        }
    }
}
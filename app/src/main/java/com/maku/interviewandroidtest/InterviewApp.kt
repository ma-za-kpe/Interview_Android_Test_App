package com.maku.interviewandroidtest

import android.app.Application
import com.maku.interviewandroidtest.BuildConfig.DEBUG
import com.maku.interviewandroidtest.common.utils.ReleaseLogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class InterviewApp: Application() {
    override fun onCreate() {
        super.onCreate()

        //plant timber
        if (DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                //Add the line number to the tag
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ": " + element.lineNumber
                }
            })
        } else {
            //Release mode
            Timber.plant(ReleaseLogTree())
        }
    }
}
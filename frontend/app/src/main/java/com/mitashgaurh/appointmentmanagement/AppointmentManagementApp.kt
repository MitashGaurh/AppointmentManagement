package com.mitashgaurh.appointmentmanagement

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.mitashgaurh.appointmentmanagement.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import java.io.PrintWriter
import java.io.StringWriter
import javax.inject.Inject


class AppointmentManagementApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, exception ->
            val sw = StringWriter()
            exception.printStackTrace(PrintWriter(sw))
            val exceptionAsString = sw.toString()
            Timber.e("  ---->  %s", exceptionAsString)
            Timber.e("uncaughtException: Exception ENDS")
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}
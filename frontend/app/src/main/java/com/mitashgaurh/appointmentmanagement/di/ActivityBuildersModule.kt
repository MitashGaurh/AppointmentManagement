package com.mitashgaurh.appointmentmanagement.di


import com.mitashgaurh.appointmentmanagement.view.home.HomeActivity
import com.mitashgaurh.appointmentmanagement.view.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [LoginFragmentBuildersModule::class])
    abstract fun contributeLoginActivity(): LoginActivity
}
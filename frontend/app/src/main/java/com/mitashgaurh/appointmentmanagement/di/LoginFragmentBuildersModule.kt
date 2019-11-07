package com.mitashgaurh.appointmentmanagement.di

import com.mitashgaurh.appointmentmanagement.view.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class LoginFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

}
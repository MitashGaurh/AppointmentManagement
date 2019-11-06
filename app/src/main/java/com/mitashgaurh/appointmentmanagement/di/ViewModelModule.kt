package com.mitashgaurh.appointmentmanagement.di

import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.view.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindUserViewModel(homeViewModel: HomeViewModel): ViewModel
}
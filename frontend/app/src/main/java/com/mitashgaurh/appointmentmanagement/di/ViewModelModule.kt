package com.mitashgaurh.appointmentmanagement.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentViewModel
import com.mitashgaurh.appointmentmanagement.view.home.HomeViewModel
import com.mitashgaurh.appointmentmanagement.view.login.LoginViewModel
import com.mitashgaurh.appointmentmanagement.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookAppointmentViewModel::class)
    abstract fun bindBookAppointmentViewModel(bookAppointmentViewModel: BookAppointmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
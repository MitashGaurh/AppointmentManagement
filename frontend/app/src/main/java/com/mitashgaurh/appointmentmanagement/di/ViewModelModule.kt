package com.mitashgaurh.appointmentmanagement.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mitashgaurh.appointmentmanagement.view.appointmentHistory.AppointmentHistoryViewModel
import com.mitashgaurh.appointmentmanagement.view.appointmentHistory.PaymentHistoryViewModel
import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentViewModel
import com.mitashgaurh.appointmentmanagement.view.doctor.DoctorViewModel
import com.mitashgaurh.appointmentmanagement.view.home.HomeViewModel
import com.mitashgaurh.appointmentmanagement.view.login.LoginViewModel
import com.mitashgaurh.appointmentmanagement.view.profile.ProfileViewModel
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
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentHistoryViewModel::class)
    abstract fun bindAppointmentHistoryViewModel(appointmentHistoryViewModel: AppointmentHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentHistoryViewModel::class)
    abstract fun bindPaymentHistoryViewModel(paymentHistoryViewModel: PaymentHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoctorViewModel::class)
    abstract fun bindDoctorViewModel(doctorViewModel: DoctorViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
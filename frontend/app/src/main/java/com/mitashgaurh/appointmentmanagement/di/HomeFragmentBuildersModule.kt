package com.mitashgaurh.appointmentmanagement.di

import com.mitashgaurh.appointmentmanagement.view.appointmentHistory.AppointmentHistoryFragment
import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentFragment
import com.mitashgaurh.appointmentmanagement.view.doctor.DoctorListFragment
import com.mitashgaurh.appointmentmanagement.view.home.HomeFragment
import com.mitashgaurh.appointmentmanagement.view.home.NavigationBottomSheet
import com.mitashgaurh.appointmentmanagement.view.payment.PaymentHistoryFragment
import com.mitashgaurh.appointmentmanagement.view.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNavigationBottomSheet(): NavigationBottomSheet

    @ContributesAndroidInjector
    abstract fun contributeBookAppointmentFragment(): BookAppointmentFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeAppointmentHistoryFragment(): AppointmentHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributePaymentHistoryFragment(): PaymentHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeDoctorListFragment(): DoctorListFragment

}
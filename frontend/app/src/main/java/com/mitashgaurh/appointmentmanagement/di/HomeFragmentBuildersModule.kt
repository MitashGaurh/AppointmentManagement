package com.mitashgaurh.appointmentmanagement.di

import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentFragment
import com.mitashgaurh.appointmentmanagement.view.home.HomeFragment
import com.mitashgaurh.appointmentmanagement.view.home.NavigationBottomSheet
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

}
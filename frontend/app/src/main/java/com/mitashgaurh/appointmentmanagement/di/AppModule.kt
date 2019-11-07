package com.mitashgaurh.appointmentmanagement.di

import android.app.Application
import androidx.room.Room
import com.mitashgaurh.appointmentmanagement.db.AppointmentManagementDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppointmentManagementDb {
        return Room
            .databaseBuilder(app, AppointmentManagementDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
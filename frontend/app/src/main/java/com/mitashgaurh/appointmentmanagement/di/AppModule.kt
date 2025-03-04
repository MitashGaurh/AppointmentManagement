package com.mitashgaurh.appointmentmanagement.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.db.AppointmentManagementDb
import com.mitashgaurh.appointmentmanagement.db.dao.*
import com.mitashgaurh.appointmentmanagement.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppointmentManagementService(): AppointmentManagementService {
        return Retrofit.Builder()
            .baseUrl("http://ec2-18-206-160-69.compute-1.amazonaws.com:4000")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(AppointmentManagementService::class.java)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppointmentManagementDb {
        return Room
            .databaseBuilder(app, AppointmentManagementDb::class.java, "appointment.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppointmentManagementDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideAppointmentHistoryDao(db: AppointmentManagementDb): AppointmentHistoryDao {
        return db.appointmentHistoryDao()
    }

    @Singleton
    @Provides
    fun provideAppointmentTypeDao(db: AppointmentManagementDb): AppointmentTypeDao {
        return db.appointmentTypeDao()
    }

    @Singleton
    @Provides
    fun provideAppointmentReasonDao(db: AppointmentManagementDb): AppointmentReasonDao {
        return db.appointmentReasonDao()
    }

    @Singleton
    @Provides
    fun provideTimeSlotDao(db: AppointmentManagementDb): TimeSlotDao {
        return db.timeSlotDao()
    }

    @Singleton
    @Provides
    fun providePaymentHistoryDao(db: AppointmentManagementDb): PaymentHistoryDao {
        return db.paymentHistoryDao()
    }

    @Singleton
    @Provides
    fun provideDoctorDao(db: AppointmentManagementDb): DoctorDao {
        return db.doctorDao()
    }
}
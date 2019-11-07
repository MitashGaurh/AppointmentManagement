package com.mitashgaurh.appointmentmanagement.repository

import com.mitashgaurh.appointmentmanagement.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val appExecutors: AppExecutors
)
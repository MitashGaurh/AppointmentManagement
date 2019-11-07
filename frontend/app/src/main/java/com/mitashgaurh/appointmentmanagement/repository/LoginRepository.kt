package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.ApiResponse
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.api.NetworkBoundResource
import com.mitashgaurh.appointmentmanagement.db.dao.UserDao
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.vo.LoginResponse
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mUserDao: UserDao
) {

    fun triggerLoginService(studentId: String, password: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, LoginResponse>(mAppExecutors) {
            override fun saveCallResult(item: LoginResponse) {
                if (item.status == "success") {
                    mUserDao.insert(item.userDetails)
                }
            }

            override fun shouldFetch(data: User?): Boolean = data == null

            override fun loadFromDb(): LiveData<User> {
                return mUserDao.loadUserByStudentId(studentId = studentId.toLong())
            }

            override fun createCall(): LiveData<ApiResponse<LoginResponse>> {
                return mService.performLogin(studentId, password)
            }
        }.asLiveData()
    }
}
package com.mitashgaurh.appointmentmanagement.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mitashgaurh.appointmentmanagement.AppExecutors
import com.mitashgaurh.appointmentmanagement.api.AppointmentManagementService
import com.mitashgaurh.appointmentmanagement.db.dao.UserDao
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.vo.Resource
import com.mitashgaurh.appointmentmanagement.vo.Status
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val mAppExecutors: AppExecutors,
    private val mService: AppointmentManagementService,
    private val mUserDao: UserDao
) {

    fun loadUserByStudentId(studentId: String): LiveData<User> {
        return mUserDao.loadUserByStudentId(studentId)
    }

    fun triggerUpdateProfileService(user: User): MutableLiveData<Resource<Any>> {
        val resultLiveData: MutableLiveData<Resource<Any>> = MutableLiveData()
        resultLiveData.postValue(Resource(Status.LOADING, null, null))

        mService.updateUserProfile(user)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(
                    call: @NotNull Call<Any?>?,
                    response: @NotNull Response<Any?>?
                ) {
                    if (response?.isSuccessful!!) {
                        mAppExecutors.diskIO().execute {
                            mUserDao.insert(user)
                            resultLiveData.postValue(
                                Resource(
                                    Status.SUCCESS,
                                    response.body(),
                                    "Inside onResponse"
                                )
                            )
                        }
                    } else resultLiveData.postValue(
                        Resource(
                            Status.ERROR,
                            response.body(),
                            "Login Failure."
                        )
                    )
                }

                override fun onFailure(
                    call: @NotNull Call<Any?>?,
                    t: @NotNull Throwable?
                ) {
                    resultLiveData.postValue(Resource(Status.ERROR, null, "Login Failure."))
                }
            })

        return resultLiveData
    }
}
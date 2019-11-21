package com.mitashgaurh.appointmentmanagement.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.repository.ProfileRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Event
import com.mitashgaurh.appointmentmanagement.vo.Resource
import java.util.*
import javax.inject.Inject

class ProfileViewModel
@Inject constructor(private val mProfileRepository: ProfileRepository) :
    ViewModel() {

    private var studentIdEvent = MutableLiveData<String>()

    private val pickDateEvent = MutableLiveData<Event<Date>>()
    val mPickDateEvent: LiveData<Event<Date>> = pickDateEvent

    fun triggerPickDateEvent(date: Date) {
        pickDateEvent.value = Event(date)
    }

    val mUserLiveData: LiveData<User> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mProfileRepository.loadUserByStudentId(it)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = studentId
    }

    private var updateProfileRequestEvent = MutableLiveData<User>()

    val mUpdateProfileLiveData: LiveData<Resource<Any>> =
        Transformations.switchMap(updateProfileRequestEvent) {
            if (null == it) {
                AbsentLiveData.create<Resource<Any>>()
            } else {
                mProfileRepository.triggerUpdateProfileService(it)
            }
        }

    fun setUpdateProfileRequest(user: User) {
        this.updateProfileRequestEvent.value = user
    }

}
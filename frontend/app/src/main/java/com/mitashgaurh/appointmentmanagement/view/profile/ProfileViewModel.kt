package com.mitashgaurh.appointmentmanagement.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.repository.ProfileRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Event
import javax.inject.Inject

class ProfileViewModel
@Inject constructor(private val mProfileRepository: ProfileRepository) :
    ViewModel() {

    private var studentIdEvent = MutableLiveData<Event<String>>()

    val mUserLiveData: LiveData<User> =
        Transformations.switchMap(studentIdEvent) {
            if (null == it) {
                AbsentLiveData.create()
            } else {
                mProfileRepository.loadUserByStudentId(it.getContentIfNotHandled()!!)
            }
        }

    fun setStudentId(studentId: String) {
        studentIdEvent.value = Event(studentId)
    }

}
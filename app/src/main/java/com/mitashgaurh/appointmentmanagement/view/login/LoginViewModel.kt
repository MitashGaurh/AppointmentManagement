package com.mitashgaurh.appointmentmanagement.view.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.repository.LoginRepository
import com.mitashgaurh.appointmentmanagement.vo.Event
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel
@Inject constructor(loginRepository: LoginRepository) : ViewModel() {

    var studentId: String = ""

    var password: String = ""

    private val loginEvent = MutableLiveData<Event<Boolean>>()

    val mLoginEvent: LiveData<Event<Boolean>> = loginEvent

    fun onLoginClicked(view: View) {
        if (view.id == R.id.btn_login) {
            Timber.d("Student Id = $studentId Password = $password")

            loginEvent.value = Event(true)
        }
    }
}
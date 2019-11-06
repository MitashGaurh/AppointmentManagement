package com.mitashgaurh.appointmentmanagement.view.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel
@Inject constructor(loginRepository: LoginRepository) : ViewModel() {

    var studentId: String = ""

    var password: String = ""

    fun onLoginClicked(view: View) {
        if (view.id == R.id.btn_login) {
            Timber.d("Student Id = $studentId Password = $password")
        }
    }
}
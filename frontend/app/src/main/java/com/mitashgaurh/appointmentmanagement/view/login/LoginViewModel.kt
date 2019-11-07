package com.mitashgaurh.appointmentmanagement.view.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.db.entity.User
import com.mitashgaurh.appointmentmanagement.repository.LoginRepository
import com.mitashgaurh.appointmentmanagement.util.AbsentLiveData
import com.mitashgaurh.appointmentmanagement.vo.Resource
import javax.inject.Inject

class LoginViewModel
@Inject constructor(private val mLoginRepository: LoginRepository) : ViewModel() {

    var studentId: String = ""

    var password: String = ""

    private val loginEvent = MutableLiveData<Boolean>()

    val mLoginLiveData: LiveData<Resource<User>> = Transformations.switchMap(loginEvent) {
        if (null == it || it == false) {
            AbsentLiveData.create()
        } else {
            mLoginRepository.triggerLoginService(studentId, password)
        }
    }


    fun onLoginClicked(view: View) {
        if (view.id == R.id.btn_login && studentId.isNotEmpty() && password.isNotEmpty()) {
            loginEvent.value = true
        }
    }
}
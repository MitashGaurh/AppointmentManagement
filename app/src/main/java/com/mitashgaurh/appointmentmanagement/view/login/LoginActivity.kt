package com.mitashgaurh.appointmentmanagement.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.util.ActivityUtils

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (null == savedInstanceState) {
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                LoginFragment(),
                R.id.login_container,
                false,
                LoginFragment::class.java.simpleName
            )
        }
    }
}

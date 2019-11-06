package com.mitashgaurh.appointmentmanagement.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.util.ActivityUtils
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

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

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}

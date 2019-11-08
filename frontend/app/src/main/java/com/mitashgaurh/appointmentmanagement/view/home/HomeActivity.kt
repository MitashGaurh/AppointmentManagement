package com.mitashgaurh.appointmentmanagement.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.ActivityHomeBinding
import com.mitashgaurh.appointmentmanagement.util.ActivityUtils
import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        if (null == savedInstanceState) {
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                HomeFragment(),
                R.id.home_container,
                false,
                HomeFragment::class.java.simpleName
            )

            initBottomAppBar()
        }
    }

    private fun initBottomAppBar() {
        setSupportActionBar(mBinding.bottomAppBar)

        mBinding.currentIsHome = true

        mBinding.fab.setOnClickListener {
            if (mBinding.currentIsHome!!) {
                toggleFabBehavior()
                ActivityUtils.addFragmentToActivity(
                    supportFragmentManager,
                    BookAppointmentFragment(),
                    R.id.home_container,
                    true,
                    BookAppointmentFragment::class.java.simpleName
                )
            } else {
                if (supportFragmentManager.backStackEntryCount == 1) toggleFabBehavior()
                onBackPressed()
            }
        }
    }

    private fun toggleFabBehavior() {
        mBinding.currentIsHome = mBinding.currentIsHome?.not()!!
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}

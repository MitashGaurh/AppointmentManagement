package com.mitashgaurh.appointmentmanagement.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mitashgaurh.appointmentmanagement.R
import com.mitashgaurh.appointmentmanagement.databinding.ActivityHomeBinding
import com.mitashgaurh.appointmentmanagement.util.ActivityUtils
import com.mitashgaurh.appointmentmanagement.view.bookappointment.BookAppointmentFragment
import com.mitashgaurh.appointmentmanagement.vo.FragmentState
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

        mBinding.bottomAppBar.setNavigationOnClickListener {
            NavigationBottomSheet().show(
                supportFragmentManager,
                NavigationBottomSheet::class.java.simpleName
            )
        }

        mBinding.fragmentState = FragmentState.HOME

        mBinding.fab.setOnClickListener {
            if (mBinding.fragmentState == FragmentState.HOME) {
                toggleFabBehavior(FragmentState.BOOK_APPOINTMENT)
                ActivityUtils.addFragmentToActivity(
                    supportFragmentManager,
                    BookAppointmentFragment(),
                    R.id.home_container,
                    true,
                    BookAppointmentFragment::class.java.simpleName
                )
            } else if (mBinding.fragmentState == FragmentState.BOOK_APPOINTMENT) {
                val bookAppointmentFragment =
                    supportFragmentManager.findFragmentById(R.id.home_container) as BookAppointmentFragment

                bookAppointmentFragment.submitAppointment()
            }
        }
    }

    private fun toggleFabBehavior(state: FragmentState) {
        mBinding.fragmentState = state
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) toggleFabBehavior(FragmentState.HOME)
        super.onBackPressed()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}

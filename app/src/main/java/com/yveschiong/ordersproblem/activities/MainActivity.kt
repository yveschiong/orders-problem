package com.yveschiong.ordersproblem.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.extensions.replaceFragment
import com.yveschiong.ordersproblem.fragments.OrderByProvinceFragment
import com.yveschiong.ordersproblem.fragments.OrderByYearFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var fragments = HashMap<Int, Fragment>()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // Attempt to use the cached fragment
        if (switchFragments(item.itemId)) {
            return@OnNavigationItemSelectedListener true
        }

        when (item.itemId) {
            R.id.navigation_province -> {
                fragments[item.itemId] = OrderByProvinceFragment.newInstance()
            }
            R.id.navigation_year -> {
                fragments[item.itemId] = OrderByYearFragment.newInstance()
            }
        }

        // Check again to see if the fragment has been created earlier
        if (switchFragments(item.itemId)) {
            return@OnNavigationItemSelectedListener true
        }

        false
    }

    fun switchFragments(id: Int): Boolean {
        if (!fragments.containsKey(id)) {
            return false
        }

        replaceFragment(R.id.fragment, fragments[id]!!)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_province
    }
}

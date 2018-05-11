package com.yveschiong.ordersproblem.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_province -> {
                message.setText(R.string.title_province)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_year -> {
                message.setText(R.string.title_year)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val ordersCollection = App.graph.getRequestHandler
        ordersCollection.getOrders(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    Log.d("Result", "There are ${result.orders.size} orders")
                }, { error ->
                    error.printStackTrace()
                })
    }
}

package com.yveschiong.ordersproblem.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.adapters.YearListAdapter
import com.yveschiong.ordersproblem.models.Order
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.order_by_year_fragment.view.*
import java.util.*

class OrderByYearFragment: Fragment() {

    companion object {
        fun newInstance(): OrderByYearFragment {
            return OrderByYearFragment()
        }
    }

    private var yearListAdapter: YearListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_by_year_fragment, container, false)

        view.recyclerview.layoutManager = LinearLayoutManager(context)

        if (yearListAdapter != null) {
            // Do not fetch the data again since we will get the same results (assuming)
            view.recyclerview.adapter = yearListAdapter
            return view
        }

        // Currently only fetching on page 1 and for the year of 2017
        App.graph.getRequestHandler.getOrders(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it.orders }
                .filter {
                    var calendar = Calendar.getInstance()
                    calendar.timeInMillis = it.created_at.time
                    calendar.get(Calendar.YEAR) == 2017 && it.shipping_address.province.isNotEmpty()
                }
                .toList()
                .subscribe({ result ->
                    yearListAdapter = YearListAdapter(result as ArrayList<Order>, context!!)
                    view.recyclerview.adapter = yearListAdapter
                }, { error -> error.printStackTrace() })

        return view
    }
}
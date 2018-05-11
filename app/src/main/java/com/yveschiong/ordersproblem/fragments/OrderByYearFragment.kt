package com.yveschiong.ordersproblem.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.order_by_year_fragment.view.*
import java.util.*

class OrderByYearFragment: Fragment() {

    private var textString: String = ""

    companion object {
        fun newInstance(): OrderByYearFragment {
            return OrderByYearFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_by_year_fragment, container, false)

        if (!textString.isEmpty()) {
            // Do not fetch the data again since we will get the same results (assuming)
            view.textView.text = textString
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
                    calendar.get(Calendar.YEAR) == 2017
                }
                .count()
                .subscribe({ result ->
                    textString = String.format(context!!.getString(R.string.year_order_count), result.toInt())
                    view.textView.text = textString
                }, { error -> error.printStackTrace() })

        return view
    }
}
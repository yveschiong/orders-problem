package com.yveschiong.ordersproblem.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.adapters.ProvinceListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.order_by_province_fragment.view.*

class OrderByProvinceFragment : Fragment() {

    companion object {
        fun newInstance(): OrderByProvinceFragment {
            return OrderByProvinceFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_by_province_fragment, container, false)

        view.recyclerview.layoutManager = LinearLayoutManager(context)

        val ordersCollection = App.graph.getRequestHandler
        ordersCollection.getOrders(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it.orders }
                .groupBy { it.shipping_address.province }
                .filter { group -> !group.key.isNullOrEmpty() }
                .flatMap { group -> group.count().map { ProvinceListAdapter.ProvinceOrders(group.key!!, it.toInt()) }.toObservable() }
                .toList()
                .subscribe({ result ->
                    view.recyclerview.adapter = ProvinceListAdapter(result as ArrayList<ProvinceListAdapter.ProvinceOrders>, context!!)
                }, { error -> error.printStackTrace() })

        return view
    }
}
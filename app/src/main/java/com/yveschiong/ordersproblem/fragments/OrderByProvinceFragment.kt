package com.yveschiong.ordersproblem.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.activities.ProvincesOrdersActivity
import com.yveschiong.ordersproblem.adapters.ProvinceListAdapter
import com.yveschiong.ordersproblem.extensions.launchActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.order_by_province_fragment.view.*

class OrderByProvinceFragment : Fragment() {

    companion object {
        fun newInstance(): OrderByProvinceFragment {
            return OrderByProvinceFragment()
        }
    }

    private var provinceListAdapter: ProvinceListAdapter? = null

    private var provinceListActionController = object: ProvinceListActionController {
        override fun onItemClicked(position: Int) {
            when(provinceListAdapter!!.getItemViewType(position)) {
                ProvinceListAdapter.HEADER_TYPE -> {
                    context!!.launchActivity(ProvincesOrdersActivity::class.java)
                }
                ProvinceListAdapter.ITEM_TYPE -> {
                    // Do nothing for now
                }
            }
        }
    }

    interface ProvinceListActionController {
        fun onItemClicked(position: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_by_province_fragment, container, false)

        view.recyclerview.layoutManager = LinearLayoutManager(context)

        if (provinceListAdapter != null) {
            // Do not fetch the data again since we will get the same results (assuming)
            view.recyclerview.adapter = provinceListAdapter
            return view
        }

        // Currently only fetching on page 1
        App.graph.getRequestHandler.getOrders(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it.orders }
                .groupBy { it.shipping_address.province }
                .filter { group -> !group.key.isNullOrEmpty() }
                .flatMap { group -> group.count().map { ProvinceListAdapter.ProvinceOrders(group.key!!, it.toInt()) }.toObservable() }
                .toList()
                .subscribe({ result ->
                    provinceListAdapter = ProvinceListAdapter(result as ArrayList<ProvinceListAdapter.ProvinceOrders>, context!!, provinceListActionController)
                    view.recyclerview.adapter = provinceListAdapter
                }, { error -> error.printStackTrace() })

        return view
    }
}
package com.yveschiong.ordersproblem.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yveschiong.ordersproblem.App
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.adapters.ProvinceSortedListAdapter
import com.yveschiong.ordersproblem.models.ProvincesOrderData
import com.yveschiong.ordersproblem.models.ShippingAddress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.order_by_province_fragment.*

class ProvincesOrdersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinces_orders)

        recyclerview.layoutManager = LinearLayoutManager(this)

        // Currently only fetching on page 1
        App.graph.getRequestHandler.getOrders(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it.orders }
                .filter { !it.shipping_address.province.isEmpty() }
                .toSortedList { o1, o2 -> o1.shipping_address.province.compareTo(o2.shipping_address.province) }
                .subscribe({ result ->
                    val list = ArrayList<ProvincesOrderData>()
                    var shippingAddress = ShippingAddress()
                    for (i in result.indices) {
                        if (result[i].shipping_address != shippingAddress) {
                            shippingAddress = result[i].shipping_address
                            list.add(shippingAddress)
                        }

                        list.add(result[i].customer)
                    }

                    recyclerview.adapter = ProvinceSortedListAdapter(list, this)
                }, { error -> error.printStackTrace() })
    }
}

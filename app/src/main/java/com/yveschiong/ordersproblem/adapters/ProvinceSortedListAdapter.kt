package com.yveschiong.ordersproblem.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.models.Customer
import com.yveschiong.ordersproblem.models.ProvincesOrderData
import com.yveschiong.ordersproblem.models.ShippingAddress
import com.yveschiong.ordersproblem.viewholders.ProvinceListHeaderViewHolder
import com.yveschiong.ordersproblem.viewholders.ProvinceListItemViewHolder

class ProvinceSortedListAdapter(var items: ArrayList<ProvincesOrderData>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val HEADER_TYPE = 0
        val ITEM_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            HEADER_TYPE -> {
                return ProvinceListHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.province_list_header, parent, false))
            }
            ITEM_TYPE -> {
                return ProvinceListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.province_list_item, parent, false))
            }
        }

        return ProvinceListItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.province_list_item, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position] is ShippingAddress) {
            return HEADER_TYPE
        }

        return ITEM_TYPE
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            HEADER_TYPE -> {
                (holder as ProvinceListHeaderViewHolder).textView.text = (items[position] as ShippingAddress).province
            }
            ITEM_TYPE -> {
                val customer = (items[position] as Customer)
                (holder as ProvinceListItemViewHolder).textView.text = String.format(context.getString(R.string.full_name), customer.first_name, customer.last_name)
            }
        }
    }

}
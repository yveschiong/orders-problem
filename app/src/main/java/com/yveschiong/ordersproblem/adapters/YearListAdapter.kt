package com.yveschiong.ordersproblem.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.models.Order
import com.yveschiong.ordersproblem.viewholders.ProvinceListHeaderViewHolder
import com.yveschiong.ordersproblem.viewholders.ProvinceListItemViewHolder

class YearListAdapter(var items: ArrayList<Order>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        if (position == 0) {
            return HEADER_TYPE
        }

        return ITEM_TYPE
    }

    override fun getItemCount(): Int {
        // Include the header item
        return items.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            HEADER_TYPE -> {
                (holder as ProvinceListHeaderViewHolder).textView.text = context.getText(R.string.year_2017)
            }
            ITEM_TYPE -> {
                setFormattedText((holder as ProvinceListItemViewHolder).textView, position)
            }
        }
    }

    fun setFormattedText(textView: TextView, position: Int) {
        textView.text = String.format(context.getString(R.string.full_info),
                items[position - 1].customer.first_name, items[position - 1].customer.last_name,
                items[position - 1].shipping_address.province)
    }

}
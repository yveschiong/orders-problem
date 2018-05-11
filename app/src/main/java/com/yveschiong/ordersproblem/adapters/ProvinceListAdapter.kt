package com.yveschiong.ordersproblem.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.yveschiong.ordersproblem.R
import com.yveschiong.ordersproblem.viewholders.ProvinceListHeaderViewHolder
import com.yveschiong.ordersproblem.viewholders.ProvinceListItemViewHolder

class ProvinceListAdapter(var items: ArrayList<ProvinceOrders>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val HEADER_TYPE = 0
        val ITEM_TYPE = 1
    }

    data class ProvinceOrders(val province: String, val orders: Int)

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
                // Don't need to set the text since it's been set in the XML and we don't change it
            }
            ITEM_TYPE -> {
                setFormattedText((holder as ProvinceListItemViewHolder).textView, position)
            }
        }
    }

    fun setFormattedText(textView: TextView, position: Int) {
        textView.text = String.format(context.getString(R.string.province_order_count), items[position - 1].orders, items[position - 1].province)
    }

}
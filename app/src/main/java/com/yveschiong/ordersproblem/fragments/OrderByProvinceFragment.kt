package com.yveschiong.ordersproblem.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.ordersproblem.R

class OrderByProvinceFragment: Fragment() {

    companion object {
        fun newInstance(): OrderByProvinceFragment {
            return OrderByProvinceFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.order_by_province_fragment, container, false)
    }
}
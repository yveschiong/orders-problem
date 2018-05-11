package com.yveschiong.ordersproblem.responses

import com.yveschiong.ordersproblem.models.Order

data class OrdersResponse(
    var orders: List<Order> = ArrayList()
)
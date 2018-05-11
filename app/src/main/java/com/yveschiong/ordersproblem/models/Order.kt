package com.yveschiong.ordersproblem.models

import java.util.*

data class Order (
    var created_at: Date = Date(),
    var shipping_address: ShippingAddress = ShippingAddress(),
    var customer: Customer = Customer()
)
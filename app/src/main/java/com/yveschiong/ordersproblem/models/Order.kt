package com.yveschiong.ordersproblem.models

import java.util.*

data class Order (
    var shipping_address: ShippingAddress = ShippingAddress(),
    var created_at: Date = Date()
)
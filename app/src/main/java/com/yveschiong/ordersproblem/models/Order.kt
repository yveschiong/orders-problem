package com.yveschiong.ordersproblem.models

data class Order (
    var shipping_address: ShippingAddress = ShippingAddress()
)
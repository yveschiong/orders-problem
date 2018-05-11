package com.yveschiong.ordersproblem.requests

import com.yveschiong.ordersproblem.constants.Info
import com.yveschiong.ordersproblem.interfaces.RequestService

class RequestHandler(private val requestService: RequestService) {
    fun getOrders(page: Int) = requestService.getOrders(page, Info.ACCESS_TOKEN_VAL)
}
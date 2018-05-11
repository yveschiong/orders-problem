package com.yveschiong.ordersproblem.interfaces

import com.yveschiong.ordersproblem.constants.Info
import com.yveschiong.ordersproblem.responses.OrdersResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {
    @GET("admin/orders.json")
    fun getOrders(
            @Query("page") page: Int,
            @Query("access_token") accessToken: String
    ): Observable<OrdersResponse>

    companion object {
        fun create() = Retrofit.Builder()
                .baseUrl(Info.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestService::class.java)
    }
}
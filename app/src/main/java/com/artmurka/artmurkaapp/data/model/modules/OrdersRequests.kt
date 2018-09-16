package com.artmurka.artmurkaapp.data.model.modules

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IOrderRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit

import java.util.HashMap

import retrofit2.Call
import javax.inject.Inject


class OrdersRequests @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ) : IOrderRequest {

    override val orders: Call<Orders>
        get() {
            val mapForUcozModule = HashMap<String, String>()
            mapForUcozModule["sort"] = "id"
            mapForUcozModule["order"] = "desc"
            val confForRequest = ucoz.get("GET", "uapi/shop/invoices/", mapForUcozModule)
            return apiModule.getInvoises(confForRequest)
        }
}

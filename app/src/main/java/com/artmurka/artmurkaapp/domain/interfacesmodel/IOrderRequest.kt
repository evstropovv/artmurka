package com.artmurka.artmurkaapp.domain.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders

import retrofit2.Call


interface IOrderRequest {
    val orders: Call<Orders>
}

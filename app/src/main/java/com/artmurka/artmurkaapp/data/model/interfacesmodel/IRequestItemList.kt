package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample

import io.reactivex.Observable


interface IRequestItemList {
    fun getItemList(page: String, pageNumber: String, sort: String, order: String): Observable<SuccessExample>
}

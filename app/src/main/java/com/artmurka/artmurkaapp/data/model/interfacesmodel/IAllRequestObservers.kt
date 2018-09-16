package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Example

import io.reactivex.Observable


interface IAllRequestObservers {
    val categories: Observable<Example>
}

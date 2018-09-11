package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Example;

import io.reactivex.Observable;


public interface IAllRequestObservers {
    Observable<Example> getCategories();
}

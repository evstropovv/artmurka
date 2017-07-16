package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Retrofit.Example;

import io.reactivex.Observable;


public interface IAllRequestObservers {
    Observable<Example> getCategories();
}

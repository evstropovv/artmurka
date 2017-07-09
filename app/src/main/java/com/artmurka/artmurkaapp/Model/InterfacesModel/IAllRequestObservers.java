package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Retrofit.Example;

import io.reactivex.Observable;

/**
 * Created by Вася on 29.06.2017.
 */

public interface IAllRequestObservers {
    Observable<Example> getCategories();
}

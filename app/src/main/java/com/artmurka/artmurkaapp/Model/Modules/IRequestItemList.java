package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;

import io.reactivex.Observable;

/**
 * Created by Вася on 05.07.2017.
 */

public interface IRequestItemList {
    Observable<Success> getItemList(String page);
}

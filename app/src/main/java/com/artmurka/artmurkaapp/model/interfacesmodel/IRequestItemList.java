package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.SuccessExample;

import io.reactivex.Observable;


public interface IRequestItemList {
    Observable<SuccessExample> getItemList(String page, String pageNumber, String sort, String order);
}

package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;

import io.reactivex.Observable;


public interface IRequestItemList {
    Observable<SuccessExample> getItemList(String page, String pageNumber, String sort, String order);
}

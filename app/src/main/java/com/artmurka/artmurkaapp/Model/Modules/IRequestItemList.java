package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import io.reactivex.Observable;


public interface IRequestItemList {
    Observable<SuccessExample> getItemList(String page, String pageNumber);
}

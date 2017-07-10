package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;

import io.reactivex.Observable;


public interface IBasket {
    Observable<BasketItems> toBasket(String goodId);
    Observable<BasketItems> getItemInBasket();

}

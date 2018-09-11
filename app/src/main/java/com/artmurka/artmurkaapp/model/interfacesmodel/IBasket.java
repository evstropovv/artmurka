package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.BasketItems;

import io.reactivex.Observable;
import retrofit2.Call;


public interface IBasket {
    Observable<BasketItems> toBasket(String goodId);
    Observable<BasketItems> getItemInBasket();
    Call<BasketItems> deleteItemFromBasket(String goodId);
}

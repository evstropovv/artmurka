package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;

import io.reactivex.Observable;
import retrofit2.Call;


public interface IBasket {
    Observable<BasketItems> toBasket(String goodId);
    Observable<BasketItems> getItemInBasket();
    Call<BasketItems> deleteItemFromBasket(String goodId);

}

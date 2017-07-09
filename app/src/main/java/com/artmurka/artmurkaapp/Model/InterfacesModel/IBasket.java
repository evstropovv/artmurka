package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Вася on 09.07.2017.
 */

public interface IBasket {
    Observable<ResponseBody> toBasket(String goodId);

}

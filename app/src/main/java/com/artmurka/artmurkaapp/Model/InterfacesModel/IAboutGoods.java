package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import io.reactivex.Observable;
import retrofit2.Call;

public interface IAboutGoods {
    Call<AboutGood> getDataAboutGood(String id);
    Observable<SuccessExample> getItemList(String page, String pageNumber);
}

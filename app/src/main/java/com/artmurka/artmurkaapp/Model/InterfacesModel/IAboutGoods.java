package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Good.Good;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;

import io.reactivex.Observable;
import retrofit2.Call;

public interface IAboutGoods {
    Call<AboutGood> getDataAboutGood(String id);
    Call<Good> getDataGood(String id);
    Observable<SuccessExample> getItemList(String page, String pageNumber);
}

package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods.AboutGood;
import com.artmurka.artmurkaapp.model.pojo.itemlist.good.Good;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.SuccessExample;

import io.reactivex.Observable;
import retrofit2.Call;

public interface IAboutGoods {
    Call<AboutGood> getDataAboutGood(String id);
    Call<Good> getDataGood(String id);
    Observable<SuccessExample> getItemList(String page, String pageNumber);
}

package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;

import retrofit2.Call;

public interface IAboutGoods {
    Call<AboutGood> getDataAboutGood(String id);
}

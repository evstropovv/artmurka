package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;

import io.reactivex.Observable;
import retrofit2.Call;


public interface IRequestItemList {
    Call<Success> getItemList(String page);
}

package com.artmurka.artmurkaapp.Presenter;

import android.text.Html;
import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IAboutGoods;
import com.artmurka.artmurkaapp.Model.Modules.AboutGoodsRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.Success;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AboutGoodsPresenter implements IAboutGoodsPresenter {

    IFragmentAboutGoods fragment;

    public AboutGoodsPresenter(IFragmentAboutGoods fr) {
        this.fragment = fr;
    }

    @Override
    public void getDataAboutGoods(String id) {
        IAboutGoods model = new AboutGoodsRequest();
        Call<AboutGood> observable = model.getDataAboutGood(id);
        observable.enqueue(new Callback<AboutGood>() {
            @Override
            public void onResponse(Call<AboutGood> call, Response<AboutGood> response) {
                Success aboutGood = response.body().getSuccess();
                fragment.setName(aboutGood.getEntryTitle());
                fragment.setDescription(Html.fromHtml(aboutGood.getEntryDescription()).toString());
                fragment.setPrice(aboutGood.getEntryPrice().getPrice());
                fragment.setPhoto(aboutGood.getEntryPhoto().getDefPhoto().getPhoto());
                Log.d("Log.d", new Gson().toJson(aboutGood.getEntryPhoto().getOthersPhoto()));
            }

            @Override
            public void onFailure(Call<AboutGood> call, Throwable t) {
                Log.d("Log.d", "url " + call.request().url());
            }
        });
//        observable.subscribe(new Observer<AboutGood>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(AboutGood value) {
//                    fragment.setDescription(value.getSuccess().getEntryDescription());
//                    Log.d("Log.d", new Gson().toJson(value.getSuccess()));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("Log.d","onError-AboutGoodsPresenter "+ e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
}

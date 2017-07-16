package com.artmurka.artmurkaapp.Presenter;

import android.text.Html;
import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IAboutGoods;
import com.artmurka.artmurkaapp.Model.Modules.AboutGoodsRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.SizePhoto;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.Success;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
                fragment.setPhoto(getImageList(aboutGood.getEntryPhoto().getOthersPhoto()));

                fragment.getDataForRecyclerView(response.body().getSuccess().getEntryCat().getUrl());
            }

            @Override
            public void onFailure(Call<AboutGood> call, Throwable t) {

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

    @Override
    public void getCategoryData(String category) {
        IAboutGoods model = new AboutGoodsRequest();
        Observable<SuccessExample> observable = model.getItemList(category, "1");
        observable.subscribe(new Observer<SuccessExample>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(SuccessExample value) {

                Log.d("Log.d", new Gson().toJson(value.getSuccess()));
                fragment.setDataForRecyclerView(getGoodsList(value.getSuccess().getGoodsList()));

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {

            }
        });
    }

    private ArrayList<String> getImageList(HashMap<String, SizePhoto> map) {

        ArrayList<String> photosUrl = new ArrayList<>();
        for (String key : map.keySet()) {
            photosUrl.add(map.get(key).getPhoto());
        }
        return photosUrl;
    }


    private ArrayList<GoodsProperties> getGoodsList(HashMap<String, GoodsProperties> map) {
       ArrayList<GoodsProperties> goodsProperties = new ArrayList<>();
        for (String key : map.keySet()) {
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

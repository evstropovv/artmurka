package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IWishList;
import com.artmurka.artmurkaapp.Model.Modules.WishListRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IWishPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IWishFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WishPresenter implements IWishPresenter {
    private IWishFragment fragment;

    public WishPresenter(IWishFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void getDataForWishList() {
        IWishList wishRequest = new WishListRequest();
        Call<WishList> call = wishRequest.getWishList();
        call.enqueue(new Callback<WishList>() {
            @Override
            public void onResponse(Call<WishList> call, Response<WishList> response) {
                Log.d("Log.d ","url " + call.request().url());
                fragment.showWishList(response.body());
            }

            @Override
            public void onFailure(Call<WishList> call, Throwable t) {
                Log.d("Log.d","onFailure WishList FALSE " + t.toString());
            }
        });
    }
}

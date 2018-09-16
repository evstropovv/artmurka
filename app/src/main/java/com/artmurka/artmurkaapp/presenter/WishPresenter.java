package com.artmurka.artmurkaapp.presenter;

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList;
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WishPresenter implements IWishPresenter {
    private IWishFragment fragment;

    public WishPresenter(IWishFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void getDataForWishList() {
        IWishList wishRequest = new WishListRequest();
        Call<WishList> call = wishRequest.getWishList();
        call.enqueue(new Callback<WishList>() {
            @Override
            public void onResponse(Call<WishList> call, Response<WishList> response) {
                try {
                    fragment.showWishList(getList(response.body().getSuccess().getGoodsList()));
                } catch (NullPointerException e) {
                }

            }

            @Override
            public void onFailure(Call<WishList> call, Throwable t) {
            }
        });
    }

    private List<GoodsListDescription> getList(HashMap<String, GoodsListDescription> map) {
        List<GoodsListDescription> answerList = new ArrayList<>();
        for (String key : map.keySet()) {
            answerList.add(map.get(key));
        }
        return answerList;
    }

}

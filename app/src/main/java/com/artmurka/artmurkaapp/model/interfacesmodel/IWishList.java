package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.WishList;

import retrofit2.Call;

public interface IWishList {
    Call<WishList> toWishList(String goods_id);
    Call<WishList> getWishList();
}

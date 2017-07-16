package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;

import retrofit2.Call;

public interface IWishList {
    Call<WishList> toWishList(String goods_id);
}

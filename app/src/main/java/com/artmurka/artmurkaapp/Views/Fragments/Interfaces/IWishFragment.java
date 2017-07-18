package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.GoodsListDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;

import java.util.ArrayList;
import java.util.List;


public interface IWishFragment {
    void showWishList(List<GoodsListDescription> list );
}

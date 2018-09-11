package com.artmurka.artmurkaapp.views.fragments.interfaces;

import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;

import java.util.ArrayList;

public interface IItemListFragment {
    void showItemList(ArrayList<GoodsProperties> goodsProperties); //какой то эррейлист
    void showError(String error);
    void setTitle(String title);
    void stopProgressBar();
}

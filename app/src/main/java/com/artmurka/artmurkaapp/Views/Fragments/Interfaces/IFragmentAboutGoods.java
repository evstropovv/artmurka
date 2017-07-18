package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import java.util.ArrayList;

public interface IFragmentAboutGoods {

    void setName(String name);
    void setPhoto(ArrayList<String> urles);
    void setPrice(String price);
    void setDescription(String description);
    void getDataForRecyclerView(String category);

    void setFullDescription(String fullDescription);
    void setDataForRecyclerView(ArrayList<GoodsProperties> list);
    void setWishButton(boolean isInWish);
    void setBasketButton(boolean isInBasket);

}

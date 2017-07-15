package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

public interface IFragmentAboutGoods {

    void setName(String name);
    void setPhoto(String url);
    void setPrice(String price);
    void setDescription(String description);

    void setFullDescription(String fullDescription);
    void setDataForRecyclerView();

}

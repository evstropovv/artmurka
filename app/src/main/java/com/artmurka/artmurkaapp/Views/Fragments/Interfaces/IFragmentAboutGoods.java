package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import java.util.ArrayList;

public interface IFragmentAboutGoods {

    void setName(String name);
    void setPhoto(ArrayList<String> urles);
    void setPrice(String price);
    void setDescription(String description);

    void setFullDescription(String fullDescription);
    void setDataForRecyclerView();

}

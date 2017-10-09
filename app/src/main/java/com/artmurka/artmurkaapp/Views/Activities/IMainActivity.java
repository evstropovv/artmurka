package com.artmurka.artmurkaapp.Views.Activities;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.List;

public interface IMainActivity {
    void changeFragment(int currentFragment, String url,List<Success> childs, String catName);
}

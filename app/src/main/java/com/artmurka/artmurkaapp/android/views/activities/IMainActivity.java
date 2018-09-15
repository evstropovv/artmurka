package com.artmurka.artmurkaapp.android.views.activities;


import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;

import java.util.List;

public interface IMainActivity {
    void changeFragment(int currentFragment, String url,List<Success> childs, String catName);
}

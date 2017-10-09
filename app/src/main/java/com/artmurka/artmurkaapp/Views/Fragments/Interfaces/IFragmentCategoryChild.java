package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.ArrayList;

public interface IFragmentCategoryChild {
    void showCategories(ArrayList<Success> categoriesList);
    void showError(String msg);
}

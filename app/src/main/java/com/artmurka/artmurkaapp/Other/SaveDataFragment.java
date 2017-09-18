package com.artmurka.artmurkaapp.Other;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.ArrayList;

public class SaveDataFragment extends Fragment {
    private ArrayList<Success> successList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public ArrayList<Success> getCategories() {
        return successList;
    }

    public void setCategories(ArrayList<Success> list) {
        this.successList = list;
    }
}

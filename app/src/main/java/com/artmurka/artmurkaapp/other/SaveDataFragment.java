package com.artmurka.artmurkaapp.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success;

import java.util.List;

public class SaveDataFragment extends Fragment {
    private List<Success> successList;
    private List<Success> childs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<Success> getCategories() {
        return successList;
    }

    public void setCategories(List<Success> list) {
        this.successList = list;
        this.childs = null;
    }

    public List<Success> getChilds() {
        return childs;
    }

    public void setChilds(List<Success> list) {
        this.childs = list;
    }
}

package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.Modules.RequestItemList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IPresenterItemList;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IItemListFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ItemListPresenter implements IPresenterItemList {
    private IItemListFragment fragment;
    private static Observable<SuccessExample> exampleObservable;
    private String url;
    private ArrayList<GoodsProperties> goodsProperties;
    private boolean isFull = false;


    public ItemListPresenter(IItemListFragment fragment, String url) {
        this.fragment = fragment;
        this.url = url;
        goodsProperties = new ArrayList<>();
    }


    @Override
    public void getCategoriesData(int curPage) {
        if (!isFull) {
            RequestItemList model = new RequestItemList();
            exampleObservable = model.getItemList(url, String.valueOf(curPage));
            exampleObservable.subscribe(new Observer<SuccessExample>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d("Log.d", "onSubscribe " + d.toString());
                }

                @Override
                public void onNext(SuccessExample value) {
                    Log.d("Log.d", "URL " + new Gson().toJson(value));
                    int i = (value.getSuccess().getGoodsList().size());
                    Log.d("Log.d", "goodList.size() " + i);
                    fragment.showItemList(getList(value.getSuccess().getGoodsList()));
                    fragment.setTitle(value.getSuccess().getCatName());
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("Log.d", "onError " + e.toString());
                    isFull = true;
                }

                @Override
                public void onComplete() {
                    Log.d("Log.d", "onComplete ");
                }
            });
        }
    }

    private ArrayList<GoodsProperties> getList(HashMap<String, GoodsProperties> map) {
        for (String key : map.keySet()) {
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

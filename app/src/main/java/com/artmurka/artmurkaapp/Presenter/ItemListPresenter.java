package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.Modules.RequestItemList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IPresenterItemList;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IItemListFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ItemListPresenter implements IPresenterItemList {
    private IItemListFragment fragment;
    private static Observable<SuccessExample> exampleObservable;
    private String url, sort, order;
    private ArrayList<GoodsProperties> goodsProperties;
    private boolean isFull = false;

    public ItemListPresenter(IItemListFragment fragment, String url, String sort, String order) {
        this.fragment = fragment;
        this.url = url;
        this.sort = sort;
        this.order = order;
        goodsProperties = new ArrayList<>();
    }


    @Override
    public void getCategoriesData(int curPage) {
        if (!isFull) {
            RequestItemList model = new RequestItemList();
            exampleObservable = model.getItemList(url, String.valueOf(curPage), sort, order);
            exampleObservable.subscribe(new Observer<SuccessExample>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(SuccessExample value) {
                    fragment.showItemList(getList(value.getSuccess().getGoodsList()));
                    fragment.setTitle(value.getSuccess().getCatName());
                }

                @Override
                public void onError(Throwable e) {
                    isFull = true;
                    Log.d("Log.d", e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        }
    }

    private ArrayList<GoodsProperties> getList(TreeMap<String, GoodsProperties> map) {
        for (String key : map.keySet()) {
            Log.d("Log.d", key + " " +map.get(key).getEntryPrice().getPriceRaw());
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

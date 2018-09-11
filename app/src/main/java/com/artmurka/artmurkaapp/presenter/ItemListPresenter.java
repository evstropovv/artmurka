package com.artmurka.artmurkaapp.presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.model.modules.RequestItemList;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.SuccessExample;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IPresenterItemList;
import com.artmurka.artmurkaapp.views.fragments.interfaces.IItemListFragment;

import java.util.ArrayList;
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


    @Override
    public void onDetach() {
        fragment = null;
    }

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
                    if (fragment != null) {
                        fragment.showItemList(getList(value.getSuccess().getGoodsList()));
                        fragment.setTitle(value.getSuccess().getCatName());
                        fragment.stopProgressBar();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (fragment != null) {
                        isFull = true;
                        fragment.stopProgressBar();
                        Log.d("Log.d", e.getMessage());
                    }
                }

                @Override
                public void onComplete() {
                    if (fragment != null) fragment.stopProgressBar();
                }
            });
        } else {
            if (fragment != null) fragment.stopProgressBar();
        }
    }

    private ArrayList<GoodsProperties> getList(TreeMap<String, GoodsProperties> map) {
        for (String key : map.keySet()) {
            Log.d("Log.d", key + " " + map.get(key).getEntryPrice().getPriceRaw());
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

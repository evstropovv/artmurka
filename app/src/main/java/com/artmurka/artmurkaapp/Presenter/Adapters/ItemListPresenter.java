package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.artmurka.artmurkaapp.Model.Modules.RequestItemList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.Presenter.IPresenterItemList;
import com.artmurka.artmurkaapp.Views.Fragments.IItemListFragment;
import com.artmurka.artmurkaapp.Views.Fragments.ItemListFragment;
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



    public ItemListPresenter(IItemListFragment fragment, String url) {
        this.fragment = fragment;
        this.url = url;
    }


    @Override
    public void getCategoriesData() {

        RequestItemList model = new RequestItemList();
        exampleObservable = model.getItemList(url);
        exampleObservable.subscribe(new Observer<SuccessExample>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Log.d", "onSubscribe " + d.toString());
            }

            @Override
            public void onNext(SuccessExample value) {

                Log.d("Log.d", "URL " + new Gson().toJson(value));

                fragment.showItemList(getList(value.getSuccess().getGoodsList()));
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Log.d", "onError " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Log.d", "onComplete ");
            }
        });

    }

    private ArrayList<GoodsProperties> getList(HashMap<String, GoodsProperties> map){
        ArrayList<GoodsProperties> goodsProperties = new ArrayList<>();
        for (String key: map.keySet()){
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

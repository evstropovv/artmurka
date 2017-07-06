package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.Model.Modules.AllRequestOvservers;
import com.artmurka.artmurkaapp.Model.Modules.RequestItemList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;
import com.artmurka.artmurkaapp.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ItemListFragmentFragment extends Fragment implements IItemListFragment {

    private static Observable<Success> exampleObservable;

    public ItemListFragmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestItemList model = new RequestItemList();
        exampleObservable = model.getItemList("string-art");
        exampleObservable.subscribe(new Observer<Success>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Log.d", "onSubscribe " + d.toString());
            }

            @Override
            public void onNext(Success value) {
                Log.d("Tag.t", value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Log.d", "onError " + e.toString());

            }

            @Override
            public void onComplete() { }
        });
    }

    @Override
    public void showItemList() {

    }

    @Override
    public void showError(String error) {

    }
}

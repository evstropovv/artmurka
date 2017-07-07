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
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemListFragmentFragment extends Fragment implements IItemListFragment {

    private static Observable<Success> exampleObservable;
    String url = "string-art";
    public ItemListFragmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState!=null && savedInstanceState.getString("url")!=null){
           this.url = savedInstanceState.getString("url");
        }
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestItemList model = new RequestItemList();
        model.getItemList(url).enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                Log.d("Log.d","onResponse: " + "Response : "+  new Gson().toJson(response) );
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {

            }
        });
//        exampleObservable = model.getItemList("string-art");
//        exampleObservable.subscribe(new Observer<Success>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("Log.d", "onSubscribe " + d.toString());
//            }
//
//            @Override
//            public void onNext(Success value) {
//                Log.d("Log.d", "onNext " + new Gson().toJson(value));
//            }
//            @Override
//            public void onError(Throwable e) {
//                Log.d("Log.d", "onError " + e.toString());}
//            @Override
//            public void onComplete() {
//                Log.d("Log.d", "onComplete ");
//            }
//        });
    }
    @Override
    public void showItemList() {
    }
    @Override
    public void showError(String error) {

    }
}

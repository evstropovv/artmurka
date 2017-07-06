package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.Modules.AllRequestOvservers;
import com.artmurka.artmurkaapp.Model.Modules.IAllRequestObservers;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.artmurka.artmurkaapp.Views.Fragments.ICategoryFragment;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryFragment catFragment;
    private IAllRequestObservers model;
    private static ArrayList<Success> successList;
    private static Observable<Example> exampleObservable;


    public CategoryPresenter(ICategoryFragment fragment) {
        catFragment = fragment;
        model = new AllRequestOvservers();
    }

    @Override
    public void getCategoriesData(final boolean isUpdate) {
        if (successList == null) {
            resetData();
        } else {
            catFragment.showCategories(successList);
        }
    }

    private void  resetData(){
            exampleObservable = model.getCategories();
            exampleObservable.subscribe(new Observer<Example>() {
                @Override
                public void onSubscribe(Disposable d) {
                    
                }
                @Override
                public void onNext(Example value) {
                    successList = value.getSuccess();
                    if (successList.isEmpty()) catFragment.showError("Error, succes list is Empty");
                    else catFragment.showCategories(successList);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    Log.d("Log.d", "onComplete");
                }
            });
    }
}

package com.artmurka.artmurkaapp.Presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.artmurka.artmurkaapp.Model.Modules.AllRequestOvservers;
import com.artmurka.artmurkaapp.Model.InterfacesModel.IAllRequestObservers;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Example;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;
import com.artmurka.artmurkaapp.Other.SaveDataFragment;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICategoryFragment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryFragment catFragment;
    private IAllRequestObservers model;
    private ArrayList<Success> successList;
    private Observable<Example> exampleObservable;
    private Context ctx;

    public CategoryPresenter(ICategoryFragment fragment, Context context) {
        catFragment = fragment;
        this.ctx = context;
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

    private void resetData() {

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
                catFragment.showError("Щось пішло не так. Перезавантажити ?");
                Log.d("Log.d", e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }
}

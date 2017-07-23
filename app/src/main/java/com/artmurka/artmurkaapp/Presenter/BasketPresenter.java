package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.Modules.BasketRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IBasketPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IBasketFragment;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class BasketPresenter implements IBasketPresenter {
    IBasketFragment fragment;

    public BasketPresenter(IBasketFragment fragment){
        this.fragment = fragment;
    }


    @Override
    public void getDataForbasket() {

        final IBasket basket = new BasketRequest();
        Observable<BasketItems> observable = basket.getItemInBasket();
        observable.subscribe(new Observer<BasketItems>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(BasketItems value) {
                fragment.showItemsInBasket(value.getSuccess().getBasket().getItems());
                fragment.makeMessageInvisible(true);
                fragment.showPrice(value.getSuccess().getBasket().getTotal() + " грн.");
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {
            }
        });
    }
}

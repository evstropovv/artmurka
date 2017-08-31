package com.artmurka.artmurkaapp.Presenter;

import android.text.Html;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IAboutGoods;
import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.InterfacesModel.IWishList;
import com.artmurka.artmurkaapp.Model.Modules.AboutGoodsRequest;
import com.artmurka.artmurkaapp.Model.Modules.BasketRequest;
import com.artmurka.artmurkaapp.Model.Modules.WishListRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.SizePhoto;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.Success;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.GoodsListDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutGoodsPresenter implements IAboutGoodsPresenter {

    private IFragmentAboutGoods fragment;
    private String goodsId;

    public AboutGoodsPresenter(IFragmentAboutGoods fr) {
        this.fragment = fr;
    }

    @Override
    public void getDataAboutGoods(String id) {
        this.goodsId = id;
        IAboutGoods model = new AboutGoodsRequest();
        Call<AboutGood> observable = model.getDataAboutGood(id);
        observable.enqueue(new Callback<AboutGood>() {
            @Override
            public void onResponse(Call<AboutGood> call, Response<AboutGood> response) {
                Success aboutGood = response.body().getSuccess();
                fragment.setName(aboutGood.getEntryTitle());
                fragment.setDescription(Html.fromHtml(aboutGood.getEntryDescription()).toString());
                fragment.setPrice(aboutGood.getEntryPrice().getPrice());
                fragment.setPhoto(getImageList(aboutGood.getEntryPhoto().getOthersPhoto()));
                fragment.getDataForRecyclerView(response.body().getSuccess().getEntryCat().getUrl());
                fragment.setWishButton(aboutGood.getEntryIsInWishlist() == 1 ? true : false);
                fragment.setBasketButton(aboutGood.getEntryIsInBasket() > 0 ? true : false);
            }

            @Override
            public void onFailure(Call<AboutGood> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCategoryData(String category) {
        IAboutGoods model = new AboutGoodsRequest();
        Observable<SuccessExample> observable = model.getItemList(category, "1");
        observable.subscribe(new Observer<SuccessExample>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(SuccessExample value) {
                fragment.setDataForRecyclerView(getGoodsList(value.getSuccess().getGoodsList()));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void btnClicked(int buttonId) {
        switch (buttonId) {
            case R.id.ivWish:
                //добавить в список желаний
                IWishList iWishList = new WishListRequest();
                Call<WishList> obs = iWishList.toWishList(goodsId);
                obs.enqueue(new Callback<WishList>() {
                    @Override
                    public void onResponse(Call<WishList> call, Response<WishList> response) {
                        try {
                            for (Map.Entry<String, GoodsListDescription> map : response.body().getSuccess().getGoodsList().entrySet()) {
                                if (map.getValue().getEntryId().equals(goodsId)) {
                                    fragment.setWishButton(true);
                                    break;
                                } else {
                                    fragment.setWishButton(false);
                                }
                            }
                        } catch (NullPointerException e) {
                            fragment.setWishButton(false);
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<WishList> call, Throwable t) {
                    }
                });

                break;
            case R.id.ivBasket:
                //кнопка добавления в корзину
                IBasket basket = new BasketRequest();
                Observable<BasketItems> observable = basket.toBasket(goodsId);
                observable.subscribe(new Observer<BasketItems>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BasketItems value) {
                        fragment.setBasketButton(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            default:
                break;
        }


    }

    private ArrayList<String> getImageList(HashMap<String, SizePhoto> map) {

        ArrayList<String> photosUrl = new ArrayList<>();
        for (String key : map.keySet()) {
            photosUrl.add(map.get(key).getPhoto());
        }
        return photosUrl;
    }


    private ArrayList<GoodsProperties> getGoodsList(HashMap<String, GoodsProperties> map) {
        ArrayList<GoodsProperties> goodsProperties = new ArrayList<>();
        for (String key : map.keySet()) {
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}

package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.model.interfacesmodel.ICheckoutRequest;
import com.artmurka.artmurkaapp.model.interfacesmodel.IWishList;
import com.artmurka.artmurkaapp.model.modules.CheckoutRequest;
import com.artmurka.artmurkaapp.model.modules.WishListRequest;
import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.Item;

import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.WishList;
import com.artmurka.artmurkaapp.R;

import com.artmurka.artmurkaapp.android.views.activities.SelectedGood;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVbasketAdapter extends RecyclerView.Adapter<RVbasketAdapter.ViewHolder> {
    private Context ctx;
    List<Item> basketItemList;
    IBasketFragment fragment ;
    public RVbasketAdapter(Context context, IBasketFragment fragment) {
        this.ctx = context;
        this.fragment = fragment;
        basketItemList = new ArrayList<>();
    }

    public void setData(List<Item> list) {
        if (basketItemList != null && list.size() > 0) {
            this.basketItemList.clear();
            this.basketItemList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.basket_item,parent,false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.goodName.setText(basketItemList.get(position).getEntryTitle());
        holder.goodDescription.setText(basketItemList.get(position).getCnt() + " шт. " +basketItemList.get(position).getEntryPrice().getPrice());
        holder.goodPrice.setText( basketItemList.get(position).getSumm().getSummRaw()+ " грн.");
        Picasso.with(ctx).load(basketItemList.get(position).getEntryPhoto().getThumb()).into(holder.itemPhoto);
        Log.d("Log.d", new Gson().toJson(basketItemList.get(position)));

        holder.basketMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ctx, holder.basketMenu);
                popupMenu.inflate(R.menu.basket_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.delete_from_basket:
                                //delete from basket
                                refreshItemRequest(0, position);  // 0  = delete
                                basketItemList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, basketItemList.size());
                                changePrice();
                                break;
                            case R.id.wish_wad:
                                //add to wishList
                                Toast.makeText(ctx, basketItemList.get(position).getEntryTitle() + " додано до бажань", Toast.LENGTH_SHORT).show();
                                IWishList iWishList = new WishListRequest();
                                Call<WishList> obs = iWishList.toWishList(basketItemList.get(position).getEntryId());
                                obs.enqueue(new Callback<WishList>() {
                                    @Override
                                    public void onResponse(Call<WishList> call, Response<WishList> response) {
                                    }

                                    @Override
                                    public void onFailure(Call<WishList> call, Throwable t) {
                                    }
                                });

                                break;
                            default:

                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

    }

    private void changePrice() {
        float sum = 0;
        if (basketItemList.size()>0) {
            for (int i = 0; i < basketItemList.size(); i++) {
                sum = sum + Float.parseFloat(basketItemList.get(i).getEntryPrice().getPriceRaw());
            }
        }
        fragment.showPrice(sum+"");
    }

    private void refreshItemRequest(int cnt, int position){

        ICheckoutRequest request = new CheckoutRequest();
        Call<CheckoutAllGoods> call = request.recountCheckoutData(basketItemList.get(position).getId(), String.valueOf(cnt));
        call.enqueue(new Callback<CheckoutAllGoods>() {
            @Override
            public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {

            }
            @Override
            public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {}
        });
    }

    @Override
    public int getItemCount() {
        return basketItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView goodName, goodDescription, goodPrice;
        public ImageView itemPhoto, basketMenu;


        public ViewHolder(View itemView) {
            super(itemView);
            goodName = (TextView) itemView.findViewById(R.id.good_name);
            goodDescription = (TextView) itemView.findViewById(R.id.good_description);
            goodPrice = (TextView) itemView.findViewById(R.id.price);
            itemPhoto = (ImageView) itemView.findViewById(R.id.ivItemPhoto);
            basketMenu = (ImageView) itemView.findViewById(R.id.basket_menu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SelectedGood.class);
                    String id = basketItemList.get(getAdapterPosition()).getEntryId();
                    intent.putExtra("id",id);
                    intent.putExtra("inWish", basketItemList.get(getAdapterPosition()).getEntryIsInWishlist());
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}

package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IBasket;
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList;
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest;
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RVwishListAdapter extends RecyclerView.Adapter<RVwishListAdapter.ViewHolder> {
    private Context ctx;
    private List<GoodsListDescription> wishList;

    public RVwishListAdapter(Context context) {
        this.ctx = context;
        wishList = new ArrayList<>();
    }

    public void setData(List<GoodsListDescription> list) {
        if (wishList != null && list.size() > 0) {
            this.wishList.clear();
            this.wishList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.card_wishlist, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(ctx).load(wishList.get(position).getEntryPhoto().getDefPhoto().getThumb()).into(holder.ivItemPhoto);
        holder.tvCategoryName.setText(wishList.get(position).getEntryTitle());
        holder.tvPrice.setText(wishList.get(position).getEntryPrice().getPriceRaw()+ " грн.");
        holder.ivToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wishList.get(position).getEntryIsInBasket() == 0) {
                    //в корзину
                    IBasket basket = new BasketRequest();
                    Observable<BasketItems> observable = basket.toBasket(wishList.get(position).getEntryId());

                    observable.subscribe(new Observer<BasketItems>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(BasketItems value) {
                             Toast.makeText(ctx, wishList.get(position).getEntryTitle() + " успішно додано до кошика", Toast.LENGTH_SHORT).show();
                            String goodsId = wishList.get(position).getEntryId();
                            deleteFromWishOnline(goodsId);
                            wishList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, wishList.size());
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
        });
        holder.ivDeleteFromWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodsId = wishList.get(position).getEntryId();
                deleteFromWishOnline(goodsId);
                wishList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, wishList.size());
            }
        });
    }


    private void deleteFromWishOnline(String goodsId) {
        //delete from wishlist online
        IWishList iWishList = new WishListRequest();
        Call<WishList> obs = iWishList.toWishList(goodsId); //здесь по запросу toWishList - или удаляется если она есть, или добавляется если позиции в списке нет
        obs.enqueue(new Callback<WishList>() {
            @Override
            public void onResponse(Call<WishList> call, Response<WishList> response) {
            }
            @Override
            public void onFailure(Call<WishList> call, Throwable t) {}
        });
    }


    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategoryName, tvPrice;
        public ImageView ivItemPhoto, ivToBasket, ivDeleteFromWish;


        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            ivItemPhoto = (ImageView) itemView.findViewById(R.id.ivItemPhoto);
            ivToBasket = (ImageView) itemView.findViewById(R.id.ivToBasket);
            ivDeleteFromWish = (ImageView) itemView.findViewById(R.id.ivDeleteFromWish);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SelectedGoodActivity.class);
                    String id = wishList.get(getAdapterPosition()).getEntryId();
                    intent.putExtra("id",id);
                    intent.putExtra("inWish", wishList.get(getAdapterPosition()).getEntryIsInWishlist());
                    intent.putExtra("inBasket", wishList.get(getAdapterPosition()).getEntryIsInBasket());
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}

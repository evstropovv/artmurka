package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.model.interfacesmodel.IBasket;
import com.artmurka.artmurkaapp.model.interfacesmodel.IWishList;
import com.artmurka.artmurkaapp.model.modules.BasketRequest;
import com.artmurka.artmurkaapp.model.modules.WishListRequest;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.BasketItems;
import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.WishList;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.SelectedGood;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVitemListAdapter extends RecyclerView.Adapter<RVitemListAdapter.ViewHolder> {
    private ArrayList<GoodsProperties> successList;
    Context ctx;

    public RVitemListAdapter(Context context){
        this.ctx = context;
        successList = new ArrayList<>();
    }

    public void setData(ArrayList<GoodsProperties> list){
        if (list!=null && list.size()>0){
            this.successList.clear();
            this.successList.addAll(list);
            notifyDataSetChanged();
            Log.d("Log.d","success list :" + new Gson().toJson(successList));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvItemName.setText(successList.get(position).getEntryTitle());
        Picasso.with(ctx).load(successList.get(position).getEntryPhoto().getDefPhoto().getThumb()).into(holder.ivItemPhoto);
        holder.ivMenu.setOnClickListener((View v) -> {
            PopupMenu popupMenu = new PopupMenu(ctx, holder.ivMenu);
            popupMenu.inflate(R.menu.item_menu);

            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.to_card:
                        //проверяем, есть ли такой товар в корзине
                        if (successList.get(position).getEntryIsInBasket()==0) {
                            //в корзину
                            IBasket basket = new BasketRequest();
                            Observable<BasketItems> observable = basket.toBasket(successList.get(position).getEntryId());

                            observable.subscribe(new Observer<BasketItems>() {
                                @Override
                                public void onSubscribe(Disposable d) {}

                                @Override
                                public void onNext(BasketItems value) {
                                         Toast.makeText(ctx, successList.get(position).getEntryTitle() + " успішно додано до кошика", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onError(Throwable e) {
                                  ;
                                }
                                @Override
                                public void onComplete() {
                                }
                            });
                        }
                        break;
                    case R.id.wish_wad:
                        //в список пожеланий
                        IWishList iWishList = new WishListRequest();
                        Call<WishList> obs = iWishList.toWishList(successList.get(position).getEntryId());
                        obs.enqueue(new Callback<WishList>() {
                            @Override
                            public void onResponse(Call<WishList> call, Response<WishList> response) {
                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано до бажань", Toast.LENGTH_SHORT).show();
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
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public ImageView ivItemPhoto;
        public ImageView ivMenu;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvItemName = (TextView)itemView.findViewById(R.id.item_name);
            ivItemPhoto = (ImageView)itemView.findViewById(R.id.ivItemPhoto);
            ivMenu = (ImageView)itemView.findViewById(R.id.item_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //to aboutGoods activity
                    Intent intent = new Intent(itemView.getContext(), SelectedGood.class);
                    String id = successList.get(getAdapterPosition()).getEntryId();
                    intent.putExtra("id",id);
                    intent.putExtra("inWish", successList.get(getAdapterPosition()).getEntryIsInWishlist());
                    intent.putExtra("inBasket", successList.get(getAdapterPosition()).getEntryIsInBasket());
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}

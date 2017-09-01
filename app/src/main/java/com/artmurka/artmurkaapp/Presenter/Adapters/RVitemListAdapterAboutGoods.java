package com.artmurka.artmurkaapp.Presenter.Adapters;

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

import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.Modules.BasketRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.SelectedGood;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RVitemListAdapterAboutGoods extends RecyclerView.Adapter<RVitemListAdapterAboutGoods.ViewHolder> {
    private ArrayList<GoodsProperties> successList;
    Context ctx;

    public RVitemListAdapterAboutGoods(Context context){
        this.ctx = context;
        successList = new ArrayList<>();
    }

    public void setData(ArrayList<GoodsProperties> list){
        if (list!=null && list.size()>0){
            this.successList.clear();
            this.successList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_about_goods, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvItemName.setText(successList.get(position).getEntryTitle());
        Picasso.with(ctx).load(successList.get(position).getEntryPhoto().getDefPhoto().getThumb()).into(holder.ivItemPhoto);
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ctx, holder.ivMenu);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.to_card:
                                //в корзину
                                IBasket basket = new BasketRequest();
                                Observable<BasketItems> observable = basket.toBasket(successList.get(position).getEntryId());
                                                             observable.subscribe(new Observer<BasketItems>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {}

                                    @Override
                                    public void onNext(BasketItems value) {
                                        Log.d("Log.d", new Gson().toJson(value.getSuccess().getBasket()));
                                        Toast.makeText(ctx, successList.get(position).getEntryTitle() + " успішно додано до кошика.", Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("Log.d", "onError " + e.toString());
                                    }
                                    @Override
                                    public void onComplete() {
                                    }
                                });
                                break;
                            case R.id.wish_wad:

                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано до бажань", Toast.LENGTH_SHORT).show();
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

                    Intent intent = new Intent(itemView.getContext(), SelectedGood.class);
                    String id = successList.get(getAdapterPosition()).getEntryId();
                    intent.putExtra("id",id);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}

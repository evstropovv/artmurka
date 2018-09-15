package com.artmurka.artmurkaapp.presenter.adapters;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.SelectedGood;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVitemListGridAdapter extends RecyclerView.Adapter<RVitemListGridAdapter.ViewHolder> {
    private ArrayList<GoodsProperties> successList;
    Context ctx;

    public RVitemListGridAdapter(Context context){
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
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_grid, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(ctx).load(successList.get(position).getEntryPhoto().getDefPhoto().getPhoto()).into(holder.ivItemPhoto);
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName;
        public ImageView ivItemPhoto;


        public ViewHolder(final View itemView) {
            super(itemView);
            tvItemName = (TextView)itemView.findViewById(R.id.item_name);
            ivItemPhoto = (ImageView)itemView.findViewById(R.id.ivItemPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //to aboutGoods activity
                    Intent intent = new Intent(itemView.getContext(), SelectedGood.class);
                    String id = successList.get(getAdapterPosition()).getEntryId();
                    intent.putExtra("id",id);
                    intent.putExtra("inWish", successList.get(getAdapterPosition()).getEntryIsInWishlist());
                    intent.putExtra("inBasket", successList.get(getAdapterPosition()).getEntryIsInBasket());

                    Activity activity = (Activity) ctx;
                    activity.startActivity(intent);
                 //   activity.overridePendingTransition(R.drawable.fadein, R.drawable.fadeout);

                }
            });
        }
    }
}
package com.artmurka.artmurkaapp.Presenter.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVcategoryAdapter extends RecyclerView.Adapter<RVcategoryAdapter.ViewHolder> {
    private List<Success> successList;
    private Context ctx;

    public RVcategoryAdapter(Context context){
        this.ctx = context;
        successList = new ArrayList<>();
    }

    public void setData(List<Success> list){
        if (list!=null && list.size()>0){
            this.successList.clear();
            this.successList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(successList.get(position).getCatName());
        Picasso.with(ctx).load(successList.get(position).getCatImg()).into(holder.ivCategoryImage);
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategoryName;
        public ImageView ivCategoryImage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView)itemView.findViewById(R.id.category_name);
            ivCategoryImage = (ImageView)itemView.findViewById(R.id.item_ph);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),successList.get(getAdapterPosition()).getCatUrl() +"\n", Toast.LENGTH_SHORT).show();
                    MainActivity activity = (MainActivity)v.getContext();
                    activity.changeFragment(Const.ITEM_LIST_FRAGMENT,successList.get(getAdapterPosition()).getCatUrl());

                }
            });
        }
    }
}
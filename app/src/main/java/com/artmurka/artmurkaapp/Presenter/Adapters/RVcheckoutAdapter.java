package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVcheckoutAdapter extends RecyclerView.Adapter<RVcheckoutAdapter.ViewHolder> {
    List<OrderDesc> orderList;
    Context context;

    public RVcheckoutAdapter(Context context){
        this.context = context;
        orderList = new ArrayList<>();
    }

    public void setData(List<OrderDesc> orderList){
        if (orderList!=null && orderList.size()>0){
            this.orderList.clear();
            this.orderList.addAll(orderList);
            notifyDataSetChanged();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_checkout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCategoryName.setText(orderList.get(position).getName());
        holder.tvPrice.setText(orderList.get(position).getPrice().getPrice());
        holder.tvAmount.setText(orderList.get(position).getCnt());
        holder.tvSumPrice.setText(orderList.get(position).getSum().getSumRaw() + " грн/шт");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCategoryName, tvPrice, tvAmount, tvSumPrice;
        public ImageView ivPlus, ivMinus, ivDeleteFromWish;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView)itemView.findViewById(R.id.tvCategoryName);
            tvPrice = (TextView)itemView.findViewById(R.id.tvPrice);
            tvAmount = (TextView)itemView.findViewById(R.id.tvAmount);
            tvSumPrice = (TextView)itemView.findViewById(R.id.tvSumPrice);

            ivPlus = (ImageView)itemView.findViewById(R.id.ivPlus);
            ivMinus = (ImageView)itemView.findViewById(R.id.ivMinus);
            ivDeleteFromWish = (ImageView)itemView.findViewById(R.id.ivDeleteFromWish);

        }
    }
}

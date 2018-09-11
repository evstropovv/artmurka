package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.model.interfacesmodel.ICheckoutRequest;
import com.artmurka.artmurkaapp.model.modules.CheckoutRequest;
import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.OrderDesc;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.views.fragments.interfaces.ICheckoutFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RVcheckoutAdapter extends RecyclerView.Adapter<RVcheckoutAdapter.ViewHolder> {
    List<OrderDesc> orderList;
    Context context;
    ICheckoutFragment fragment;

    public RVcheckoutAdapter(Context context, ICheckoutFragment fragment){
        this.context = context;
        orderList = new ArrayList<>();
        this.fragment = fragment;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(orderList.get(position).getName());
        holder.tvPrice.setText(orderList.get(position).getPrice().getPriceRaw() + " грн/шт");
        holder.tvAmount.setText(orderList.get(position).getCnt());
        holder.tvSumPrice.setText(orderList.get(position).getSum().getSumRaw() +" грн");

        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //при клике увеличиваем количество на 1
                int cnt = Integer.parseInt(orderList.get(position).getCnt());
                refreshItemCnt(++cnt, position);
                refreshItemRequest(cnt, position);

            }
        });
        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cnt = Integer.parseInt(orderList.get(position).getCnt());
                --cnt;
                if (cnt <1) cnt=1;
                refreshItemCnt(cnt, position);
                refreshItemRequest(cnt, position);

            }
        });
        holder.ivDeleteFromCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshItemRequest(0, position);  // 0  = delete
                orderList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, orderList.size());
                refreshItemCnt();

            }
        });


    }

    private void refreshItemCnt(int cnt, int position){
        if (cnt<1) cnt=1;
        Float price = orderList.get(position).getPrice().getPriceRaw();
        orderList.get(position).setCnt(String.valueOf(cnt));
        orderList.get(position).getSum().setSumRaw(Float.parseFloat(cnt * price+""));
        notifyItemChanged(position);

        int sumPrice =0;
        for (int i = 0; i <orderList.size() ; i++) {
            sumPrice = sumPrice +  orderList.get(i).getSum().getSumRaw().intValue();
        }

        fragment.refreshSumPrice(String.valueOf(sumPrice) + " грн");
    }

    private void refreshItemCnt(){
        int sumPrice =0;
        for (int i = 0; i <orderList.size() ; i++) {
            sumPrice = sumPrice +  orderList.get(i).getSum().getSumRaw().intValue();
        }
        fragment.refreshSumPrice(String.valueOf(sumPrice) + " грн");
    }

    private void refreshItemRequest(int cnt, int position){

        ICheckoutRequest request = new CheckoutRequest();
        Call<CheckoutAllGoods> call = request.recountCheckoutData(orderList.get(position).getOrderPosition(), String.valueOf(cnt));
         call.enqueue(new Callback<CheckoutAllGoods>() {
            @Override
            public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {
            }
            @Override
            public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {
            }});
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCategoryName, tvPrice, tvAmount, tvSumPrice;
        public ImageView ivPlus, ivMinus, ivDeleteFromCheckout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryName = (TextView)itemView.findViewById(R.id.tvCategoryName);
            tvPrice = (TextView)itemView.findViewById(R.id.tvPrice);
            tvAmount = (TextView)itemView.findViewById(R.id.tvAmount);
            tvSumPrice = (TextView)itemView.findViewById(R.id.tvSumPrice);

            ivPlus = (ImageView)itemView.findViewById(R.id.ivPlus);
            ivMinus = (ImageView)itemView.findViewById(R.id.ivMinus);
            ivDeleteFromCheckout = (ImageView)itemView.findViewById(R.id.ivDeleteFromCheckout);

        }
    }
}

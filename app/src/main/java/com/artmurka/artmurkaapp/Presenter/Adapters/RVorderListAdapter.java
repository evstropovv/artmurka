package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Order;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.GoodsListDescription;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.artmurka.artmurkaapp.Views.Fragments.OrderStatusFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVorderListAdapter extends RecyclerView.Adapter<RVorderListAdapter.ViewHolder> {
    private Context ctx;
    List<Order> orderList;
    Orders orders;

    public RVorderListAdapter(Context context) {
        this.ctx = context;
        orderList = new ArrayList<>();
    }

    public void setData(Orders orders) {
        this.orders = orders;

        this.orderList.clear();
        this.orderList.addAll(orders.getSuccess().getOrders());
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.card_order, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvOrderNumber.setText(orderList.get(position).getNom());

        String curentStatus = orderList.get(position).getStatus();
        holder.tvStatus.setText(orders.getSuccess().getOrderStatus().get(curentStatus));

        String curHide = orderList.get(position).getHide();
        holder.tvName.setText(orders.getSuccess().getOrderHide().get(curHide));

        holder.tvPrice.setText(orderList.get(position).getPayment().getTopay());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOrderNumber, tvStatus, tvName, tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            tvOrderNumber = (TextView) itemView.findViewById(R.id.tvOrderNumber);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderStatusFragment fr = new OrderStatusFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("order",orderList.get(getAdapterPosition()).getNom());
                    bundle.putString("message",Html.fromHtml(orderList.get(getAdapterPosition()).getRem()).toString());
                    fr.setArguments(bundle);
                    fr.show(((MainActivity) ctx).getFragmentManager(),"123");
                }
            });
        }
    }
}

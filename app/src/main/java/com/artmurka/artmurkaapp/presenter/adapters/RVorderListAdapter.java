package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artmurka.artmurkaapp.model.pojo.itemlist.orders.Order;
import com.artmurka.artmurkaapp.model.pojo.itemlist.orders.Orders;
import com.artmurka.artmurkaapp.other.Const;
import com.artmurka.artmurkaapp.other.PayLiq;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.MainActivity;
import com.artmurka.artmurkaapp.android.views.fragments.OrderStatusFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RVorderListAdapter extends RecyclerView.Adapter<RVorderListAdapter.ViewHolder> {
    private Context ctx;
    private List<Order> orderList;
    private Orders orders;

    public RVorderListAdapter(Context context) {
        this.ctx = context;
        orderList = new ArrayList<>();
    }

    public void setData(Orders orders) {
        this.orders = orders;
        this.orderList.clear();
        try {
            this.orderList.addAll(orders.getSuccess().getOrders());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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
        try{
            holder.tvOrderNumber.setText(orderList.get(position).getNom());
            String curentStatus = orderList.get(position).getStatus();
            holder.tvStatus.setText(orders.getSuccess().getOrderStatus().get(curentStatus));
            String curHide = orderList.get(position).getHide();
            holder.tvName.setText(orders.getSuccess().getOrderHide().get(curHide));
            holder.tvPrice.setText(orderList.get(position).getPayment().getTopay());
            holder.tvPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } catch (NullPointerException e){
            e.printStackTrace();
        }


        //if order status is new, and deliver
        if (orderList.get(position).getPayment().getId().equals("2") && Integer.parseInt(orderList.get(position).getStatus()) <= 1) {
            holder.btnPay.setVisibility(View.VISIBLE);
        } else {
            holder.btnPay.setVisibility(View.INVISIBLE);
        }


        holder.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Перед тим як сплачувати, будь ласка, зателефонуйте менеджеру для підтвердження наявності товару. Ви впевнені що хочете сплатити ?")
                        .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HashMap<String, String> parameters = new HashMap<String, String>();
                                parameters.put("order_id", orderList.get(position).getNom());
                                parameters.put("amount", orderList.get(position).getAmount());
                                new PayLiq(view.getContext(), parameters).start();
                            }
                        })
                        .setNeutralButton("Зателефонувати", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Uri call = Uri.parse("tel:" + Const.TEL_NUMBER);
                                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                                view.getContext().startActivity(surf);
                            }
                        })
                        .setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvOrderNumber, tvStatus, tvName, tvPrice;
        Button btnPay;

        public ViewHolder(View itemView) {
            super(itemView);
            tvOrderNumber = (TextView) itemView.findViewById(R.id.tvOrderNumber);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            btnPay = (Button) itemView.findViewById(R.id.btnPay);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderStatusFragment fr = new OrderStatusFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("order", orderList.get(getAdapterPosition()).getNom());
                    bundle.putString("message", Html.fromHtml(orderList.get(getAdapterPosition()).getRem()).toString());
                    fr.setArguments(bundle);
                    fr.show(((MainActivity) ctx).getFragmentManager(), "123");
                }
            });
        }
    }
}

package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.artmurka.artmurkaapp.data.model.interfacesmodel.ICheckoutRequest
import com.artmurka.artmurkaapp.data.model.modules.CheckoutRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.OrderDesc
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICheckoutFragment

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RVcheckoutAdapter(internal var context: Context, internal var fragment: ICheckoutFragment) : RecyclerView.Adapter<RVcheckoutAdapter.ViewHolder>() {
    internal var orderList: MutableList<OrderDesc>

    init {
        orderList = ArrayList()
    }

    fun setData(orderList: List<OrderDesc>?) {
        if (orderList != null && orderList.size > 0) {
            this.orderList.clear()
            this.orderList.addAll(orderList)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_checkout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryName.text = orderList[position].name
        holder.tvPrice.text = orderList[position].price?.priceRaw!!.toString() + " грн/шт"
        holder.tvAmount.text = orderList[position].cnt
        holder.tvSumPrice.text = orderList[position].sum?.sumRaw!!.toString() + " грн"

        holder.ivPlus.setOnClickListener {
            //при клике увеличиваем количество на 1
            var cnt = Integer.parseInt(orderList[position].cnt)
            refreshItemCnt(++cnt, position)
            refreshItemRequest(cnt, position)
        }
        holder.ivMinus.setOnClickListener {
            var cnt = Integer.parseInt(orderList[position].cnt)
            --cnt
            if (cnt < 1) cnt = 1
            refreshItemCnt(cnt, position)
            refreshItemRequest(cnt, position)
        }
        holder.ivDeleteFromCheckout.setOnClickListener {
            refreshItemRequest(0, position)  // 0  = delete
            orderList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, orderList.size)
            refreshItemCnt()
        }


    }

    private fun refreshItemCnt(cnt: Int, position: Int) {
        var cnt = cnt
        if (cnt < 1) cnt = 1
        val price = orderList[position].price?.priceRaw
        orderList[position].cnt = cnt.toString()
        orderList[position].sum?.sumRaw = java.lang.Float.parseFloat((cnt * price!!).toString() + "")
        notifyItemChanged(position)

        var sumPrice = 0
        for (i in orderList.indices) {
            sumPrice = sumPrice + orderList[i].sum?.sumRaw!!.toInt()
        }

        fragment.refreshSumPrice(sumPrice.toString() + " грн")
    }

    private fun refreshItemCnt() {
        var sumPrice = 0
        for (i in orderList.indices) {
            sumPrice = sumPrice + orderList[i].sum?.sumRaw!!.toInt()
        }
        fragment.refreshSumPrice(sumPrice.toString() + " грн")
    }

    private fun refreshItemRequest(cnt: Int, position: Int) {
        //TODO don't remove it1
        //        ICheckoutRequest request = new CheckoutRequest();
        //        Call<CheckoutAllGoods> call = request.recountCheckoutData(orderList.get(position).getOrderPosition(), String.valueOf(cnt));
        //         call.enqueue(new Callback<CheckoutAllGoods>() {
        //            @Override
        //            public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {
        //            }
        //            @Override
        //            public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {
        //            }});
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView
        var tvPrice: TextView
        var tvAmount: TextView
        var tvSumPrice: TextView
        var ivPlus: ImageView
        var ivMinus: ImageView
        var ivDeleteFromCheckout: ImageView

        init {
            tvCategoryName = itemView.findViewById<View>(R.id.tvCategoryName) as TextView
            tvPrice = itemView.findViewById<View>(R.id.tvPrice) as TextView
            tvAmount = itemView.findViewById<View>(R.id.tvAmount) as TextView
            tvSumPrice = itemView.findViewById<View>(R.id.tvSumPrice) as TextView

            ivPlus = itemView.findViewById<View>(R.id.ivPlus) as ImageView
            ivMinus = itemView.findViewById<View>(R.id.ivMinus) as ImageView
            ivDeleteFromCheckout = itemView.findViewById<View>(R.id.ivDeleteFromCheckout) as ImageView

        }
    }
}

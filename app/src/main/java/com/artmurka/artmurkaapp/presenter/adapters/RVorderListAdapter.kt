package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Order
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.other.PayLiq
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.fragments.OrderStatusFragment
import java.util.ArrayList
import java.util.HashMap


class RVorderListAdapter(private val ctx: Context) : RecyclerView.Adapter<RVorderListAdapter.ViewHolder>() {
    private val orderList: MutableList<Order>
    private var orders: Orders? = null

    init {
        orderList = ArrayList()
    }

    fun setData(orders: Orders) {
        this.orders = orders
        this.orderList.clear()
        try {
            this.orderList.addAll(orders?.success?.orders!!)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.card_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.tvOrderNumber.text = orderList[position].nom
            val curentStatus = orderList[position].status
            holder.tvStatus.text = orders?.success?.orderStatus!![curentStatus]
            val curHide = orderList[position].hide
            holder.tvName.text = orders?.success?.orderHide!![curHide]
            holder.tvPrice.text = orderList[position].payment?.topay
            holder.tvPrice.setOnClickListener { }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        //if order status is new, and deliver
        if (orderList[position].payment?.id == "2" && Integer.parseInt(orderList[position].status) <= 1) {
            holder.btnPay.visibility = View.VISIBLE
        } else {
            holder.btnPay.visibility = View.INVISIBLE
        }

        holder.btnPay.setOnClickListener { view ->
            val builder = AlertDialog.Builder(view.context)
            builder.setMessage(ctx.resources.getString(R.string.fragment_order_dialog))
                    .setPositiveButton(ctx.resources.getString(R.string.yes)) { dialog, id ->
                        val parameters = HashMap<String, String>()
                        parameters["order_id"] = orderList[position].nom!!
                        parameters["amount"] = orderList[position].amount!!
                        PayLiq(view.context, parameters).start()
                    }
                    .setNeutralButton(ctx.resources.getString(R.string.call)) { dialog, id ->
                        val call = Uri.parse("tel:" + Const.TEL_NUMBER)
                        val surf = Intent(Intent.ACTION_DIAL, call)
                        view.context.startActivity(surf)
                    }
                    .setNegativeButton(ctx.resources.getString(R.string.no)) { dialog, id ->
                        // User cancelled the dialog
                    }.show()
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvOrderNumber: TextView
        var tvStatus: TextView
        var tvName: TextView
        var tvPrice: TextView
        internal var btnPay: Button

        init {
            tvOrderNumber = itemView.findViewById<View>(R.id.tvOrderNumber) as TextView
            tvStatus = itemView.findViewById<View>(R.id.tvStatus) as TextView
            tvName = itemView.findViewById<View>(R.id.tvName) as TextView
            tvPrice = itemView.findViewById<View>(R.id.tvPrice) as TextView
            btnPay = itemView.findViewById<View>(R.id.btnPay) as Button


            itemView.setOnClickListener {
                val fr = OrderStatusFragment()
                val bundle = Bundle()
                bundle.putString("order", orderList[adapterPosition].nom)
                bundle.putString("message", Html.fromHtml(orderList[adapterPosition].rem).toString())
                fr.arguments = bundle
                fr.show((ctx as MainActivity).fragmentManager, "123")
            }
        }
    }
}

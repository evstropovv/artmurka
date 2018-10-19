package com.artmurka.artmurkaapp.presenter.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import java.util.ArrayList

class RVitemListGridAdapter(internal var ctx: Context) : RecyclerView.Adapter<RVitemListGridAdapter.ViewHolder>() {
    private val successList: ArrayList<GoodsProperties>

    init {
        successList = ArrayList()
    }

    fun setData(list: ArrayList<GoodsProperties>?) {
        if (list != null && list.size > 0) {
            this.successList.clear()
            this.successList.addAll(list)
            notifyDataSetChanged()
            Log.d("Log.d", "success list :" + Gson().toJson(successList))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(ctx).load(successList[position].entryPhoto?.defPhoto?.photo).into(holder.ivItemPhoto)
    }

    override fun getItemCount(): Int {
        return successList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemName: TextView
        var ivItemPhoto: ImageView


        init {
            tvItemName = itemView.findViewById<View>(R.id.item_name) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView

            itemView.setOnClickListener {
                //to aboutGoods activity
                val intent = Intent(itemView.context, SelectedGoodActivity::class.java)
                val id = successList[adapterPosition].entryId
                intent.putExtra("id", id)
                intent.putExtra("inWish", successList[adapterPosition].entryIsInWishlist)
                intent.putExtra("inBasket", successList[adapterPosition].entryIsInBasket)

                val activity = ctx as Activity
                activity.startActivity(intent)
                //   activity.overridePendingTransition(R.drawable.fadein, R.drawable.fadeout);
            }
        }
    }
}
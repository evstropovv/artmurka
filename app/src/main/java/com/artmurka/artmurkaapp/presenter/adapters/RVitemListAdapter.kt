package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.PopupMenu
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

class RVitemListAdapter(internal var ctx: Context) : RecyclerView.Adapter<RVitemListAdapter.ViewHolder>() {
    private val successList: ArrayList<GoodsProperties>

    var clickListener : RVitemListAdapter.OnItemClickListener? = null


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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = successList[position].entryTitle
        holder.tvItemCost.text = successList[position].entryPrice?.price
        Picasso.with(ctx).load(successList[position].entryPhoto?.defPhoto?.thumb).into(holder.ivItemPhoto)
        holder.ivMenu.setOnClickListener { v: View ->
            val popupMenu = PopupMenu(ctx, holder.ivMenu)
            popupMenu.inflate(R.menu.item_menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.to_card ->
                        //проверяем, есть ли такой товар в корзине
                        if (successList[position].entryIsInBasket == 0) {
                            clickListener?.toBasket(successList.get(position).entryId!!)
                        }
                    R.id.wish_wad -> {
                        clickListener?.toWishList(successList.get(position).entryId!!)
                    }

                    else -> {
                    }
                }
                false
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return successList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvItemName: TextView
        var tvItemCost: TextView
        var ivItemPhoto: ImageView
        var ivMenu: ImageView

        init {
            tvItemName = itemView.findViewById<View>(R.id.item_name) as TextView
            tvItemCost = itemView.findViewById<View>(R.id.item_cost) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            ivMenu = itemView.findViewById<View>(R.id.item_iv) as ImageView
            itemView.setOnClickListener {
                //to aboutGoods activity
                val intent = Intent(itemView.context, SelectedGoodActivity::class.java)
                val id = successList[adapterPosition].entryId
                intent.putExtra("id", id)
                intent.putExtra("inWish", successList[adapterPosition].entryIsInWishlist)
                intent.putExtra("inBasket", successList[adapterPosition].entryIsInBasket)
                itemView.context.startActivity(intent)
            }
        }
    }

    public interface OnItemClickListener {
        fun toWishList(goodsId: String)
        fun toBasket(goodId: String)
    }

}

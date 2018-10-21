package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RVitemListAdapterAboutGoods(internal var ctx: Context) : RecyclerView.Adapter<RVitemListAdapterAboutGoods.ViewHolder>() {

    var itemClickListener : RVitemListAdapterAboutGoods.OnItemClickListener ? = null

    public interface OnItemClickListener {
        fun toWishList(goodsId: String)
        fun toBasket(goodId: String)
    }

    private val successList: ArrayList<GoodsProperties>

    init {
        successList = ArrayList()
    }

    fun setData(list: ArrayList<GoodsProperties>?) {
        if (list != null && list.size > 0) {
            this.successList.clear()
            this.successList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_about_goods, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = successList[position].entryTitle
        Picasso.with(ctx).load(successList[position].entryPhoto?.defPhoto?.thumb).into(holder.ivItemPhoto)
        holder.ivMenu.setOnClickListener {
            val popupMenu = PopupMenu(ctx, holder.ivMenu)
            popupMenu.inflate(R.menu.item_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.to_card -> {
                        itemClickListener?.toBasket(successList.get(position).entryId!!)
                    }
                    R.id.wish_wad ->{
                        itemClickListener?.toWishList(successList.get(position).entryId!!)
                        Toast.makeText(ctx, successList[position].entryTitle + " додано до бажань", Toast.LENGTH_SHORT).show()

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
        var ivItemPhoto: ImageView
        var ivMenu: ImageView

        init {
            tvItemName = itemView.findViewById<View>(R.id.item_name) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            ivMenu = itemView.findViewById<View>(R.id.item_iv) as ImageView
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SelectedGoodActivity::class.java)
                val id = successList[adapterPosition].entryId
                intent.putExtra("id", id)
                itemView.context.startActivity(intent)
            }
        }
    }
}

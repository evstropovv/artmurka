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

import com.artmurka.artmurkaapp.data.model.interfacesmodel.ICheckoutRequest
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList
import com.artmurka.artmurkaapp.data.model.modules.CheckoutRequest
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Item

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.R

import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVbasketAdapter(private val ctx: Context, internal var fragment: IBasketFragment) : RecyclerView.Adapter<RVbasketAdapter.ViewHolder>() {

    public interface OnItemClickListener {
        fun onRefreshItem(cnt: String, id: String)
        fun addToWishList(id: String)
    }

    var onItemClickListener: OnItemClickListener? = null

    internal var basketItemList: MutableList<Item>? = null

    init {
        basketItemList = ArrayList()
    }

    fun setData(list: List<Item>) {
        if (basketItemList != null && list.size > 0) {
            this.basketItemList!!.clear()
            this.basketItemList!!.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.basket_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.goodName.text = basketItemList!![position].entryTitle
        holder.goodDescription.text = basketItemList!![position].cnt + " шт. " + basketItemList!![position].entryPrice.price
        holder.goodPrice.text = basketItemList!![position].summ.summRaw + " грн."
        Picasso.with(ctx).load(basketItemList!![position].entryPhoto.thumb).into(holder.itemPhoto)
        Log.d("Log.d", Gson().toJson(basketItemList!![position]))

        holder.basketMenu.setOnClickListener {
            val popupMenu = PopupMenu(ctx, holder.basketMenu)
            popupMenu.inflate(R.menu.basket_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {

                    R.id.delete_from_basket -> {
                        //delete from basket
                        onItemClickListener?.onRefreshItem("0", basketItemList?.get(position)?.getId()!!) // 0  = delete
                        basketItemList!!.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, basketItemList!!.size)
                        changePrice()
                    }
                    R.id.wish_wad ->{
                        onItemClickListener?.addToWishList(basketItemList?.get(position)?.entryId!!)

                        Toast.makeText(ctx, basketItemList!![position].entryTitle + " додано до бажань", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                    }
                }
                false
            }

            popupMenu.show()
        }

    }

    private fun changePrice() {
        var sum = 0f
        if (basketItemList!!.size > 0) {
            for (i in basketItemList!!.indices) {
                sum = sum + java.lang.Float.parseFloat(basketItemList!![i].entryPrice.priceRaw)
            }
        }
        fragment.showPrice(sum.toString() + "")
    }


    override fun getItemCount(): Int {
        return basketItemList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var goodName: TextView
        var goodDescription: TextView
        var goodPrice: TextView
        var itemPhoto: ImageView
        var basketMenu: ImageView


        init {
            goodName = itemView.findViewById<View>(R.id.good_name) as TextView
            goodDescription = itemView.findViewById<View>(R.id.good_description) as TextView
            goodPrice = itemView.findViewById<View>(R.id.price) as TextView
            itemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            basketMenu = itemView.findViewById<View>(R.id.basket_menu) as ImageView
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SelectedGoodActivity::class.java)
                val id = basketItemList!![adapterPosition].entryId
                intent.putExtra("id", id)
                intent.putExtra("inWish", basketItemList!![adapterPosition].entryIsInWishlist)
                itemView.context.startActivity(intent)
            }
        }
    }
}

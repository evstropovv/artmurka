package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.squareup.picasso.Picasso

import java.util.ArrayList

class RVitemListAdapterList(internal var ctx: Context) : RecyclerView.Adapter<RVitemListAdapterList.ViewHolder>() {
    private val successList: ArrayList<GoodsProperties>

    var clickListener: RVitemListAdapter.OnItemClickListener? = null

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = successList[position].entryTitle
        holder.tvPrice.text = successList[position].entryPrice?.priceRaw + " грн."
        Picasso.with(ctx).load(successList[position].entryPhoto?.defPhoto?.thumb).into(holder.ivItemPhoto)
        holder.ivToBasket.setImageResource(R.drawable.basketfill_small_grey)

        holder.ivToBasket.setOnClickListener {

            holder.ivToBasket.startAnimation(com.artmurka.artmurkaapp.other.RotateAnimation().getAnimation())
            if (successList[position].entryIsInBasket == 0) {
                //в корзину
                clickListener?.toBasket(successList.get(position).entryId!!)
            }
        }
        var isHeartOrange = false
        isHeartOrange = when (successList[position].entryIsInWishlist) {
            1 -> {
                holder.ivToWish.setImageResource(R.drawable.heart_small_orange)
                true
            }
            else -> {
                holder.ivToWish.setImageResource(R.drawable.heart_small)
                false
            }
        }
        holder.ivToWish.setOnClickListener {
            Toast.makeText(ctx, successList[position].entryTitle + " додано до бажань", Toast.LENGTH_SHORT).show()
            //animation
            holder.ivToWish.startAnimation(com.artmurka.artmurkaapp.other.RotateAnimation().getAnimation())

            clickListener?.toWishList(successList.get(position).entryId!!)

            isHeartOrange = !isHeartOrange

            when(isHeartOrange){
                true ->  holder.ivToWish.setImageResource(R.drawable.heart_small_orange)
                false -> holder.ivToWish.setImageResource(R.drawable.heart_small)
            }
        }
    }

    override fun getItemCount(): Int {
        return successList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItemName: TextView
        var tvPrice: TextView
        var ivItemPhoto: ImageView
        var ivToBasket: ImageView
        var ivToWish: ImageView

        init {
            tvItemName = itemView.findViewById<View>(R.id.item_name) as TextView
            tvPrice = itemView.findViewById<View>(R.id.tvPrice) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            ivToBasket = itemView.findViewById<View>(R.id.ivToBasket) as ImageView
            ivToWish = itemView.findViewById<View>(R.id.ivToWish) as ImageView

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
}

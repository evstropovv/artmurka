package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class RVwishListAdapter(private val ctx: Context) : RecyclerView.Adapter<RVwishListAdapter.ViewHolder>() {

    var clickListener: RVwishListAdapter.OnItemClickListener? = null

    var clickedItems = HashSet<Int>()

    private var wishList: MutableList<GoodsListDescription>? = mutableListOf<GoodsListDescription>()

    fun setData(list: MutableList<GoodsListDescription>) {
        var result = DiffUtil.calculateDiff(WishListDiffCallback(this.wishList!!, list))
        wishList?.clear()
        wishList?.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.card_wishlist, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.with(ctx).load(wishList!![position].entryPhoto?.defPhoto?.thumb).into(holder.ivItemPhoto)
        holder.tvCategoryName.text = wishList!![position].entryTitle
        holder.tvPrice.text = wishList!![position].entryPrice?.priceRaw.toString() + ctx.resources.getString(R.string.money)
        holder.ivToBasket.setOnClickListener {

            holder.ivToBasket.startAnimation(com.artmurka.artmurkaapp.other.RotateAnimation().getAnimation())

            if (clickedItems.contains(position)) {
                clickedItems.remove(position)
                holder.ivToBasket.setImageDrawable(ctx.resources.getDrawable(R.drawable.heart_small_orange))
            } else {
                clickedItems.add(position)
                holder.ivToBasket.setImageDrawable(ctx.resources.getDrawable(R.drawable.heart_small))
            }

            //delay добавлен что б у пользователя было время одуматься, и нажать еще раз кнопку что бы отменить удаление
            Completable.complete()
                    .delay(300, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        if (clickedItems.contains(position)) {
                            clickedItems.remove(position)
                            clickListener?.deleteFromWishOnline(wishList?.get(position)?.entryId!!)
                            removeInList(position)
                        }
                    }.doOnError { t ->
                        Log.e("Log.e", t.message)
                    }
                    .subscribe()

        }
    }


    private fun removeInList(position: Int) {
        wishList?.removeAt(position)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, wishList?.size!!);
    }

    override fun getItemCount(): Int {
        return wishList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView
        var tvPrice: TextView
        var ivItemPhoto: RoundedImageView
        var ivToBasket: ImageView


        init {
            tvCategoryName = itemView.findViewById<View>(R.id.tvCategoryName) as TextView
            tvPrice = itemView.findViewById<View>(R.id.tvPrice) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as RoundedImageView
            ivToBasket = itemView.findViewById<View>(R.id.ivToBasket) as ImageView
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SelectedGoodActivity::class.java)
                val id = wishList!![adapterPosition].entryId
                intent.putExtra("id", id)
                intent.putExtra("inWish", wishList!![adapterPosition].entryIsInWishlist)
                intent.putExtra("inBasket", wishList!![adapterPosition].entryIsInBasket)
                itemView.context.startActivity(intent)
            }
        }
    }

    public interface OnItemClickListener {
        fun deleteFromWishOnline(goodsId: String)
        fun toBasket(goodId: String)
    }
}

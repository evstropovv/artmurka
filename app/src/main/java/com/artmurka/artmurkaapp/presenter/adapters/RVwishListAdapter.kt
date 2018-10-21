package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.artmurka.artmurkaapp.data.model.databases.Preferences.init
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.squareup.picasso.Picasso

import java.util.ArrayList


class RVwishListAdapter(private val ctx: Context) : RecyclerView.Adapter<RVwishListAdapter.ViewHolder>() {

    var clickListener: RVwishListAdapter.OnItemClickListener? = null

    private var wishList: MutableList<GoodsListDescription>? = mutableListOf<GoodsListDescription>()

    fun setData(list: MutableList<GoodsListDescription>) {
        var result = DiffUtil.calculateDiff(WishListDiffCallback(this.wishList!!, list))
        wishList?.clear()
        wishList?.addAll(list)
        result.dispatchUpdatesTo(this)
//        if (wishList != null && list.size > 0) {
//            this.wishList.clear()
//            this.wishList.addAll(list)
//            notifyDataSetChanged()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.card_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.with(ctx).load(wishList!![position].entryPhoto?.defPhoto?.thumb).into(holder.ivItemPhoto)
        holder.tvCategoryName.text = wishList!![position].entryTitle
        holder.tvPrice.text = wishList!![position].entryPrice?.priceRaw.toString() + " грн."
        holder.ivToBasket.setOnClickListener {
            if (wishList!![position].entryIsInBasket == 0L) {
                clickListener?.toBasket(wishList?.get(position)?.entryId!!)
                //в корзину
                //TODO don't remove it
                // IBasket basket = new BasketRequest();
                //                    Observable<BasketItems> observable = basket.toBasket(wishList.get(position).getEntryId());
                //
                //                    observable.subscribe(new Observer<BasketItems>() {
                //                        @Override
                //                        public void onSubscribe(Disposable d) {
                //                        }
                //
                //                        @Override
                //                        public void onNext(BasketItems value) {
                //                             Toast.makeText(ctx, wishList.get(position).getEntryTitle() + " успішно додано до кошика", Toast.LENGTH_SHORT).show();
                //                            String goodsId = wishList.get(position).getEntryId();
                //                            deleteFromWishOnline(goodsId);
                //                            wishList.remove(position);
                //                            notifyItemRemoved(position);
                //                            notifyItemRangeChanged(position, wishList.size());
                //                        }
                //
                //                        @Override
                //                        public void onError(Throwable e) {
                //
                //                        }
                //
                //                        @Override
                //                        public void onComplete() {
                //
                //                        }
                //                    });
            }
        }
        holder.ivDeleteFromWish.setOnClickListener {
            clickListener?.deleteFromWishOnline(wishList!![position].entryId!!)

//            wishList!!.removeAt(position)
//            notifyItemRemoved(position)
//            notifyItemRangeChanged(position, wishList?.size!!)
        }
    }


    fun deleteFromWishOnline(goodsId: String) {
        //delete from wishlist online
        //  clickListener?.deleteFromWishOnline(goodsId)

        //TODO don't remove it !
        //        IWishList iWishList = new WishListRequest();
        //        Call<WishList> obs = iWishList.toWishList(goodsId); //здесь по запросу toWishList - или удаляется если она есть, или добавляется если позиции в списке нет
        //        obs.enqueue(new Callback<WishList>() {
        //            @Override
        //            public void onResponse(Call<WishList> call, Response<WishList> response) {
        //            }
        //            @Override
        //            public void onFailure(Call<WishList> call, Throwable t) {}
        //        });
    }


    override fun getItemCount(): Int {
        return wishList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategoryName: TextView
        var tvPrice: TextView
        var ivItemPhoto: ImageView
        var ivToBasket: ImageView
        var ivDeleteFromWish: ImageView


        init {
            tvCategoryName = itemView.findViewById<View>(R.id.tvCategoryName) as TextView
            tvPrice = itemView.findViewById<View>(R.id.tvPrice) as TextView
            ivItemPhoto = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            ivToBasket = itemView.findViewById<View>(R.id.ivToBasket) as ImageView
            ivDeleteFromWish = itemView.findViewById<View>(R.id.ivDeleteFromWish) as ImageView
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

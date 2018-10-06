package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IBasket
import com.artmurka.artmurkaapp.data.model.interfacesmodel.ICheckoutRequest
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.artmurka.artmurkaapp.data.model.modules.CheckoutRequest
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.squareup.picasso.Picasso

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVitemListAdapterList(internal var ctx: Context) : RecyclerView.Adapter<RVitemListAdapterList.ViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = successList[position].entryTitle
        holder.tvPrice.text = successList[position].entryPrice.priceRaw + " грн."
        Picasso.with(ctx).load(successList[position].entryPhoto.defPhoto.thumb).into(holder.ivItemPhoto)
        holder.ivToBasket.setImageResource(R.drawable.basketfill_small_grey)

        holder.ivToBasket.setOnClickListener {
            val anim = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.interpolator = LinearInterpolator()
            anim.repeatCount = Animation.RELATIVE_TO_PARENT
            anim.duration = 200
            holder.ivToBasket.startAnimation(anim)

            if (successList[position].entryIsInBasket == 0) {
                //в корзину
                //TODO don't remove it1

                //                    IBasket basket = new BasketRequest();
                //                    Observable<BasketItems> observable = basket.toBasket(successList.get(position).getEntryId());
                //
                //                    observable.subscribe(new Observer<BasketItems>() {
                //                        @Override
                //                        public void onSubscribe(Disposable d) {
                //                        }
                //
                //                        @Override
                //                        public void onNext(BasketItems value) {
                //                            successList.get(position).setEntryIsInBasket(1);
                //                            Toast.makeText(ctx, successList.get(position).getEntryTitle() + " успішно додано до кошика.", Toast.LENGTH_SHORT).show();
                //                        }
                //
                //                        @Override
                //                        public void onError(Throwable e) {
                //                        }
                //
                //                        @Override
                //                        public void onComplete() {
                //                        }
                //                    });
            } else {
                //TODO don't remove it1
                //                    ICheckoutRequest request = new CheckoutRequest();
                //                    Call<CheckoutAllGoods> call = request.recountCheckoutData(successList.get(position).getEntryId(), "0");
                //                    call.enqueue(new Callback<CheckoutAllGoods>() {
                //                        @Override
                //                        public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {
                //                            successList.get(position).setEntryIsInBasket(0);
                //                            Toast.makeText(view.getContext(), "Видалено з корзини", Toast.LENGTH_LONG).show();
                //                        }
                //
                //                        @Override
                //                        public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {
                //                        }
                //                    });

            }
        }

        holder.ivToWish.setImageResource(
                if (successList[position].entryIsInWishlist == 1)
                    R.drawable.heart_small_orange
                else
                    R.drawable.heart_small)

        holder.ivToWish.setOnClickListener {
            Toast.makeText(ctx, successList[position].entryTitle + " додано до бажань", Toast.LENGTH_SHORT).show()
            //animation
            val anim = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.interpolator = LinearInterpolator()
            anim.repeatCount = Animation.RELATIVE_TO_PARENT
            anim.duration = 200
            holder.ivToWish.startAnimation(anim)

            //TODO don't remove it1
            //                IWishList iWishList = new WishListRequest();
            //                Call<WishList> obs = iWishList.toWishList(successList.get(position).getEntryId());
            //                obs.enqueue(new Callback<WishList>() {
            //                    @Override
            //                    public void onResponse(Call<WishList> call, Response<WishList> response) {
            //                        if (successList.get(position).getEntryIsInWishlist() == 1) {
            //                            successList.get(position).setEntryIsInWishlist(0);
            //                            holder.ivToWish.setImageResource(R.drawable.heart_small);
            //                        } else {
            //                            successList.get(position).setEntryIsInWishlist(1);
            //                            holder.ivToWish.setImageResource(R.drawable.heart_small_orange);
            //
            //                        }
            //                    }
            //
            //                    @Override
            //                    public void onFailure(Call<WishList> call, Throwable t) {
            //                        Log.d("Log.d", "toWishList " + t.getMessage());
            //                    }
            //                });
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

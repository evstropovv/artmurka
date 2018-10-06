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
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IBasket
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVitemListAdapter(internal var ctx: Context) : RecyclerView.Adapter<RVitemListAdapter.ViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = successList[position].entryTitle
        Picasso.with(ctx).load(successList[position].entryPhoto.defPhoto.thumb).into(holder.ivItemPhoto)
        holder.ivMenu.setOnClickListener { v: View ->
            val popupMenu = PopupMenu(ctx, holder.ivMenu)
            popupMenu.inflate(R.menu.item_menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.to_card ->
                        //проверяем, есть ли такой товар в корзине
                        if (successList[position].entryIsInBasket == 0) {
                            //в корзину
                            //TODO don't remote it
                            //                            IBasket basket = new BasketRequest();
                            //                            Observable<BasketItems> observable = basket.toBasket(successList.get(position).getEntryId());
                            //
                            //                            observable.subscribe(new Observer<BasketItems>() {
                            //                                @Override
                            //                                public void onSubscribe(Disposable d) {}
                            //
                            //                                @Override
                            //                                public void onNext(BasketItems value) {
                            //                                         Toast.makeText(ctx, successList.get(position).getEntryTitle() + " успішно додано до кошика", Toast.LENGTH_SHORT).show();
                            //                                }
                            //                                @Override
                            //                                public void onError(Throwable e) {
                            //                                  ;
                            //                                }
                            //                                @Override
                            //                                public void onComplete() {
                            //                                }
                            //                            });
                        }
                    R.id.wish_wad -> {
                    }

                    else -> {
                    }
                }//в список пожеланий
                //TODO don't remove it1
                //                        IWishList iWishList = new WishListRequest();
                //                        Call<WishList> obs = iWishList.toWishList(successList.get(position).getEntryId());
                //                        obs.enqueue(new Callback<WishList>() {
                //                            @Override
                //                            public void onResponse(Call<WishList> call, Response<WishList> response) {
                //                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано до бажань", Toast.LENGTH_SHORT).show();
                //                            }
                //                            @Override
                //                            public void onFailure(Call<WishList> call, Throwable t) {
                //
                //                            }
                //                        });
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

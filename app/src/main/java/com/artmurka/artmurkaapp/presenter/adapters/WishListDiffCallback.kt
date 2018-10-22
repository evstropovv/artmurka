package com.artmurka.artmurkaapp.presenter.adapters

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import android.util.Log
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription

class WishListDiffCallback
constructor(val oldList: MutableList<GoodsListDescription>,
            val newList: MutableList<GoodsListDescription>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var isSame = oldList[oldItemPosition].entryId!! == newList[newItemPosition].entryId!!
       // Log.d("Log.d", "areItemTheSame ${oldList[oldItemPosition].entryId!!} and ${newList[newItemPosition].entryId!!} = $isSame")
       return isSame
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var isSame = oldList[oldItemPosition].entryId?.equals(newList[newItemPosition].entryId)!!
      //  Log.d("Log.d", "areContentsTheSame ${oldList[oldItemPosition].entryId!!} and ${newList[newItemPosition].entryId!!} = $isSame")
        return isSame
      }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
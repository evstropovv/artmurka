package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.other.FragmentType
import com.squareup.picasso.Picasso

import java.util.ArrayList


class RVcategoryAdapter(private val ctx: Context) : RecyclerView.Adapter<RVcategoryAdapter.ViewHolder>() {
    private val successList: MutableList<Success>

    init {
        successList = ArrayList()
    }

    fun setData(list: List<Success>?) {
        if (list != null && list.size > 0) {
            this.successList.clear()
            this.successList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryName.text = successList[position].catName
        try {
            Picasso.with(ctx).load(successList[position].catImg).into(holder.ivCategoryImage)
        } catch (e: IllegalArgumentException) {
            e.message
        }

    }

    override fun getItemCount(): Int {
        return successList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvCategoryName: TextView
        var ivCategoryImage: ImageView

        init {

            tvCategoryName = itemView.findViewById<View>(R.id.tvCategoryName) as TextView
            ivCategoryImage = itemView.findViewById<View>(R.id.ivItemPhoto) as ImageView
            itemView.setOnClickListener { v ->
                val activity = v.context as MainActivity
                if (successList[adapterPosition].childs!!.size > 0) {
                    //если есть под-категории - открывает фрагмент с подгатегориями
                    activity.changeFragment(FragmentType.CATEGORY_CHILDS_FRAGMENT,
                            successList[adapterPosition].catUrl,
                            successList[adapterPosition].childs, successList[adapterPosition].catName)
                } else {

                    activity.changeFragment(FragmentType.ITEM_LIST_FRAGMENT, successList[adapterPosition].catUrl, null, null)

                }
            }
        }
    }
}

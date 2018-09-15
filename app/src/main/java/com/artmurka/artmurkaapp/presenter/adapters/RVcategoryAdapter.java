package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;
import com.artmurka.artmurkaapp.other.Const;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RVcategoryAdapter extends RecyclerView.Adapter<RVcategoryAdapter.ViewHolder> {
    private List<Success> successList;
    private Context ctx;

    public RVcategoryAdapter(Context context) {
        this.ctx = context;
        successList = new ArrayList<>();
    }

    public void setData(List<Success> list) {
        if (list != null && list.size() > 0) {
            this.successList.clear();
            this.successList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);

        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(successList.get(position).getCatName());
        try {
            Picasso.with(ctx).load(successList.get(position).getCatImg()).into(holder.ivCategoryImage);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategoryName;
        public ImageView ivCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            ivCategoryImage = (ImageView) itemView.findViewById(R.id.ivItemPhoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) v.getContext();
                    if (successList.get(getAdapterPosition()).getChilds().size() > 0) {
                        //если есть под-категории - открывает фрагмент с подгатегориями
                        activity.changeFragment(Const.CATEGORY_CHILDS_FRAGMENT,
                                successList.get(getAdapterPosition()).getCatUrl(),
                                successList.get(getAdapterPosition()).getChilds(), successList.get(getAdapterPosition()).getCatName());
                    } else {

                        activity.changeFragment(Const.ITEM_LIST_FRAGMENT, successList.get(getAdapterPosition()).getCatUrl(), null, null);

                    }
                }
            });
        }
    }
}

package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.GoodsListDescription;
import com.artmurka.artmurkaapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class RVwishListAdapter  extends RecyclerView.Adapter<RVwishListAdapter.ViewHolder>{
        private Context ctx;
        List<GoodsListDescription> wishList;
        public RVwishListAdapter(Context context) {
            this.ctx = context;
            wishList = new ArrayList<>();
        }

        public void setData(List<GoodsListDescription> list) {
            if (wishList != null && list.size() > 0) {
                this.wishList.clear();
                this.wishList.addAll(list);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(ctx).inflate(R.layout.card_wishlist, parent, false);
            final ViewHolder vh = new ViewHolder(view);
            return vh;
        }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("Log.d", wishList.get(position).getEntryPhoto().getDefPhoto().getPhoto() +" " +wishList.get(position).getEntryPhoto().getDefPhoto().getThumb());
        Picasso.with(ctx).load(wishList.get(position).getEntryPhoto().getDefPhoto().getPhoto()).into(holder.ivItemPhoto);
        holder.tvCategoryName.setText(wishList.get(position).getEntryTitle());
        holder.tvPrice.setText(wishList.get(position).getEntryPrice().getPrice());
        holder.ivToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.ivDeleteFromWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

        @Override
        public int getItemCount() {
            return wishList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvCategoryName, tvPrice;
            public ImageView ivItemPhoto, ivToBasket, ivDeleteFromWish;


            public ViewHolder(View itemView) {
                super(itemView);
                tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
                tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
                ivItemPhoto = (ImageView)itemView.findViewById(R.id.ivItemPhoto);
                ivToBasket = (ImageView)itemView.findViewById(R.id.ivToBasket);
                ivDeleteFromWish = (ImageView)itemView.findViewById(R.id.ivDeleteFromWish);
            }
        }
}

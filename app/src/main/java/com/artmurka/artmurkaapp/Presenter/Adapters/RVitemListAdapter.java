package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVitemListAdapter extends RecyclerView.Adapter<RVitemListAdapter.ViewHolder> {
    private ArrayList<GoodsProperties> successList;
    Context ctx;

    public RVitemListAdapter(Context context){
        this.ctx = context;
        successList = new ArrayList<>();
    }

    public void setData(ArrayList<GoodsProperties> list){
        if (list!=null && list.size()>0){
            this.successList.clear();
            this.successList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvItemName.setText(successList.get(position).getEntryTitle());
        Picasso.with(ctx).load(successList.get(position).getEntryPhoto().getDefPhoto().getThumb()).into(holder.ivItemPhoto);
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ctx, holder.ivMenu);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.to_card:
                                //в корзину
                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано до кошика", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.wish_wad:
                                //в список пожеланий
                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано до бажань", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.widget_add:
                                //в список сравнений
                                Toast.makeText(ctx, successList.get(position).getEntryTitle() + " додано в порівняння", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public ImageView ivItemPhoto;
        public ImageView ivMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView)itemView.findViewById(R.id.item_name);
            ivItemPhoto = (ImageView)itemView.findViewById(R.id.item_ph);
            ivMenu = (ImageView)itemView.findViewById(R.id.item_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),successList.get(getAdapterPosition()) +"\n", Toast.LENGTH_SHORT).show();
//                    MainActivity activity = (MainActivity)v.getContext();
//                    activity.changeFragment(successList.get(getAdapterPosition()).getCatUrl());
                }
            });
        }
    }
}

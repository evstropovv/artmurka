package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvItemName.setText(successList.get(position).getEntryTitle());
        Picasso.with(ctx).load(successList.get(position).getEntryPhoto().getDefPhoto().getThumb()).into(holder.ivItemPhoto);
    }

    @Override
    public int getItemCount() {
        return successList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public ImageView ivItemPhoto;
        public ViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView)itemView.findViewById(R.id.item_name);
            ivItemPhoto = (ImageView)itemView.findViewById(R.id.item_ph);
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

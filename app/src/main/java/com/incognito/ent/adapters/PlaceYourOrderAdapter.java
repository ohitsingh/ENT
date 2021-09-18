package com.incognito.ent.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.incognito.ent.R;
import com.incognito.ent.model.Menu;

import java.util.List;

public class PlaceYourOrderAdapter extends RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder> {
    private List<Menu> menuList;


    public PlaceYourOrderAdapter(List<Menu> restaurentModelList){
        this.menuList = restaurentModelList;

    }

    public void updateData(List<Menu> restaurentModelList){
        this.menuList = restaurentModelList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PlaceYourOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceYourOrderAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: ₹"+String.format("%.2f",menuList.get(position).getPrice()*menuList.get(position).getTotalInCart()));
        holder.menuQty.setText("Qty:" +menuList.get(position).getTotalInCart());
        Glide.with(holder.thumbImage)
                .load(menuList.get(position).getUrl())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return menuList.size();

    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        TextView menuPrice;
        TextView menuQty;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            menuName= view.findViewById(R.id.menuName);
            menuPrice=view.findViewById(R.id.menuPrice);
            menuQty=view.findViewById(R.id.menuQty);
            thumbImage=view.findViewById(R.id.thumbImage);

        }
    }
}

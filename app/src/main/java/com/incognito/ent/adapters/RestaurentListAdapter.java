package com.incognito.ent.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incognito.ent.R;
import com.incognito.ent.model.RestaurentModel;

import java.util.List;

public class RestaurentListAdapter extends RecyclerView.Adapter<RestaurentListAdapter.MyViewHolder> {
    private List<RestaurentModel> restaurentModelList;
    private RestaurantListClickListener clickListener;

    public RestaurentListAdapter(List<RestaurentModel> restaurentModelList,RestaurantListClickListener clickListener){
        this.restaurentModelList = restaurentModelList;
        this.clickListener=clickListener;
    }

    public void updateData(List<RestaurentModel> restaurentModelList){
        this.restaurentModelList = restaurentModelList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RestaurentListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurentListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.restaurentName.setText(restaurentModelList.get(position).getName());
        holder.restaurentAddress.setText("Address: "+restaurentModelList.get(position).getAddress());
        holder.restaurentHours.setText("Today's hours: "+restaurentModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(restaurentModelList.get(position));
            }
        });

        Glide.with(holder.thumbImage)
                .load(restaurentModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return restaurentModelList.size();

    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView restaurentName;
        TextView restaurentAddress;
        TextView restaurentHours;
        ImageView thumbImage;
        public MyViewHolder(View view) {
            super(view);
            restaurentName= view.findViewById(R.id.restaurentName);
            restaurentAddress=view.findViewById(R.id.restaurentAddress);
            restaurentHours=view.findViewById(R.id.restaurentHours);
            thumbImage=view.findViewById(R.id.thumbImage);




        }
    }
    public interface RestaurantListClickListener {
        public void onItemClick(RestaurentModel restaurentModel);
    }
}

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
import com.incognito.ent.model.RestaurentModel;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder> {
    private List<Menu> menuList;
    private MenuListClickListener clickListener;

    public MenuListAdapter(List<Menu> restaurentModelList, MenuListClickListener clickListener){
        this.menuList = restaurentModelList;
        this.clickListener=clickListener;
    }

    public void updateData(List<Menu> restaurentModelList){
        this.menuList = restaurentModelList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MenuListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: ₹"+menuList.get(position).getPrice());

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu=menuList.get(position);
                menu.setTotalInCart(1);
                clickListener.onAddToCartClick(menu);
                holder.addMoreLayout.setVisibility(View.VISIBLE);
                holder.addToCartButton.setVisibility(View.GONE);
                holder.tvCount.setText(menu.getTotalInCart()+"");

            }
        });
        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu=menuList.get(position);
                int total=menu.getTotalInCart();
                total--;
                if (total>0){
                    menu.setTotalInCart(total);
                    clickListener.onUpdateCartClick(menu);
                    holder.tvCount.setText(total +"");
                }else {
                    holder.addMoreLayout.setVisibility(View.GONE);
                    holder.addToCartButton.setVisibility(View.VISIBLE);
                    menu.setTotalInCart(total);
                    clickListener.onRemoveFromClick(menu);

                }
//                menu.setTotalInCart(1);
//                clickListener.onAddToCartClick(menu);
//                holder.addMoreLayout.setVisibility(View.VISIBLE);
//                holder.addToCartButton.setVisibility(View.GONE);
//                holder.tvCount.setText(menu.getTotalInCart()+"");

            }
        });
        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu=menuList.get(position);
                int total=menu.getTotalInCart();
                total++;
                if (total<=5){
                    menu.setTotalInCart(total);
                    clickListener.onUpdateCartClick(menu);
                    holder.tvCount.setText(total +"");
                }
//                menu.setTotalInCart(1);
//                clickListener.onAddToCartClick(menu);
//                holder.addMoreLayout.setVisibility(View.VISIBLE);
//                holder.addToCartButton.setVisibility(View.GONE);
//                holder.tvCount.setText(menu.getTotalInCart()+"");

            }
        });



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
        TextView addToCartButton;
        ImageView thumbImage;
        ImageView imageMinus;
        ImageView imageAddOne;
        TextView tvCount;
        LinearLayout addMoreLayout;
        public MyViewHolder(View view) {
            super(view);
            menuName= view.findViewById(R.id.menuName);
            menuPrice=view.findViewById(R.id.menuPrice);
            addToCartButton=view.findViewById(R.id.addToCartButton);
            thumbImage=view.findViewById(R.id.thumbImage);
            imageMinus= view.findViewById(R.id.imageMinus);
            imageAddOne= view.findViewById(R.id.imageAddOne);
            tvCount=view.findViewById(R.id.tvCount);
            addMoreLayout=view.findViewById(R.id.addMoreLayout);




        }
    }
    public interface MenuListClickListener {
        public void onAddToCartClick(Menu menu);
        public void onUpdateCartClick(Menu menu);
        public void onRemoveFromClick(Menu menu);
    }
}

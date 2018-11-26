package com.example.harpreet.vasdapunjab.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harpreet.vasdapunjab.Interface.ItemClickListner;
import com.example.harpreet.vasdapunjab.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView categoryName,categoryImage;



    private ItemClickListner itemClickListner;


    public CategoryViewHolder(View itemView) {
        super(itemView);

        categoryImage = itemView.findViewById(R.id.category_image);
        categoryName  = itemView.findViewById(R.id.category_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v,getAdapterPosition(),false);

    }
}

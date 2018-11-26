package com.example.harpreet.vasdapunjab;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.harpreet.vasdapunjab.Common.Common;
import com.example.harpreet.vasdapunjab.Interface.ItemClickListner;
import com.example.harpreet.vasdapunjab.Model.Category;
import com.example.harpreet.vasdapunjab.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.zip.Inflater;

import static com.example.harpreet.vasdapunjab.R.*;
import static com.example.harpreet.vasdapunjab.R.layout.category_layout;

public class CategoryFragment extends Fragment {
    View myFragment;
    RecyclerView listCategory;
    HashMap<Integer,String> hashMap;
    RecyclerView.LayoutManager layoutManager;
  FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference data_ref_categories;


public static CategoryFragment newInstance(){
    CategoryFragment categoryFragment = new CategoryFragment();
    return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         hashMap = new HashMap<>();
        hashMap.put(0,"Definition");
        hashMap.put(1,"Variables");

        database = FirebaseDatabase.getInstance();
        data_ref_categories = database.getReference("Category");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = (RecyclerView)myFragment.findViewById(id.quizlistCategory);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);


        loadcategories();

        return myFragment;
    }

    private void loadcategories() {
        FirebaseRecyclerOptions<Category>  optionsCategory = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(data_ref_categories, Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                optionsCategory) {



            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder CviewHolder,
                                            int position, @NonNull final Category model) {

                CviewHolder.categoryName.setText(model.getName());
           /*     Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(CviewHolder.categoryImage); */

           CviewHolder.categoryImage.setText(model.getImage());

                CviewHolder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(getActivity(),String.format("%s | %s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();
                    Intent startGame = new Intent(getActivity(),Start.class);
                        String s = hashMap.get(position);
                        Log.e("key",s+"");
                        startGame.putExtra("Cat",s);
                        startActivity(startGame);

                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View Cview = LayoutInflater.from(parent.getContext())
                        .inflate(layout.category_layout,parent,false);
                return new CategoryViewHolder(Cview);
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

}

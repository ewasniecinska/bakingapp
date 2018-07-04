package com.popular.android.baking_app.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popular.android.baking_app.R;
import com.popular.android.baking_app.RecipeDetailActivity;
import com.popular.android.baking_app.fragment.RecipeDetailFragment;
import com.popular.android.baking_app.models.Recipe;

import java.util.List;

/**
 * Created by ewasniecinska on 29.06.2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeHolder>{
    private List<Recipe> list;

    public RecipesAdapter (List<Recipe> list){
        this.list = list;

    }


    public static class RecipeHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        public RecipeHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textField);
            cardView = v.findViewById(R.id.cardView);


        }
    }

    @Override
    public RecipesAdapter.RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipes_item, parent, false);
        return new RecipeHolder(view);

    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, final int position) {
        String textField = list.get(position).getName();
        final RecipeHolder viewHolder = holder;
        viewHolder.textView.setText(textField);


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean tabletSize = view.getResources().getBoolean(R.bool.isTablet);
                Context context = viewHolder.textView.getContext();
                int orientation = context.getResources().getConfiguration().orientation;


                if(tabletSize && orientation == Configuration.ORIENTATION_LANDSCAPE){
                    RecipeDetailFragment fragment = new RecipeDetailFragment();
                    FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("RECIPE", list.get(position));
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment2, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Intent intent = new Intent(context, RecipeDetailActivity.class);
                    intent.putExtra("RECIPE", list.get(position));
                    context.startActivity(intent);
                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
package com.popular.android.baking_app.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popular.android.baking_app.R;
import com.popular.android.baking_app.models.Ingredient;

import java.util.List;

/**
 * Created by ewasniecinska on 30.06.2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientHolder>{

        private List<Ingredient> list;


        public IngredientsAdapter(List<Ingredient> list){
            this.list = list;

        }



        public static class IngredientHolder extends RecyclerView.ViewHolder {
            TextView textView;
            TextView textMeasure;
            CardView cardView;

            public IngredientHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.textField);
                textMeasure = v.findViewById(R.id.text_measure);
                cardView = v.findViewById(R.id.cardView);

            }
        }

        @Override
        public IngredientsAdapter.IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ingredient_item, parent, false);
            return new IngredientsAdapter.IngredientHolder(view);

        }

        @Override
        public void onBindViewHolder(IngredientsAdapter.IngredientHolder holder, final int position) {
            String textField = list.get(position).getIngredient();
            String textMeasure = list.get(position).getQuantity() + " " + list.get(position).getMeasure();
            final IngredientsAdapter.IngredientHolder viewHolder = holder;
            viewHolder.textView.setText(textField);
            viewHolder.textMeasure.setText(textMeasure);


        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }


package com.popular.android.baking_app.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular.android.baking_app.R;
import com.popular.android.baking_app.adapter.IngredientsAdapter;
import com.popular.android.baking_app.models.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment {
    List<Ingredient> ingredients = new ArrayList<Ingredient>();
    GridLayoutManager gridLayoutManager;
    RecyclerView mRecyclerView;
    boolean tabletSize;
    int orientation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tabletSize = getResources().getBoolean(R.bool.isTablet);
        orientation = getActivity().getResources().getConfiguration().orientation;


        getActivity().setTitle(getString(R.string.label_ingridients));

        getIngredientsList();

        setUpRecycleView(view);

        return view;

    }

    public void setUpRecycleView(View view){
        mRecyclerView = view.findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new IngredientsAdapter(ingredients));
    }

    public void getIngredientsList(){
        if(tabletSize && orientation == Configuration.ORIENTATION_LANDSCAPE){
            ingredients = getArguments().getParcelableArrayList(getString(R.string.INGREDIENT_BUNDLE));
        } else {
            Intent intent = getActivity().getIntent();
            ingredients = intent.getParcelableArrayListExtra(getString(R.string.INGREDIENT_BUNDLE));
        }



    }


}

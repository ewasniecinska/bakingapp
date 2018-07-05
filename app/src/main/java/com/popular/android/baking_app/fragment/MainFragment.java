package com.popular.android.baking_app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular.android.baking_app.R;
import com.popular.android.baking_app.adapter.RecipesAdapter;
import com.popular.android.baking_app.api.Service;
import com.popular.android.baking_app.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ewasniecinska on 29.06.2018.
 */

public class MainFragment extends Fragment {
    List<Recipe> recipes = new ArrayList<Recipe>();
    GridLayoutManager gridLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);

        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), calculateNoOfColumns(getActivity().getApplicationContext()));

        mRecyclerView.setLayoutManager(gridLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.BASE_API_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Recipe>> call = service.getRecipes();


        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipes = response.body();
                mRecyclerView.setAdapter(new RecipesAdapter(recipes));

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable throwable) {
            }
        });
        return view;
    }

    public int calculateNoOfColumns(Context context){
        int value = getActivity().getResources().getConfiguration().orientation;
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize && value == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int scalingFactor = 360;
            int noOfColumns = (int) (dpWidth / scalingFactor);
            if(noOfColumns < 2) {
                noOfColumns = 2;
            }
            return noOfColumns;
        } else {
            return 1;
        }
    }
}

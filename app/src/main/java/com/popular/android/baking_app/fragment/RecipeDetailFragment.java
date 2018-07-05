package com.popular.android.baking_app.fragment;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.popular.android.baking_app.IngredientsActivity;
import com.popular.android.baking_app.R;
import com.popular.android.baking_app.adapter.StepsAdapter;
import com.popular.android.baking_app.models.Ingredient;
import com.popular.android.baking_app.models.Recipe;
import com.popular.android.baking_app.models.Step;
import com.popular.android.baking_app.widget.RecipeAppWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewasniecinska on 30.06.2018.
 */

public class RecipeDetailFragment extends Fragment  {

    Recipe recipe;
    GridLayoutManager gridLayoutManager;
    View view;
    boolean tabletSize;
    Intent intent;
    int orientation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        tabletSize = view.getResources().getBoolean(R.bool.isTablet);
        orientation = getActivity().getResources().getConfiguration().orientation;

        setHasOptionsMenu(true);

        // get recipe data
        getRecipe(savedInstanceState);

        getActivity().setTitle(recipe.getName());

        // button + listener
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(tabletSize && orientation == Configuration.ORIENTATION_LANDSCAPE){
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle bundle=new Bundle();

                        IngredientsFragment fragment = new IngredientsFragment();

                        List<Ingredient> ingredients = recipe.getIngredients();
                        bundle.putParcelableArrayList(getString(R.string.INGREDIENT_BUNDLE), (ArrayList<? extends Parcelable>) ingredients); //key and value
                        fragment.setArguments(bundle);

                        fragmentTransaction.replace(R.id.fragment2, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    } else {
                        Intent intentIng = new Intent(getActivity().getApplicationContext(), IngredientsActivity.class);
                        intentIng.putExtra(getString(R.string.INGREDIENT_BUNDLE), recipe.getIngredients());
                        startActivity(intentIng);
                    }
                }
            });

        setUpRecycleView(view);

        return view;
    }

    public void getRecipe(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            recipe = savedInstanceState.getParcelable(getString(R.string.RECIPE_BUNDLE));
        } else {
            if(tabletSize && orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recipe = getArguments().getParcelable(getString(R.string.RECIPE_BUNDLE));
            } else {
                intent = getActivity().getIntent();
                recipe = intent.getParcelableExtra(getString(R.string.RECIPE_BUNDLE));
            }
        }
    }

    public void setUpRecycleView(View view){
        ArrayList<Step> steps = recipe.getSteps();

        final RecyclerView mRecyclerView = view.findViewById(R.id.recycyle_view_ingredients);
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new StepsAdapter(steps));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.RECIPE_BUNDLE), recipe);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recipe_tool_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.add_widget:
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.widget_added_msg),Toast.LENGTH_LONG).show();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity().getApplicationContext());
                RemoteViews remoteViews = new RemoteViews(getActivity().getApplicationContext().getPackageName(), R.layout.recipe_app_widget);
                ComponentName thisWidget = new ComponentName(getActivity().getApplicationContext(), RecipeAppWidget.class);
                remoteViews.setTextViewText(R.id.widget_recipe_name, recipe.getName());
                remoteViews.setTextViewText(R.id.widget_ingridants_list, getListOfIngredients(recipe));
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getListOfIngredients(Recipe recipe){
        String namesOfIngredients = "";
        StringBuilder sB = new StringBuilder(namesOfIngredients);
        List<Ingredient> ingredients = recipe.getIngredients();
        int sizeOfList = ingredients.size();


        for(int i = 0; i < sizeOfList; i++){
            sB.append("- " + ingredients.get(i).getIngredient() + System.lineSeparator());
        }

        return sB.toString();
    }


}

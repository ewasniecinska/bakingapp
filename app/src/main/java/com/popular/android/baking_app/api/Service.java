package com.popular.android.baking_app.api;

import com.popular.android.baking_app.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ewasniecinska on 29.06.2018.
 */

public interface Service {
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();

}

package com.popular.android.baking_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.popular.android.baking_app.idle_resources.Idle;

public class MainActivity extends AppCompatActivity{
    @Nullable
    private Idle idle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdlingResource();
    }


    @VisibleForTesting
    @NonNull
    public Idle getIdlingResource() {
        if (idle == null) {
            idle = new Idle();
        }
        return idle;
    }

}

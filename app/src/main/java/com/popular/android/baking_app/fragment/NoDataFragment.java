package com.popular.android.baking_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popular.android.baking_app.R;

/**
 * Created by ewasniecinska on 02.07.2018.
 */

public class NoDataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_date, container, false);
        return view;
    }
}

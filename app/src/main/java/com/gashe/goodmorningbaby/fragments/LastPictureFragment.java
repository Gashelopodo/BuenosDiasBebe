package com.gashe.goodmorningbaby.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gashe.goodmorningbaby.R;

/**
 * Created by Gashe on 22/5/17.
 */

public class LastPictureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.last_picture_fragment, container, false);
    }
}

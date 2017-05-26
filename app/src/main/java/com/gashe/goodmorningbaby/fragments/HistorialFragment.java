package com.gashe.goodmorningbaby.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gashe.goodmorningbaby.models.Notification;
import com.gashe.goodmorningbaby.R;
import com.gashe.goodmorningbaby.adapters.HistorialAdapter;
import com.gashe.goodmorningbaby.utils.Prefs;

import java.util.Collection;

/**
 * Created by Gashe on 22/5/17.
 */

public class HistorialFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.historial_fragment, container, false);

        Prefs prefs = new Prefs(getContext());
        Collection<Notification> notifications = prefs.getNotifications();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        if(notifications != null) {
            HistorialAdapter historialAdapter = new HistorialAdapter(notifications);
            recyclerView.setAdapter(historialAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }

        return view;

    }
}

package com.gashe.goodmorningbaby.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gashe.goodmorningbaby.viewholders.HistorialViewHolder;
import com.gashe.goodmorningbaby.models.Notification;
import com.gashe.goodmorningbaby.R;

import java.util.Collection;

/**
 * Created by g5 on 26/5/17.
 */

public class HistorialAdapter extends RecyclerView.Adapter<HistorialViewHolder> {

    private Collection<Notification> notifications;
    private Context context;

    public HistorialAdapter(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    @Override
    public HistorialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistorialViewHolder historialViewHolder = null;
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_historial, parent, false);
        historialViewHolder = new HistorialViewHolder(view);
        return historialViewHolder;

    }

    @Override
    public void onBindViewHolder(HistorialViewHolder holder, int position) {
        Log.d(getClass().getCanonicalName(), "Entro en position: " + position);
        Notification notification = (Notification) notifications.toArray()[position];
        notification.setPosition(position);
        holder.loadDataHolder(notification);
    }

}

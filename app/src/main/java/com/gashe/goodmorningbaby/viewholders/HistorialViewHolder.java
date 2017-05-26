package com.gashe.goodmorningbaby.viewholders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gashe.goodmorningbaby.models.Notification;
import com.gashe.goodmorningbaby.R;

/**
 * Created by g5 on 26/5/17.
 */

public class HistorialViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;
    private Context context;

    public HistorialViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.myDate);
        imageView = (ImageView) itemView.findViewById(R.id.myImage);
        context = itemView.getContext();
    }

    public void loadDataHolder(Notification notification){

        Log.d(getClass().getCanonicalName(), "PINTO LA NOTIFICACION");

        textView.setText("Enviado el " + notification.getDia() + "/" + notification.getMes() + "/" + notification.getAnio());
        byte[] decodedString = Base64.decode(notification.getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(bitmap);

    }

}

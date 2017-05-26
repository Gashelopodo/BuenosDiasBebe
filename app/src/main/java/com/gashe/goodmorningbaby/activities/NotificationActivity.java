package com.gashe.goodmorningbaby.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.gashe.goodmorningbaby.models.Notification;
import com.gashe.goodmorningbaby.R;
import com.google.gson.Gson;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();
        String notification_string = intent.getStringExtra("notification");
        Gson gson = new Gson();
        Notification notification = gson.fromJson(notification_string, Notification.class);

        byte[] decodedString = Base64.decode(notification.getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        TextView textView = (TextView) findViewById(R.id.myDate);
        textView.setText("Enviado el "+notification.getDia()+"/"+notification.getMes()+"/"+notification.getAnio());

        ImageView imageView = (ImageView) findViewById(R.id.myImage);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

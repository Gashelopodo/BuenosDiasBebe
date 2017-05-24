package com.gashe.goodmorningbaby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

        TextView textView = (TextView) findViewById(R.id.myTextView);
        textView.setText(notification.getDia()+"/"+notification.getMes()+"/"+notification.getAnio());

    }
}

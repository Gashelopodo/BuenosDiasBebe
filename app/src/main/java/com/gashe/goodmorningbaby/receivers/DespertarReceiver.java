package com.gashe.goodmorningbaby.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gashe.goodmorningbaby.services.MyServiceAlarm;

public class DespertarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MyServiceAlarm.class);
        intent1.putExtra("control", false);
        context.startService(intent);
    }
}

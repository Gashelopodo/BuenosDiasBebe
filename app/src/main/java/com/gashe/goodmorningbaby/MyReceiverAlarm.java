package com.gashe.goodmorningbaby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getCanonicalName(), "Ha saltado la alarma");
        Intent intent_serv = new Intent(context, MyServiceAlarm.class);
        context.startService(intent_serv);
    }
}

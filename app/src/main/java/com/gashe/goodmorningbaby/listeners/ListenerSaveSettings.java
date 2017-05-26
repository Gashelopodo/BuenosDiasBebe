package com.gashe.goodmorningbaby.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gashe.goodmorningbaby.R;
import com.gashe.goodmorningbaby.fragments.SettingFragment;
import com.gashe.goodmorningbaby.services.MyServiceAlarm;
import com.gashe.goodmorningbaby.utils.Constants;
import com.gashe.goodmorningbaby.utils.Prefs;

/**
 * Created by Gashe on 23/5/17.
 */

public class ListenerSaveSettings implements View.OnClickListener {

    private Context context;
    private boolean sendNotification;

    public ListenerSaveSettings(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        Activity activity = (Activity) context;
        TextView textView = (TextView) activity.findViewById(R.id.timepicker);
        String hour = textView.getText().toString();
        sendNotification = SettingFragment.isChecked;

        Prefs prefs = new Prefs(context);
        prefs.setDataSetting(sendNotification, hour);

        Intent intent = new Intent(context, MyServiceAlarm.class);
        intent.putExtra("control", true);
        context.startService(intent);

        Toast.makeText(context, Constants.ALERT.SAVE_SETTING_SUCCESS, Toast.LENGTH_LONG).show();
    }
}

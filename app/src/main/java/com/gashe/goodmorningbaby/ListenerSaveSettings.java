package com.gashe.goodmorningbaby;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

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
        Spinner spinner = (Spinner) activity.findViewById(R.id.spinnerHours);
        String hour = spinner.getSelectedItem().toString();
        sendNotification = SettingFragment.isChecked;

        Prefs prefs = new Prefs(context);
        prefs.setDataSetting(sendNotification, hour);

        Intent intent = new Intent(context, MyServiceAlarm.class);
        intent.putExtra("control", true);
        context.startService(intent);

        Toast.makeText(context, Constants.ALERT.SAVE_SETTING_SUCCESS, Toast.LENGTH_LONG).show();
    }
}

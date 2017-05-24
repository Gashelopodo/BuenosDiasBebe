package com.gashe.goodmorningbaby;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;

/**
 * Created by Gashe on 22/5/17.
 */

public class SettingFragment extends Fragment {

    private boolean sendNotification;
    public static boolean isChecked = false;
    Spinner spinner;
    Button button;
    SwitchCompat switchCompat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.setting_fragment, container, false);

        Prefs prefs = new Prefs(getContext());
        sendNotification = prefs.getSendNotification();

        spinner = (Spinner) view.findViewById(R.id.spinnerHours);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.hours_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(sendNotification);

        switchCompat = (SwitchCompat) view.findViewById(R.id.switch_notification);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                spinner.setEnabled(b);
                isChecked = b;
            }
        });

        if(sendNotification){
            int position = adapter.getPosition(prefs.getHourNotification());
            spinner.setSelection(position);
            switchCompat.setChecked(true);
        }

        button = (Button) view.findViewById(R.id.saveAlarm);
        button.setOnClickListener(new ListenerSaveSettings(getContext()));

        return view;
    }

    public void miFuncion(){

    }


}

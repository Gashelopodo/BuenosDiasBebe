package com.gashe.goodmorningbaby.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.gashe.goodmorningbaby.listeners.ListenerSaveSettings;
import com.gashe.goodmorningbaby.R;
import com.gashe.goodmorningbaby.utils.Prefs;

/**
 * Created by Gashe on 22/5/17.
 */

public class SettingFragment extends Fragment {

    private boolean sendNotification;
    public static boolean isChecked = false;
    Spinner spinner;
    TextView textView;
    Button button;
    SwitchCompat switchCompat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.setting_fragment, container, false);

        Prefs prefs = new Prefs(getContext());
        sendNotification = prefs.getSendNotification();

        textView = (TextView) view.findViewById(R.id.timepicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.hours_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textView.setEnabled(sendNotification);

        switchCompat = (SwitchCompat) view.findViewById(R.id.switch_notification);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                textView.setEnabled(b);
                isChecked = b;
            }
        });

        if(sendNotification){
            textView.setText(prefs.getHourNotification());
            switchCompat.setChecked(true);
        }

        button = (Button) view.findViewById(R.id.saveAlarm);
        button.setOnClickListener(new ListenerSaveSettings(getContext()));

        return view;
    }


}

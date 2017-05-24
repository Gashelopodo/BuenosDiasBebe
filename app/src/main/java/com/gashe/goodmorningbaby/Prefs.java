package com.gashe.goodmorningbaby;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gashe on 23/5/17.
 */

public class Prefs {

    private Context context;
    private SharedPreferences prefs = null;
    static final String PREF_SEND_NOTIFICATION = "pref_send_notification";
    static final String PREF_HOUR_NOTIFICATION = "pref_hour_notification";
    static final String PREF_NOTIFICATIONS = "pref_notifications";

    public Prefs(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public void setDataSetting(boolean sendNotification, String hour){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_SEND_NOTIFICATION, sendNotification);
        editor.putString(PREF_HOUR_NOTIFICATION, hour);
        editor.commit();
    }

    public boolean getSendNotification(){
        boolean sendNotification = false;
        sendNotification = prefs.getBoolean(PREF_SEND_NOTIFICATION, false);
        return sendNotification;
    }

    public String getHourNotification(){
        String hour = null;
        hour = prefs.getString(PREF_HOUR_NOTIFICATION, "");
        return hour;
    }

    public void setNotification(Notification notification){
        Gson gson = new Gson();
        SharedPreferences.Editor editor = prefs.edit();
        Collection<Notification> notifications = this.getNotifications();
        if(notifications == null){
           notifications = new ArrayList<Notification>();
        }

        notifications.add(notification);
        Log.d(getClass().getCanonicalName(), "###########" + notifications.size());
        Log.d(getClass().getCanonicalName(), "###########" + gson.toJson(notifications));

        editor.putString(PREF_NOTIFICATIONS, gson.toJson(notifications));
        editor.commit();
    }

    public Collection<Notification> getNotifications(){
        Collection<Notification> notifications = null;
        String notifications_string = prefs.getString(PREF_NOTIFICATIONS, null);
        Gson gson = new Gson();

        Type collectionType = new TypeToken<Collection<Notification>>(){}.getType();
        notifications = gson.fromJson(notifications_string, collectionType);

        return notifications;
    }


}

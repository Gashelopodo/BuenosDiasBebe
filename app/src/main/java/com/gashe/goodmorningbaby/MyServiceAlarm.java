package com.gashe.goodmorningbaby;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;

public class MyServiceAlarm extends Service {

    private boolean control;

    public MyServiceAlarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO llamar a json
        control = intent.getBooleanExtra("control", false);
        if(!control){
            Log.d(getClass().getCanonicalName(), "AQUÍ LLAMO PARA VER SI HAY IMAGEN");
            CheckNotificationAsyncTask checkNotification = new CheckNotificationAsyncTask(this);
            checkNotification.execute();
        }
        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Prefs prefs = new Prefs(this);
        String hour = prefs.getHourNotification();
        programAlarm();
        super.onDestroy();
    }

    // funciones adicionales
    public void programAlarm(){

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance(); // momento actual
        //calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar);
        long actual_time = calendar.getTimeInMillis();
        long alarm_time = actual_time + +10000;

        Log.d(getClass().getCanonicalName(), "Tiempo actual: " + actual_time);
        Log.d(getClass().getCanonicalName(), "Tiempo alarma: " + alarm_time);

        //creamos el pending intent para cuando termine el servicio alarma y ejecute el receivers
        Intent intent_receiver = new Intent(this, MyReceiverAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent_receiver, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm_time, pendingIntent);

    }

    public void existNotification(Notification notification){

        Prefs prefs = new Prefs(this);
        Collection<Notification> notifications = prefs.getNotifications();

        if(notifications != null) {
            Log.d(getClass().getCanonicalName(), "TAMAAÑO NOTIFICATIONS: " + notifications.size());

            for (Notification not : notifications) {
                if (not.getAnio() == notification.getAnio()
                        && not.getMes() == notification.getMes()
                        && not.getDia() == notification.getDia()) {

                    Log.d(getClass().getCanonicalName(), "FOTO REPETIDA");

                } else {
                    sendNotification(notification);
                    prefs.setNotification(notification);
                }
            }
        }else{
            sendNotification(notification);
            prefs.setNotification(notification);
        }

    }

    private void sendNotification(Notification notification){

        Gson gson = new Gson();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("GMB APP")
                .setContentText("¡Buenos días BEBE!");

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("notification", gson.toJson(notification));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(), intent, PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(537, builder.build());

    }


}

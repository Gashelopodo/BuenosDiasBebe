package com.gashe.goodmorningbaby;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
                    Bitmap bitmap = saveImage(notification);
                    sendNotification(notification, bitmap);
                    prefs.setNotification(notification);
                }
            }
        }else{
            Bitmap bitmap = saveImage(notification);
            sendNotification(notification, bitmap);
            prefs.setNotification(notification);
        }

    }

    private Bitmap saveImage(Notification notification){
        byte[] decodedString = Base64.decode(notification.getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        bitmap = bitmapCompress(bitmap);

        //convertimos de nuevo a base64
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOS);
        String base64 = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
        notification.setFoto(base64);

        return bitmap;
    }

    public Bitmap bitmapCompress(Bitmap bitmap){
        Bitmap bitmap_compress = null;
        int height = 500;
        int width = (bitmap.getHeight()*height)/bitmap.getWidth();
        bitmap_compress = Bitmap.createScaledBitmap(bitmap, height, width, true);
        return bitmap_compress;
    }

    private void sendNotification(Notification notification, Bitmap bitmap){

        Gson gson = new Gson();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("GMB APP")
                .setLargeIcon(bitmap)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText("¡Buenos días BEBE!");

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("notification", gson.toJson(notification));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(), intent, PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(537, builder.build());

    }


}

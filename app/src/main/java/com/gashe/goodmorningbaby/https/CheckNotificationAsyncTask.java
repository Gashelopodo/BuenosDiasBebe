package com.gashe.goodmorningbaby.https;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gashe.goodmorningbaby.models.Notification;
import com.gashe.goodmorningbaby.services.MyServiceAlarm;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Gashe on 24/5/17.
 */

public class CheckNotificationAsyncTask extends AsyncTask<Void, Void, Notification> {

    public static final String URL_GET_NOTIFICATION = "http://femxa-ebtm.rhcloud.com/BuenosDiasBebe?fecha=";
    private Context context;

    public CheckNotificationAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Notification doInBackground(Void... voids) {

        Notification notification = null;
        String url_image = null;
        URL object_url = null;
        HttpURLConnection http = null;
        int response = -1;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;

        try {
            object_url = new URL(URL_GET_NOTIFICATION);
            http = (HttpURLConnection) object_url.openConnection();

            response = http.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                Log.d("MENSAJE", "Todo OK");
                Gson gson = new Gson();
                inputStream = http.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-15"));
                bufferedReader = new BufferedReader(inputStreamReader);
                String response_string = bufferedReader.readLine();
                notification = gson.fromJson(response_string, Notification.class);
            } else {
                Log.d("MENSAJE", "Algo fue mal");
            }

        }catch (Throwable t){

            Log.d("MENSAJE", "Algo fue mal" + t);

        }finally {
            http.disconnect();
        }

        return notification;

    }

    @Override
    protected void onPostExecute(Notification notification) {

        MyServiceAlarm myServiceAlarm = (MyServiceAlarm) context;
        myServiceAlarm.existNotification(notification);
        Log.d(getClass().getCanonicalName(), "NOTIFICACION: " + notification.getDia()+"/"+notification.getMes()+"/"+notification.getAnio());
        super.onPostExecute(notification);
    }

}

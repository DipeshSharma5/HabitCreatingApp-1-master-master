package com.example.pushkar.habitcreatingapp.Models;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.pushkar.habitcreatingapp.AddRitual;
import com.example.pushkar.habitcreatingapp.R;

public class MyAlarm extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "notifi";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_menu_share)
                        .setContentTitle(AddRitual.a)
                        .setContentText("Nice quote")
                        .setChannelId(CHANNEL_ID).build();

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);

        mNotificationManager.notify(notifyID , notification);
        Log.d("MyAlarmBelal", "Alarm just fired");
    }
}

package com.example.user.projetz.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;


import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.view.Main2Activity;

public abstract class Notificateur {
    private static NotificationCompat.Builder mBuilder ;
    private static final int SIMPLE_NOTIFICATION_ID = 1024; //arbitrary constant
    private static final int FIN_FORFAIT_ID = 1025 ;
    private static final int ANNULER_FORFAIT_ID = 1026 ;
    private static final int PROGRESSION_ID = 1027 ;

    public static void simpleNotification(Context context, Forfait forfait){
        Param param = new Param(context);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_web);
        mBuilder.setContentTitle(context.getString(R.string.subscriptionNotif));
        mBuilder.setContentText(context.getString(R.string.subscriptionNotifMessage)+ forfait.getNom());
        mBuilder.setColorized(true);
        mBuilder.setColor(Color.argb(1, 255, 255, 255));

        if(param.getLightNotification() == 1)
            mBuilder.setLights(0xFFff0000, 100, 100);

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(param.getRingNotification() == 1)
            mBuilder.setSound(uri);

        if(param.getPopupNotification() == 1)
            mBuilder.setPriority(Notification.PRIORITY_MAX);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(SIMPLE_NOTIFICATION_ID, mBuilder.build());
    }

    public static void finForfait(Context context){
        Param param = new Param(context);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_web);
        mBuilder.setContentTitle(context.getString(R.string.subscriptionEndNotif));
        mBuilder.setContentText(context.getString(R.string.subscriptionEndNotifMessage));
        mBuilder.setColorized(true);
        mBuilder.setColor(Color.argb(1, 255, 255, 255));

        if(param.getVibratingNotification() == 1)
            mBuilder.setVibrate(new long[]{(long)0, (long)3000, (long)1000});

        if(param.getPopupNotification() == 1)
            mBuilder.setPriority(Notification.PRIORITY_MAX);

        if(param.getLightNotification() == 1)
            mBuilder.setLights(0xFFff0000, 100, 100);

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        if(param.getRingNotification() == 1)
            mBuilder.setSound(uri);

        Intent resultIntent = new Intent(Intent.ACTION_MAIN);
        resultIntent.setComponent(new ComponentName("com.android.settings",
                "com.android.settings.Settings$DataUsageSummaryActivity"));
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Main2Activity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(param.getNotificatitonFinPushed() == 0){
            mNotificationManager.notify(FIN_FORFAIT_ID, mBuilder.build());
            param.setNotification50Pushed(0);
            param.setNotification80Pushed(0);
            param.setNotificatitonFinPushed(1);
            param.saveParam(context);
        }
    }

    public static void annulerForfait(Context context, Forfait forfait){
        Param param = new Param(context);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_web);
        mBuilder.setContentTitle(context.getString(R.string.cancelNotif));
        mBuilder.setContentText(context.getString(R.string.cancelNotifMessage)+ forfait.getNom());
        mBuilder.setColorized(true);

        if(param.getLightNotification() == 1)
            mBuilder.setLights(0xFFff0000, 100, 100);

        mBuilder.setColor(Color.argb(1, 255, 255, 255));
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(param.getRingNotification() == 1)
            mBuilder.setSound(uri);

        if(param.getPopupNotification() == 1)
            mBuilder.setPriority(Notification.PRIORITY_MAX);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(ANNULER_FORFAIT_ID, mBuilder.build());
    }

    public static void progression(Context context, int niveau){
        Param param = new Param(context);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_web);
        mBuilder.setContentTitle(context.getString(R.string.stateOfConsumption));

        if(niveau == 50){
            mBuilder.setContentText(context.getString(R.string.state50));
        }else{
            mBuilder.setContentText(context.getString(R.string.state80));
        }

        mBuilder.setColorized(true);

        if(param.getLightNotification() == 1)
            mBuilder.setLights(0xFFff0000, 100, 100);

        mBuilder.setColor(Color.argb(1, 255, 255, 255));
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(param.getRingNotification() == 1)
            mBuilder.setSound(uri);

        if(param.getPopupNotification() == 1)
            mBuilder.setPriority(Notification.PRIORITY_MAX);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(PROGRESSION_ID, mBuilder.build());

        mNotificationManager.notify(PROGRESSION_ID, mBuilder.build());
    }
}

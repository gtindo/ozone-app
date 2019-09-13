package com.example.user.projetz;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.projetz.model.Souscription;
import com.example.user.projetz.utils.Notificateur;
import com.example.user.projetz.utils.Param;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class MainService extends Service {
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        mHandler.postDelayed(m_Runnable, 5000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            Param param = new Param(MainService.this);
            Souscription souscription = param.getActiveSubscription();

            boolean mobileDataEnabled = false; // Assume disabled
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            try {
                Class cmClass = Class.forName(cm.getClass().getName());
                Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
                method.setAccessible(true); // Make the method callable
                // get the setting for "mobile data"
                mobileDataEnabled = (Boolean)method.invoke(cm);
            } catch (Exception e) {
                // Some problem accessible private API
                // TODO do whatever error handling you want here
            }

            if(souscription != null && mobileDataEnabled) {
                long oldStat = param.getData();
                long now = (new Date()).getTime();
                long expire = souscription.getDateFin().getTime();
                long dataRestante = souscription.getDataRestante();
                long dataBefore = param.getDataBeforeShutdown();
                long dataForfait = souscription.getForfait().getNbOctets();
                long stat = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
                long usage = 0;

                float ratio = ((float)dataRestante / (float)dataForfait) ;


                if(ratio >=0.49 && ratio <= 0.51){
                    if(param.getNotification50Pushed() == 0)
                        Notificateur.progression(MainService.this, 50);
                    param.setNotification50Pushed(1);
                    powerOnScreen();
                }

                if(ratio >= 0.19 && ratio <= 0.21){
                    if(param.getNotification80Pushed() == 0){
                        Notificateur.progression(MainService.this, 80);
                    }
                    param.setNotification80Pushed(1);
                    powerOnScreen();
                }

                Calendar day = Calendar.getInstance();
                day.setTime(new Date());
                int hours = day.get(Calendar.HOUR_OF_DAY);
                int minutes = day.get(Calendar.MINUTE);
                int sec = day.get(Calendar.SECOND);

                String typeForfait = souscription.getForfait().getType();

                switch (typeForfait) {
                    case "standard":
                        if (expire > now && (dataRestante / 1024 / 1024) >= 13) {
                            if (oldStat == -1) {
                                //le telephone a ete eteint depuis la souscription
                                usage = stat;
                                param.getActiveSubscription().setDataRestante(dataBefore - usage);
                            } else {
                                //le telephone n'a pas ete eteint depuis la souscription
                                usage = stat - oldStat;
                                param.getActiveSubscription().setDataRestante(dataForfait - usage);
                            }
                            param.saveParam(MainService.this);
                        } else {
                            expire(param, MainService.this);
                        }

                        break;

                    case "night":
                        if (expire > now && (dataRestante / 1024 / 1024) >= 13) {
                            if(hours >= 0 && hours <= 6){
                                if (oldStat == -1) {
                                    //le telephone a ete eteint depuis la souscription
                                    usage = stat;
                                    param.getActiveSubscription().setDataRestante(dataRestante - usage);
                                } else {
                                    //le telephone n'a pas ete eteint depuis la souscription
                                    usage = stat - oldStat;
                                    param.getActiveSubscription().setDataRestante(dataForfait - usage);
                                }
                                param.saveParam(MainService.this);
                            }

                            if(hours == 5 && (minutes >= 55 || minutes <=59)){
                                expire(param, MainService.this);
                            }
                        }else{
                            expire(param, MainService.this);
                        }
                        break;

                    case "gigadata":
                        if (expire > now) {
                            if (hours == 0 && minutes == 0 && sec <= 5) {
                                //reinitialisation de la data journaliere
                                param.getActiveSubscription().setDataRestante(dataForfait);
                                param.setNotificatitonFinPushed(0);

                                //reinitialiser le setData
                                param.setData(TrafficStats.getMobileRxBytes()+TrafficStats.getMobileTxBytes());
                            }else{
                                if ((dataRestante / 1024 / 1024) >= 13) {
                                    if (oldStat == -1) {
                                        //le telephone a ete eteint depuis la souscription
                                        usage = stat;
                                        param.getActiveSubscription().setDataRestante(dataRestante - usage);
                                    } else {
                                        //le telephone n'a pas ete eteint depuis la souscription
                                        usage = stat - oldStat;
                                        param.getActiveSubscription().setDataRestante(dataForfait - usage);
                                    }
                                }else{
                                    expire(param, MainService.this);
                                }
                            }
                           param.saveParam(MainService.this);
                        }else{
                            expire(param, MainService.this);
                            param.setActiveSubscription(null);
                            param.saveParam(MainService.this);
                        }
                        break;

                }
            }
            mHandler.postDelayed(m_Runnable, 5000);
        }

    };

    private void expire(Param param, MainService service){
        Notificateur.finForfait(service);
        param.setNotification50Pushed(0);
        param.setNotification80Pushed(0);
        powerOnScreen();

        TelephonyManager telephonyManager = (TelephonyManager) service.getSystemService(Context.TELEPHONY_SERVICE);
        if(!param.getActiveSubscription().getForfait().getType().equals("gigadata")){
            param.setActiveSubscription(null);
        }
        param.saveParam(service);

        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, false);
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error setting mobile data state", ex);
        }
    }

    private void powerOnScreen(){
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        boolean result= Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT_WATCH&&powerManager.isInteractive()|| Build.VERSION.SDK_INT< Build.VERSION_CODES.KITKAT_WATCH&&powerManager.isScreenOn();

        if (!result){
            PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MH24_SCREENLOCK");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MH24_SCREENLOCK");
            wl_cpu.acquire(10000);
        }
    }
}


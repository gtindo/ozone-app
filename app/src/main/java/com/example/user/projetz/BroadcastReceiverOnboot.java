package com.example.user.projetz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.user.projetz.utils.Param;


public class BroadcastReceiverOnboot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                Param param = new Param(context) ;
                if(param.getActiveSubscription()!=null){
                    long dataRestante = param.getActiveSubscription().getDataRestante();
                    param.setDataBeforeShutdown(dataRestante);
                }
                param.setData(-1);
                param.saveParam(context);
                Intent serviceIntent = new Intent(context, MainService.class);
                context.startService(serviceIntent);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}

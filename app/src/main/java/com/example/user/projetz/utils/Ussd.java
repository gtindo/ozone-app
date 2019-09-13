package com.example.user.projetz.utils;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.app.Activity;

import com.example.user.projetz.MainService;
import com.example.user.projetz.R;
import com.example.user.projetz.model.Forfait;
import com.example.user.projetz.model.Infos;
import com.example.user.projetz.model.Souscription;

/**
 * <p>
 *     Cette classe contient les methodes pour effectuer des requettes ussd
 * </p>
 */

public abstract class Ussd {

    public static void sendUssd(final Forfait forfait, final Activity activity, String operateur) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String code = "";

        if(operateur.equals("orange")){
            code = Uri.encode("#") + Uri.parse(forfait.getCodeUssd()) + Uri.encode("#") ;
        }else{
            code = Uri.parse(forfait.getCodeUssd()) + Uri.encode("#") ;
        }

        intent.setData(Uri.parse("tel:" + code));

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            PermissionsManager.requestCallPhonePemission(activity);
        }else{
            activity.startActivity(intent);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.MyDialogTheme);
            alertDialogBuilder.setMessage(R.string.addSubscription);
            alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Param param = new Param(activity);
                    Souscription souscription = new Souscription(forfait);
                    souscription.sauvegarderSouscription(activity);

                    param.setData(TrafficStats.getMobileRxBytes()+TrafficStats.getMobileTxBytes());
                    param.setActiveSubscription(souscription);
                    param.saveParam(activity);

                    Notificateur.simpleNotification(activity, forfait);
                    activity.startService(new Intent(activity.getBaseContext(), MainService.class));
                }
            });

            alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialogBuilder.show();
        }
    }

}

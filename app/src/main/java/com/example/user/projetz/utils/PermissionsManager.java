package com.example.user.projetz.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * <p>
 *     La classe Permissions manager contient les constantes et les methodes pour gerer les differentes permission de l'application.
 * </p>
 *
 * @author GUEKENG TINDO YVAN
 */

public abstract class PermissionsManager {

    public static final int MY_PERMISSION_CALL_PHONE = 1 ;
    public static final int MY_PERMISSION_READ_PHONE_STATE = 2 ;
    public static final int MY_PERMISSION_MODIFY_PHONE_STATE = 3 ;
    public static final int MY_PERMISSION_ACCESS_NETWORK_STATE = 4 ;
    public static final int MY_PERMISSION_CHANGE_NETWORK_STATE = 5 ;
    public static final int MY_PERMISSION_INTERNET = 6 ;
    public static final int MY_PERMISSION_READ_SMS = 7 ;
    public static final int MY_PERMISSION_RECEIVED_BOOT_COMPLETED = 8 ;
    public static final int MY_PERMISSION_WAKE_LOCK = 9 ;

    /**
     *Methode permettant d'afficher une boite de dialogue pour demander une permission
     *
     *@param activity Contexte d'execution de la requette
     *@param manifestPermission Permission declaree dans le manifest de l'application
     *@param myPermission Constante definie dans la classe pour chaque permission declaree
     */
    private static void requestPermission(Activity activity, String manifestPermission, int myPermission){

        if (ContextCompat.checkSelfPermission(activity,
                manifestPermission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    manifestPermission)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{manifestPermission},
                        myPermission);
            }
        }
    }


    public static void requestCallPhonePemission(Activity activity){
        requestPermission(activity, Manifest.permission.CALL_PHONE, MY_PERMISSION_CALL_PHONE);
    }

    public static void requestModifyPhoneStatePermission(Activity activity){
        requestPermission(activity, Manifest.permission.MODIFY_PHONE_STATE, MY_PERMISSION_MODIFY_PHONE_STATE);
    }

    public static void requestAccessNetworkStatePermission(Activity activity){
        requestPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE, MY_PERMISSION_ACCESS_NETWORK_STATE);
    }

    public static void requestChangeNetworkStatePermission(Activity activity){
        requestPermission(activity, Manifest.permission.CHANGE_NETWORK_STATE, MY_PERMISSION_CHANGE_NETWORK_STATE);
    }

    public static void requestReadPhoneStatePermission(Activity activity){
        requestPermission(activity, Manifest.permission.READ_PHONE_STATE, MY_PERMISSION_READ_PHONE_STATE);
    }

    public static void requestPermissionReadSms(Activity activity){
        requestPermission(activity, Manifest.permission.READ_SMS, MY_PERMISSION_READ_SMS);
    }

    public static void requestPermissionWakeLock(Activity activity){
        requestPermission(activity, Manifest.permission.WAKE_LOCK, MY_PERMISSION_WAKE_LOCK);
    }

    /* Bout methode a overrider dans les activite qui vont demander les permissions
    @Override
        public void onRequestPermissionsResult(int requestCode,
                String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request.
            }
        }
     */
}

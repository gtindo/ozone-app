package com.example.user.projetz.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

/**
 * Created by Marie Claire Mbanga on 03/02/2018.
 * this class may keep dynamics phone states information like
 * the operator's name(nomOperateur), the data activity(detaActivity),  the network type(typeReseau), the sim state(simState)
 */

public class Infos {
    private int typeReseau;
    private int dataActivity;
    private String nomOperateur;
    private int simState;

    public Infos(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.nomOperateur= telephonyManager.getNetworkOperatorName();
        this.dataActivity=telephonyManager.getDataActivity();
        this.typeReseau=telephonyManager.getNetworkType();
        this.simState=telephonyManager.getSimState();
    }

    public int getTypeReseau(){
        return typeReseau;
    }

    public int getDataActivity() {
        return dataActivity;
    }

    public int getSimState() {
        return simState;
    }

    public String getNomOperateur() {
        return nomOperateur;
    }

    public void setDataActivity(int dataActivity) {
        this.dataActivity = dataActivity;
    }

    public void setNomOperateur(String nomOperateur) {
        this.nomOperateur = nomOperateur;
    }

    public void setSimState(int simState) {
        this.simState = simState;
    }

    public void setTypeReseau(int typeReseau) {
        this.typeReseau = typeReseau;
    }
}

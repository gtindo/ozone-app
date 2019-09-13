package com.example.user.projetz.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.user.projetz.model.Souscription;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Param {
    private final String PARAM_FILE = "ozone_param.p" ;

    private int firstOpening ;
    private String operatorName ;
    private int vibratingNotification ;
    private int ringNotification ;
    private int lightNotification ;
    private Souscription activeSubscription ;
    private long data ;
    private String ozone_id ;
    private int ozone_id_set ;
    private long dataBeforeShutdown ;
    private int popupNotification ;
    private int notification50Pushed ;
    private int notification80Pushed ;
    private int notificatitonFinPushed ;

    public Param(Context context){
        Param p = readParam(context);

        if(p == null){
            this.firstOpening = 1 ;
            this.operatorName = "orange" ;
            this.vibratingNotification = 1 ;
            this.ringNotification = 1 ;
            this.lightNotification = 1 ;
            this.activeSubscription = null ;
            this.data = 0 ;
            this.ozone_id = UUID.randomUUID().toString();
            this.ozone_id_set = 0 ;
            this.dataBeforeShutdown = 0 ;
            this.popupNotification = 1 ;
            this.notification50Pushed = 0 ;
            this.notification80Pushed = 0 ;
            this.notificatitonFinPushed = 0 ;

            this.saveParam(context);
        }else{
            this.firstOpening = p.firstOpening ;
            this.operatorName = p.operatorName ;
            this.vibratingNotification = p.vibratingNotification;
            this.ringNotification = p.ringNotification ;
            this.lightNotification = p.lightNotification ;
            this.activeSubscription = p.activeSubscription ;
            this.data = p.data ;
            this.ozone_id = p.ozone_id ;
            this.ozone_id_set = p.ozone_id_set ;
            this.dataBeforeShutdown = p.dataBeforeShutdown ;
            this.popupNotification = p.popupNotification ;
            this.notification50Pushed = p.notification50Pushed ;
            this.notification80Pushed = p.notification80Pushed ;
            this.notificatitonFinPushed = p.notificatitonFinPushed ;
        }
    }

    private Param(String paramString){
        String values[] = paramString.split("/");
        this.firstOpening = Integer.parseInt(values[0]) ;
        this.operatorName = values[1];
        this.vibratingNotification = Integer.parseInt(values[2]);
        this.ringNotification = Integer.parseInt(values[3]);
        this.lightNotification = Integer.parseInt(values[4]);
        this.activeSubscription = (values[5].equals("0") ? null : new Souscription(values[5])); // si la valeur dans le fichier est 0 alors on retourne null
        this.data = Long.parseLong(values[6]);
        this.ozone_id = values[7];
        this.ozone_id_set = Integer.parseInt(values[8]);
        this.dataBeforeShutdown = Long.parseLong(values[9]);
        this.popupNotification = Integer.parseInt(values[10]);
        this.notification50Pushed = Integer.parseInt(values[11]);
        this.notification80Pushed = Integer.parseInt(values[12]);
        this.notificatitonFinPushed = Integer.parseInt(values[13]);
    }

    //methode pour enregistrer les parametres
    public void saveParam(Context context){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(PARAM_FILE, Context.MODE_PRIVATE);
            fos.write(this.toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // methode pour lire les parametres
    private Param readParam(Context context){
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(PARAM_FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine() ;
            fis.close();

            return new Param(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null ;
    }

    @Override
    public String toString(){
        return firstOpening+"/"+operatorName+"/"+vibratingNotification+"/"+ringNotification+"/"+lightNotification+"/"+
                (activeSubscription != null ? activeSubscription.toString() : 0)+"/" //si la souscription est nulle alors on ecrit 0
                +data+"/"+ozone_id+"/"+ozone_id_set+"/"+dataBeforeShutdown+"/"+popupNotification+"/"+notification50Pushed+"/"+notification80Pushed+"/"+notificatitonFinPushed;
    }

    public int getFirstOpening() {
        return firstOpening;
    }

    public void setFirstOpening(int firstOpening) {
        this.firstOpening = firstOpening;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getVibratingNotification() {
        return vibratingNotification;
    }

    public void setVibratingNotification(int vibratingNotification) {
        this.vibratingNotification = vibratingNotification;
    }

    public int getRingNotification() {
        return ringNotification;
    }

    public void setRingNotification(int ringNotification) {
        this.ringNotification = ringNotification;
    }

    public int getLightNotification() {
        return lightNotification;
    }

    public void setLightNotification(int lightNotification) {
        this.lightNotification = lightNotification;
    }

    public Souscription getActiveSubscription() {
        return activeSubscription;
    }

    public void setActiveSubscription(Souscription activeSubscription) {
        this.activeSubscription = activeSubscription;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getOzone_id() {
        return ozone_id;
    }

    public void setOzone_id(String ozone_id) {
        this.ozone_id = ozone_id;
    }

    public int getOzone_id_set() {
        return ozone_id_set;
    }

    public void setOzone_id_set(int ozone_id_set) {
        this.ozone_id_set = ozone_id_set;
    }

    public long getDataBeforeShutdown() {
        return dataBeforeShutdown;
    }

    public void setDataBeforeShutdown(long dataBeforeShutdown) {
        this.dataBeforeShutdown = dataBeforeShutdown;
    }

    public int getNotification50Pushed() {
        return notification50Pushed;
    }

    public void setNotification50Pushed(int notification50Pushed) {
        this.notification50Pushed = notification50Pushed;
    }

    public int getNotification80Pushed() {
        return notification80Pushed;
    }

    public void setNotification80Pushed(int notification80Pushed) {
        this.notification80Pushed = notification80Pushed;
    }

    public int getNotificatitonFinPushed() {
        return notificatitonFinPushed;
    }

    public void setNotificatitonFinPushed(int notificatitonFinPushed) {
        this.notificatitonFinPushed = notificatitonFinPushed;
    }

    public int getPopupNotification() {
        return popupNotification;
    }

    public void setPopupNotification(int popupNotification) {
        this.popupNotification = popupNotification;
    }
}

package com.example.user.projetz.model;

import android.content.Context;

import com.example.user.projetz.utils.Param;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Marie Claire Mbanga on 25/01/2018.
 * to initialize operator we've used classes Forfait;
 * !The volume of data!
 * in case of "Forfaits" with type="GigaData" we've only mentioned
 * the data volume of day.
 * for the other type of "forfait" we've mentioned all the data volume for
 * the long duration of the "forfait"
 */

public abstract class ListeForfaits {
    private static final String FORFAIT_JOUR_MTN = "forfait_jour_mtn.ft" ;
    private static final String FORFAIT_MOIS_MTN = "forfait_mois_mtn.ft" ;
    private static final String FORFAIT_SEMAINE_MTN = "forfait_semaine_mtn.ft" ;

    private static final String FORFAIT_JOUR_ORANGE = "forfait_jour_orange.ft" ;
    private static final String FORFAIT_MOIS_ORANGE = "forfait_mois_orange.ft" ;
    private static final String FORFAIT_SEMAINE_ORANGE = "forfait_semaine_orange.ft" ;

    private static final String FORFAIT_JOUR_NEXTTEL = "forfait_jour_nexttel.ft" ;
    private static final String FORFAIT_MOIS_NEXTTEL = "forfait_mois_nexttel.ft" ;
    private static final String FORFAIT_SEMAINE_NEXTTEL = "forfait_semaine_nexttel.ft" ;

    private static final String FORFAIT_JOUR_YOOMEE = "forfait_jour_yoomee.ft" ;
    private static final String FORFAIT_MOIS_YOOMEE = "forfait_mois_yoomee.ft" ;
    private static final String FORFAIT_SEMAINE_YOOMEE = "forfait_semaine_yoomee.ft" ;

    private static final String FORFAIT_JOUR_CAMTEL = "forfait_jour_camtel.ft" ;
    private static final String FORFAIT_MOIS_CAMTEL = "forfait_mois_camtel.ft" ;
    private static final String FORFAIT_SEMAINE_CAMTEL = "forfait_semaine_camtel.ft" ;

    public static void initForfaits(Context context){
        initorange(context);
        initmtn(context);
        initnexttel(context);
        inityoomee(context);
        initcamtel(context);
    }


    // this fonction convert in octet numbers in Mo
    private static long convertMOctect(double Mo){
        return (long) (1024 * 1024 * Mo);
    }
    // this fonction convert in octet numbers in Go
    private static long convertGOctect(double Go){
        return (long) (1024*1024*1024*Go);
    }

    private static void initorange(Context context){
        // remplissons la liste de forfait pour orange.cm
        Forfait jour5 = new Forfait("Découverte 102F", 1, "145*2*3*5",convertMOctect(80),1);
        Forfait jour4 = new Forfait("Jour 100F", 1, "145*2*3*4",convertMOctect(100),1);//a modifier
        Forfait jour3 = new Forfait("Jour 250F", 1, "145*2*3*3",convertMOctect(250),1);
        Forfait jour2 = new Forfait("Jour 500F", 1, "145*2*3*2",convertGOctect(1),1);
        Forfait jour1 = new Forfait("Jour 1000F", 1, "145*2*3*1",convertGOctect(2),1);
        String jourText = jour5.toString()+"/"+jour4.toString()+"/"+jour3.toString()+"/"+jour2.toString()+"/"+jour1.toString();
        write(context, jourText, FORFAIT_JOUR_ORANGE);

        Forfait three1 = new Forfait("Forfait 3Jrs 500F", 3, "145*2*2*1",convertMOctect(500),1);
        Forfait three2 = new Forfait("Forfait 3Jrs 300F", 3, "145*2*2*2",convertMOctect(350),1);
        Forfait giga35 = new Forfait("Giga data 3Jrs 1Go 1200F", 3, "145*2*4*4*5",convertGOctect(1),2);
        Forfait giga34 = new Forfait("Giga data 3Jrs 2Go 2550F", 3, "145*2*4*4*4",convertGOctect(2),2);
        Forfait giga33 = new Forfait("Giga data 3Jrs 3Go 4050F", 3, "145*2*4*4*3",convertGOctect(3),2);
        Forfait giga32 = new Forfait("Giga data 3Jrs 4Go 5400F", 3, "145*2*4*4*2",convertGOctect(4),2);
        Forfait giga31 = new Forfait("Giga data 3Jrs 5Go 6750F", 3, "145*2*4*4*1",convertGOctect(5),2);
        Forfait giga75 = new Forfait("Giga data 7Jrs 1Go 2650F", 7, "145*2*4*3*5",convertGOctect(1),2);
        Forfait giga74 = new Forfait("Giga data 7Jrs 2Go 5600F", 7, "145*2*4*3*4",convertGOctect(2),2);
        Forfait giga73 = new Forfait("Giga data 7Jrs 3Go 8950F", 7, "145*2*4*3*3",convertGOctect(3),2);
        Forfait giga72 = new Forfait("Giga data 7Jrs 4Go 12300F", 7, "145*2*4*3*2",convertGOctect(4),2);
        Forfait giga71 = new Forfait("Giga data 7Jrs 5Go 15400F", 7, "145*2*4*3*1",convertGOctect(5),2);
        Forfait giga155 = new Forfait("Giga data 15Jrs 1Go 5250F", 15, "145*2*4*2*5",convertGOctect(1),2);
        Forfait giga154 = new Forfait("Giga data 15Jrs 2Go 11250F", 15, "145*2*4*2*4",convertGOctect(2),2);
        Forfait giga153 = new Forfait("Giga data 15Jrs 3Go 18000F", 15, "145*2*4*2*3",convertGOctect(3),2);
        Forfait giga152 = new Forfait("Giga data 15Jrs 4Go 25800F", 15, "145*2*4*2*2",convertGOctect(4),2);
        Forfait giga151 = new Forfait("Giga data 15Jrs 5Go 32250F", 15, "145*2*4*2*1",convertGOctect(5),2);
        String semaineText = three1.toString()+"/"+three2.toString()+"/"+giga35.toString()+"/"+giga34.toString()+"/"+giga33.toString()+
                             giga32.toString()+"/"+giga31.toString()+"/"+giga75.toString()+"/"+giga74.toString()+"/"+giga73.toString()+
                             giga72.toString()+"/"+giga71.toString()+"/"+giga155.toString()+"/"+giga154.toString()+"/"+giga153.toString()+"/"+giga152.toString()+"/"+giga151.toString() ;
        write(context, semaineText, FORFAIT_SEMAINE_ORANGE);

        Forfait mois3 = new Forfait("Forfait 30Jrs 2000F", 30, "145*2*1*4",convertGOctect(1.2),1);
        Forfait mois4 = new Forfait("Forfait 30Jrs Night 2040F", 30, "145*2*1*5",convertGOctect(3),3);
        Forfait mois2 = new Forfait("Forfait 30Jrs 4000F", 30, "145*2*1*3",convertGOctect(2.5),1);
        Forfait mois1 = new Forfait("Forfait 30Jrs 8000F", 30, "145*2*1*2",convertGOctect(4.6),1);
        Forfait giga305 = new Forfait("Giga data30 1Go 10000F", 30, "145*2*4*1*5",convertGOctect(1),2);
        Forfait giga304 = new Forfait("Giga data30 2Go 21500F", 30, "145*2*4*1*4",convertGOctect(2),2);
        Forfait giga303 = new Forfait("Giga data30 3Go 34450F", 30, "145*2*4*1*3",convertGOctect(3),2);
        Forfait giga302 = new Forfait("Giga data30 4Go 50400F", 30, "145*2*4*1*2",convertGOctect(4),2);
        Forfait giga301 = new Forfait("Giga data30 5Go 63000F", 30, "145*2*4*1*1",convertGOctect(5),2);
        String moisText = mois3.toString()+"/"+mois4.toString()+"/"+mois2.toString()+"/"+mois1.toString()+"/"+giga305.toString()+"/"+
                          giga304.toString()+"/"+giga303.toString()+"/"+giga302.toString()+"/"+giga301.toString() ;
        write(context, moisText, FORFAIT_MOIS_ORANGE);
    }

    private static void initmtn(Context context) {
        Forfait jour3 = new Forfait("jour 100F", 1, "*157*1*3*1",convertMOctect(75),1);
        Forfait jour4 = new Forfait("jour 250F", 1, "*157*1*4*1",convertMOctect(200),1);
        Forfait jour5 = new Forfait("jour 500F", 1, "*157*1*5*1",convertMOctect(400),1);
        String jourText = jour3.toString()+"/"+jour4.toString()+"/"+jour5.toString();
        write(context, jourText, FORFAIT_JOUR_MTN);

        Forfait semaine1 = new Forfait("semaine 60F", 7, "*157*2*1*1",convertMOctect(15),1);
        Forfait semaine2 = new Forfait("semaine 180F", 7, "*157*2*2*1",convertMOctect(50),1);
        Forfait semaine3 = new Forfait("semaine 700F", 7, "*157*2*3*1",convertMOctect(200),1);
        Forfait semaine4 = new Forfait("semaine 1000F", 7, "*157*2*4*1",convertMOctect(500),1);
        Forfait semaine5 = new Forfait("semaine 3000F", 7, "*157*2*5*1",convertGOctect(2.5),1);
        String semaineText = semaine1.toString()+"/"+semaine2.toString()+"/"+semaine3.toString()+"/"+semaine4.toString()+"/"+semaine5.toString();
        write(context, semaineText, FORFAIT_SEMAINE_MTN);

        Forfait mois1 = new Forfait("mois 175F", 30, "*157*3*1*1",convertMOctect(150),1);
        Forfait mois2 = new Forfait("mois 850F", 30,"*157*3*2*1",convertMOctect(250),1);
        Forfait mois3 = new Forfait("mois 2000F", 30, "*157*3*3*1",convertMOctect(600),1);
        Forfait mois4 = new Forfait("mois 4000F", 30, "*157*3*4*1",convertGOctect(1.5),1);
        Forfait mois5 = new Forfait("mois 8000F", 30, "*157*3*5*1",convertGOctect(4.6),1);
        Forfait mois6 = new Forfait("GigaData mois 10000F", 30, "*157*3*6*1",convertGOctect(1),2);
        String moisText = mois1.toString()+"/"+mois2.toString()+"/"+mois3.toString()+"/"+mois4.toString()+"/"+mois5.toString()+"/"+mois6.toString();
        write(context, moisText, FORFAIT_MOIS_MTN);
    }

    private static void initnexttel(Context context) {
        Forfait h2 = new Forfait("Fly hour(2h) 50F", 1, "*865*20",convertMOctect(50),1);
        Forfait jour1 = new Forfait("Fly daily 1 150F", 1, "*865*21",convertMOctect(150),1);
        Forfait jour2 = new Forfait("Fly daily 2 250F", 1, "*865*22",convertMOctect(250),1);
        Forfait jour3 = new Forfait("Fly daily 3 500F", 1, "*865*23",convertMOctect(500),1);
        Forfait jour4 = new Forfait("Fly daily 4 100F", 1, "*865*24",convertMOctect(100),1);
        Forfait nuit1 = new Forfait("Fly night 1 150F", 1, "*865*11",convertMOctect(200),3);
        Forfait nuit2 = new Forfait("Fly night 2 250F", 1, "*865*12",convertMOctect(300),3);
        Forfait special = new Forfait(" Forfait special 1000F", 1, "*865*33",convertGOctect(10),3);
        String jourText = h2.toString()+"/"+jour1.toString()+"/"+jour2.toString()+"/"+jour3.toString()+"/"+jour4.toString()+"/"+nuit1.toString()+"/"+nuit2.toString()+"/"+special.toString();
        write(context, jourText, FORFAIT_JOUR_NEXTTEL);

        Forfait semaine1 = new Forfait("Fly weekly 2000F", 7, "*865*31",convertMOctect(1024),1);
        write(context, semaine1.toString(), FORFAIT_SEMAINE_NEXTTEL);

        Forfait mois1 = new Forfait("Fly monthly 1 8000F", 30, "*865*41",convertMOctect(4710),1);
        Forfait mois2 = new Forfait("Fly monthly 2 12000F", 30, "*865*42",convertMOctect(8192),1);
        Forfait mois3 = new Forfait("Fly monthly 3 25000F", 30, "*865*43",convertMOctect(18432),1);
        Forfait mois4 = new Forfait("Fly monthly 4 4000F", 30, "*865*44",convertMOctect(1536),1);
        String moisText = mois1.toString()+"/"+mois2.toString()+"/"+mois3.toString()+"/"+mois4.toString();
        write(context, moisText, FORFAIT_MOIS_NEXTTEL);
    }

    private static void inityoomee(Context context) {
        // all these ussd code are wrong
        Forfait jour1 = new Forfait("DAY 100F", 1, "*155*6050*1",convertMOctect(200),1);
        Forfait jour2 = new Forfait("DAY 250F", 1, "*155*6051*1",convertMOctect(500),1);
        Forfait jour3 = new Forfait("DAY 450F", 1, "*155*6050*1",convertGOctect(1),1);
        String jourText = jour1.toString()+"/"+jour2.toString()+"/"+jour3.toString();
        write(context, jourText, FORFAIT_JOUR_YOOMEE);

        Forfait three1 = new Forfait("3 DAY 1000F", 3, "*155*6050*1",convertGOctect(2.5),1);
        Forfait semaine1 = new Forfait("WEEK 5000F", 7, "*155*6050*1",convertGOctect(12),1);
        String semaineText = three1.toString()+"/"+semaine1.toString();
        write(context, semaineText, FORFAIT_SEMAINE_YOOMEE);

        Forfait mois1 = new Forfait("MONTH 10000F", 30, "*155*6150*1",convertGOctect(32),1);
        Forfait mois2 = new Forfait("MONTH 20000F", 30, "*155*6700*1",convertGOctect(75),1);
        Forfait mois3 = new Forfait("MONTH 30000F", 30, "*155*6050*1",convertGOctect(100),1);
        String moisText = mois1.toString()+"/"+mois2.toString()+"/"+mois3.toString() ;
        write(context, moisText, FORFAIT_MOIS_YOOMEE);
    }

    private static void initcamtel(Context context) {
        Forfait h4 = new Forfait("FAKO MINI 100F", 1, "*55*6100*1",convertMOctect(500),1);
        Forfait jour1 = new Forfait("FAKO DAY 250F", 1, "*155*6050*1",convertMOctect(100),1);
        Forfait jour2 = new Forfait("FAKO DAY PLUS 500F", 1, "*155*6051*1",convertGOctect(1),1);
        Forfait nuit1 = new Forfait("FAKO NIGHT 3000F", 1, "*155*6002*1",convertGOctect(8),3);
        Forfait nuit2 = new Forfait("FAKO NIGHT PLUS ILLIMITE 1000F", 1, "*155*6003*1",convertGOctect(50),3);
        String jourText = h4.toString()+"/"+jour1.toString()+"/"+jour2.toString()+"/"+nuit1.toString()+"/"+nuit2.toString();
        write(context, jourText, FORFAIT_JOUR_CAMTEL);

        Forfait mois1 = new Forfait("FAKO ACCESS 2000F", 30, "*155*6150*1",convertMOctect(500),1);
        Forfait mois2 = new Forfait("FAKO SMART 6000F", 30, "*155*6700*1",convertGOctect(2),1);
        Forfait mois3 = new Forfait("FAKO COMFORT 10000F", 30, "*155*6015*1",convertGOctect(5),1);
        Forfait mois4 = new Forfait("FAKO TOP 25000F", 30, "**155*6005*1",convertGOctect(18),1);
        Forfait mois5 = new Forfait("FAKO PRO 50000F", 30, "*157*2*5*1*1",convertGOctect(60),2);
        String moisText = mois1.toString()+"/"+mois2.toString()+"/"+mois3.toString()+"/"+mois4.toString()+"/"+mois5.toString();
        write(context, moisText, FORFAIT_MOIS_CAMTEL);
    }

    static void write(Context context, String text, String file_name){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Forfait> read(Context context, String file_name){
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(file_name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String chaine = bufferedReader.readLine() ;
            String[] tabForfaits = chaine.split("/");
            ArrayList<Forfait> listForfait = new ArrayList<Forfait>();
            fis.close();

            for(String forfait : tabForfaits){
                listForfait.add(new Forfait(forfait));
            }

            return listForfait ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static void fetchForfaitJourOrange(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://fierce-citadel-85111.herokuapp.com/forfaits/orangeCM/1", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    public static ArrayList<Forfait> getForfaitJourOrange(Context context){
        return read(context, FORFAIT_JOUR_ORANGE);
    }

    public static ArrayList<Forfait> getForfaitSemaineOrange(Context context){
        return read(context, FORFAIT_SEMAINE_ORANGE);
    }

    public static ArrayList<Forfait> getForfaitMoisOrange(Context context){
        return read(context, FORFAIT_MOIS_ORANGE);
    }

    public static ArrayList<Forfait> getForfaitJourMtn(Context context){
        return read(context, FORFAIT_JOUR_MTN);
    }

    public static ArrayList<Forfait> getForfaitSemaineMtn(Context context){
        return read(context, FORFAIT_SEMAINE_MTN);
    }

    public static ArrayList<Forfait> getForfaitMoisMtn(Context context){
        return read(context, FORFAIT_MOIS_MTN);
    }

    public static ArrayList<Forfait> getForfaitJourNexttel(Context context){
        return read(context, FORFAIT_JOUR_NEXTTEL);
    }

    public static ArrayList<Forfait> getForfaitSemaineNexttel(Context context){
        return read(context, FORFAIT_SEMAINE_NEXTTEL);
    }

    public static ArrayList<Forfait> getForfaitMoisNexttel(Context context){
        return read(context, FORFAIT_MOIS_NEXTTEL);
    }

    public static ArrayList<Forfait> getForfaitJourYoomee(Context context){
        return read(context, FORFAIT_JOUR_YOOMEE);
    }

    public static ArrayList<Forfait> getForfaitSemaineYoomee(Context context){
        return read(context, FORFAIT_SEMAINE_YOOMEE);
    }

    public static ArrayList<Forfait> getForfaitMoisYoomee(Context context){
        return read(context, FORFAIT_MOIS_YOOMEE);
    }

    public static ArrayList<Forfait> getForfaitJourCamtel(Context context){
        return read(context, FORFAIT_JOUR_CAMTEL);
    }

    public static ArrayList<Forfait> getForfaitMoisCamtel(Context context){
        return read(context, FORFAIT_MOIS_CAMTEL);
    }

    public static ArrayList<Forfait> getForfaitJourOperatorActiv(Context context){
       String i;
        ArrayList<Forfait> forfaits = new ArrayList<Forfait>();
        Param p = new Param(context);
        i=p.getOperatorName();
        if(i.equals("orange")) forfaits=getForfaitJourOrange(context);
        if(i.equals("mtn")) forfaits=getForfaitJourMtn(context);
        if(i.equals("nexttel")) forfaits=getForfaitJourNexttel(context);
        if(i.equals("camtel")) forfaits=getForfaitJourCamtel(context);
        if(i.equals("yoomee")) forfaits=getForfaitJourYoomee(context);
        return forfaits;
    }
    public static ArrayList<Forfait> getForfaitSemaineOperatorActiv(Context context){
        String i;
        ArrayList<Forfait> forfaits = new ArrayList<Forfait>();
        Param p = new Param(context);
        i=p.getOperatorName();
        if(i.equals("orange")) forfaits=getForfaitSemaineOrange(context);
        if(i.equals("mtn")) forfaits=getForfaitSemaineMtn(context);
        if(i.equals("nexttel")) forfaits=getForfaitSemaineNexttel(context);
        if(i.equals("yoomee")) forfaits=getForfaitSemaineYoomee(context);
        return forfaits;
    }
    public static ArrayList<Forfait> getForfaitMoisOperatorActiv(Context context){
        String i;
        ArrayList<Forfait> forfaits = new ArrayList<Forfait>();
        Param p = new Param(context);
        i=p.getOperatorName();
        if(i.equals("orange")) forfaits=getForfaitMoisOrange(context);
        if(i.equals("mtn")) forfaits=getForfaitMoisMtn(context);
        if(i.equals("nexttel")) forfaits=getForfaitMoisNexttel(context);
        if(i.equals("camtel")) forfaits=getForfaitMoisCamtel(context);
        if(i.equals("yoomee")) forfaits=getForfaitMoisYoomee(context);
        return forfaits;
    }

}

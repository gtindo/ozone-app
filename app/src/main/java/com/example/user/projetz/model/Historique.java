package com.example.user.projetz.model;

import android.app.Activity;
import android.content.Context;

import com.example.user.projetz.utils.Param;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Historique est la classe representant la site des forfaits qui ont ete souscrits
 */
public class Historique {
    /**
     * Liste des souscription
     */
    private ArrayList<Souscription> history= new ArrayList<Souscription>();

    /**
     * Constructeur de la classe Historique
     * @param context context dans lequel la methode est appelle
     */
    public Historique(Context context){
        history = readSouscriptions(context);
    }

    /**
     *Lis le fichier de souscription et recupere les dernieres souscritpion
     * @param context
     * @return
     */
    private ArrayList<Souscription> readSouscriptions(Context context){
        FileInputStream fis = null;
        ArrayList<Souscription> list = new ArrayList<Souscription>() ;
        try {
            fis = context.openFileInput(Souscription.SOUSCRIPTION_FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line = bufferedReader.readLine() ;
            String stringSouscription[] = line.split("/") ;
            for(String ch : stringSouscription){
                list.add(new Souscription(ch));
            }
            fis.close();

            return list ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null ;
    }

    public ArrayList<Souscription> getHistory(){
        return history;
    }

    public void setHistory(Souscription history, Context context) {
        this.history.add(history);
    }

}

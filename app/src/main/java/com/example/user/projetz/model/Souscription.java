package com.example.user.projetz.model;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.user.projetz.utils.Param;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID ;

/**
 * <b>Souscription est la classe reprétant la souscription à un forfait.</b>
 * <p>
 * Une Souscription est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un forfait, non modifiable.</li>
 * <li>Une "dateDebut", qui représente la date à laquelle la souscription a été effectuée.</li>
 * <li>Une "dateFin". Qui représente la date à laquelle la souscription expire, elle peut être modifiée en cas de cumulation de forfait</li>
 * <li>Un "id" qui est un identifiant unique non modifiable generee à l'aide de la classe UUID</li>
 * <li>Un "dataRestante" La quantite de data restante au cours de l'utilisation de la souscription</li>
 * </ul>
 * </p>
 * <p>
 *
 * @see Forfait
 *
 * @author GUEKENG TINDO YVAN, Marie Claire MBANGA
 */
public class Souscription implements Serializable{
    public static final String SOUSCRIPTION_FILE_NAME = "souscriptions.scp" ;

    /**
     * Forfait souscrit. On ne peut pas modifier le forfait une fois la souscription effectuée.
     *
     * @see Souscription#Souscription(String)
     * @see Souscription#getId()
     */
    private Forfait forfait ;

    /**
     * Date de fin de souscription. Cette date est modifiable en cas de cumulation de forfait.
     *
     * @see Souscription#Souscription(String)
     * @see Souscription#getDateFin()
     * @see Souscription#setDateFin(Date)
     */
    private Date dateFin ;

    /**
     * La date de la souscription. Cette date n'est pas modifiable.
     *
     * @see Souscription#Souscription(String)
     * @see Souscription#getDateDebut()
     */
    private Date dateDebut ;

    /**
     * L'id de la souscription. Cet id n'est pas modifiable.
     *
     * @see Souscription#Souscription(String)
     * @see Souscription#getId()
     */
    private String id ;

    /**
     * Quantite de data restante a la souscription
     */
    private long dataRestante ;


    /**
     * Constructeur d'une Souscription a partir d'un forfait.
     *
     * @param forfait forfait souscrit par l'utilisateur.
     */
    public Souscription(Forfait forfait){
        //duree de la souscription en milisecondes
        int dureeSouscription = forfait.getNbJours() * 24 * 60 * 60 * 1000 ;

        this.forfait = forfait ;
        this.dateDebut = new Date() ;
        long fin = dateDebut.getTime() + dureeSouscription ;
        this.dateFin = new Date(fin) ;

        if(forfait.getNbJours() == 30){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            this.dateFin = cal.getTime() ;
        }

        this.id = UUID.randomUUID().toString() ;

        //this.dataRestante = forfait.getType().equals("gigadata") ? forfait.getNbOctets()/forfait.getNbJours() : forfait.getNbOctets() ;
        this.dataRestante = forfait.getNbOctets() ;
    }

    /**
     * Constructeur d'une Souscription a partir d'une chaine de carateres.
     *
     * @param chaineConcat Chaine de caractere sur le modele du forfait:dateDebut:dateFin:id:dataRestante.
     */
    public Souscription(String chaineConcat){
        String chaine[] = chaineConcat.split(":");
        this.forfait = new Forfait(chaine[0]);
        this.dateDebut = new Date(Long.parseLong(chaine[1]));
        this.dateFin = new Date(Long.parseLong(chaine[2]));
        this.id = chaine[3];
        this.dataRestante = Long.parseLong(chaine[4]);
    }

    /**
     * Retourne une description de la souscription
     *
     * @return La souscription sous la forme d'une chaine de caracterès
     */
    public String toString()
    {
        return forfait.toString()+":"+dateDebut.getTime()+":"+dateFin.getTime()+":"+id+":"+dataRestante ;
    }

    /**
     * Sauvegarde la souscription dans un fichier
     *
     * @param activity Context dans lequel la méthode va être exécutée
     */
    public void sauvegarderSouscription(Activity activity){
        FileOutputStream fos = null;
        String oldContent = Souscription.getSouscriptions(activity);

        if(oldContent == null){
            oldContent = "" ;
        }

        try {
            fos = activity.openFileOutput(SOUSCRIPTION_FILE_NAME, Context.MODE_PRIVATE);
            fos.write((oldContent+this.toString()+"/").getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de recuperer la liste des souscrition depuis le fichier de souscriptions
     * @param context
     * @return
     */
    private static String getSouscriptions(Context context){
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(SOUSCRIPTION_FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String chaine = "" ;

            chaine = bufferedReader.readLine() ;
            fis.close();
            return chaine ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }


    public Forfait getForfait() {
        return forfait;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public String getId() {
        return id;
    }

    public long getDataRestante() {
        return dataRestante;
    }

    public void setDataRestante(long dataRestante) {
        this.dataRestante = dataRestante;
    }
}

package com.example.user.projetz.model;

import com.example.user.projetz.R;

import java.io.Serializable;

/**
 * <b>Forfait est la classe reprétant l'offre internet d'un operateur.</b>
 * <p>
 * Un Forfait est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un nom</li>
 * <li>Un nombre de jours</li>
 * <li>Un code USSD</li>
 * <li>Un nombre d'octets</li>
 * </ul>
 * </p>
 *
 * @author GUEKENG TINDO YVAN, Marie Claire Mbanga
 */
public class Forfait implements Serializable{
    /**
     * Nom du forfait
     *
     * @see Forfait#Forfait(String)
     * @see Forfait#getNom()
     * @see Forfait#setNom(String)
     */
    private String nom ;

    /**
     * Nombre de jours pour lesquels le forfait est valide
     *
     * @see Forfait#Forfait(String)
     * @see Forfait#getNbJours()
     * @see Forfait#setNbJours(int)
     */
    private int nbJours ;

    /**
     * Code USSD pour necessaire pour souscrire au forfait
     *
     * @see Forfait#Forfait(String)
     * @see Forfait#getCodeUssd()
     * @see Forfait#setCodeUssd(String)
     */
    private String codeUssd ;

    /**
     * Nombre d'octets mis a disposition de l'utilisateur suite a la souscription au forfait
     *
     * @see Forfait#Forfait(String)
     * @see Forfait#getNbOctets()
     * @see Forfait#setNbOctets(long)
     */
    private long nbOctets ;

    private String type;


    public Forfait(String nom, int nbJours, String codeUssd, long nbOctets, int n){
        this.nom = nom ;
        this.nbJours = nbJours ;
        this.codeUssd = codeUssd ;
        this.nbOctets = nbOctets ;
        this.type=typeforfait(n);
    }

    /**
     * Constructeur de la classe forfait prenant en parametre une chaine de caractere au format nom;nbJours;codeUssd;nbOctets
     *
     * @param chaineConcat chaine au format nom;nbJours;codeUssd;nbOctets
     */
    public Forfait(String chaineConcat){
        //chaineConcat : nom;nbJours;codeUssd;nbOctets

        String[] chaine = chaineConcat.split(";");
        this.nom = chaine[0] ;
        this.nbJours = Integer.parseInt(chaine[1]);
        this.codeUssd = chaine[2];
        this.nbOctets = Long.parseLong(chaine[3]);
        this.type = chaine[4];
    }

    /**
     * typeforfait est une fonction qui retourne le type de forfait
     * elle prend en parametre un nombre n qui est considéré comme identifiant de type
     * par defaut(si en instaciant la classe forfait l'on entre une valeur differente des identifiants de type connus 1,2,3)
     * le forfait est de type standard
     *
     * type 1 : standard
     * type 2 : gigadata
     * type 3 : night
     * type 4 : dayNight
     */
    private  String typeforfait(int n){
        switch (n){
            case 1 :
                return "standard" ;
            case 2 :
                return "gigadata" ;
            case 3 :
                return "night" ;
            case 4 :
                return "dayNight" ;
            default :
                return "standard" ;
        }
    }

    @Override
    public String toString(){
        return nom+";"+nbJours+";"+codeUssd+";"+nbOctets+";"+type ;
    }

    public Forfait(){
        this(null, 0, null, (long) 0,0);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    public String getCodeUssd() {
        return codeUssd;
    }

    public void setCodeUssd(String codeUssd) {
        this.codeUssd = codeUssd;
    }

    public long getNbOctets() {
        return nbOctets;
    }

    public void setNbOctets(long nbOctets) {
        this.nbOctets = nbOctets;
    }

    public void setType (int n) {this.type=typeforfait(n);}

    public String getType() {return type;}

    /**
     * Permet de recuperer l'id l'image associee a une instance de la classe forfait
     * @return id de la ressource
     */
    public int getImage(){
        int img = 0;
        Long id=this.getNbOctets();
        if(convertGO(id)==1.5)img= R.drawable.a;
        if(convertGO(id)==1)img=R.drawable.b;
        if(convertGO(id)==2.5)img=R.drawable.c;
        if(convertGO(id)==2)img=R.drawable.d;
        if(convertGO(id)==3)img= R.drawable.e;
        if(convertGO(id)==4.5)img=R.drawable.f;
        if(convertGO(id)==4.6)img= R.drawable.g;
        if(convertGO(id)==4)img=R.drawable.h;
        if(convertGO(id)==5)img=R.drawable.i;
        if(convertGO(id)==8)img=R.drawable.j;
        if(convertGO(id)==10)img=R.drawable.k;
        if(convertMO(id)==10)img= R.drawable.l;
        if(convertGO(id)==12)img=R.drawable.m;
        if(convertMO(id)==15)img=R.drawable.n;
        if(convertGO(id)==18)img=R.drawable.o;
        if(convertGO(id)==32)img= R.drawable.p;
        if(convertMO(id)==45)img=R.drawable.q;
        if(convertGO(id)==50)img= R.drawable.r;
        if(convertGO(id)==60)img=R.drawable.s;
        if(convertGO(id)==75)img=R.drawable.t;
        if(convertMO(id)==75)img=R.drawable.u;
        if(convertMO(id)==80)img=R.drawable.v;
        if(convertMO(id)==100)img=R.drawable.w;
        if(convertMO(id)==150)img=R.drawable.x;
        if(convertMO(id)==200)img=R.drawable.y;
        if(convertMO(id)==250)img=R.drawable.z;
        if(convertMO(id)==300)img= R.drawable.aa;
        if(convertMO(id)==350)img=R.drawable.bb;
        if(convertMO(id)==400)img=R.drawable.cc;
        if(convertMO(id)==450)img=R.drawable.dd;
        if(convertMO(id)==500)img=R.drawable.ee;
        if(convertMO(id)==600)img=R.drawable.ff;

        return img;
    }

    /**
     * Permet de convertir un entier long en gigaoctet
     * @param a entier a convertir
     * @return le resutltat de la conversion
     */
    private  double convertGO(long a){
        double i= a/1024/1024/1024;
        return i;
    }

    /**
     * Permet de convertir un entier long en megaoctet
     * @param a entier a convertir
     * @return le resultat de la conversion
     */
    private double convertMO(long a){
        double i= a/1024/1024;
        return i;
    }
}

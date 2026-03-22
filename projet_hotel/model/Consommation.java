package model;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Consommation {

    /**
     * Default constructor
     */
    public Consommation() {
    }

    /**
     * 
     */
    private int quantite_conso;

    /**
     * 
     */
    private Vector<Produit> listProduit = new Vector<Produit>();

    Reservation reservation;

    public  Consommation(int quantite_conso, Reservation re){
        this.quantite_conso = quantite_conso;
        this.reservation = re;
        re.addConsommation(this);
    }

    public void setQuantite(int quantite_conso){
        this.quantite_conso = quantite_conso;
    }
    public int getQuantite(){
        return this.quantite_conso;
    }
    
    public void addProduit(Produit produit){
        this.listProduit.add(produit);
    }
    public void removeProduit(Produit produit){
       this.listProduit.remove(produit);
    }
    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }
    public Reservation getReservation(){
        return this.reservation;
    }

    public Vector<Produit> getListProduit(){
        return this.listProduit;
    }

}
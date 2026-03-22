package model;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Receptionniste extends Personnel {

    /**
     * Default constructor
     */
    public Receptionniste() {
    }

    private Vector<Reservation> listReservation = new Vector<Reservation>();

    public Receptionniste(String nom, String prenom, int num_badge,Hotel h){
        super(nom, prenom, num_badge,h);
    }
    
    public void addReservation(Reservation reservation){
        this.listReservation.add(reservation);
    }
    
    public void removeReservation(Reservation reservation){
        this.listReservation.remove(reservation);
    }
    public List<Reservation> getListReservation(){
        return this.listReservation;
    }
}
package model;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Personnel_de_Maintenance extends Personnel {

    /**
     * Default constructor
     */
    public Personnel_de_Maintenance() {
    }

    /**
     * 
     */
    private Vector<Intervention> listIntervention = new Vector<Intervention>();

    public Personnel_de_Maintenance(String nom, String prenom, int num_badge, Hotel h){
        super(nom, prenom,num_badge,h);
    }

    public void addIntervention(Intervention intervention){
        listIntervention.add(intervention);
    }
    public void removeIntervention(Intervention intervention){
        listIntervention.remove(intervention);
    }
    public List<Intervention> getListIntervention(){
        return this.listIntervention;
    }
}
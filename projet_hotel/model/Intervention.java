package model;
import java.util.*;

/**
 * 
 */
public class Intervention {

    /**
     * Default constructor
     */
    public Intervention() {
    }

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private Vector<Chambre> listChambre = new Vector<Chambre>();

    /**
     * 
     */
    Personnel_de_Maintenance personnel_de_Maintenance;

    public Intervention(Date date,Vector<Chambre> ch, Personnel_de_Maintenance pdm){
        this.date = date;
        this.personnel_de_Maintenance = pdm;
        this.listChambre = ch;
        pdm.addIntervention(this);
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }    
    
    public void addChambre(Chambre chambre){
        listChambre.add(chambre);
    }
    public void removeChambre(Chambre chambre){
        listChambre.remove(chambre);
    }

    public void setPersonnel_de_Maintenance(Personnel_de_Maintenance personnel_de_Maintenance){
        this.personnel_de_Maintenance = personnel_de_Maintenance;
    }
    public Personnel_de_Maintenance getPersonnel(){
        return this.personnel_de_Maintenance;
    }
    public List<Chambre> getListChambre(){
        return this.listChambre;
    }
}
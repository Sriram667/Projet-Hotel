package model;

import java.util.List;
import java.util.Vector;

/**
 * 
 */
public class Chambre {

    /**
     * Default constructor
     */
    public Chambre() {
    }
    
    /**
     * 
     */
    private int numero;

    /**
     * 
     */
    private int etage;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private int prix;

    /**
     * 
     */
    private String statut;

    /**
     * 
     */
    private Vector<Intervention> listIntervention = new Vector<Intervention>();

    /**
     * 
     */
    private Vector<Reservation> listReservation = new Vector<Reservation>();
    /**
     * 
     */
    Hotel hotel;

    /**
     * Default constructor
     */
    public Chambre(int numero, int etage, String type, int prix, String statut, Hotel h) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
        this.prix = prix;
        this.statut = statut;
        this.hotel = h;
        hotel.addChambre(this);
    }

     public void setEtage(int etage){
        this.etage = etage;
    }

    public void setPrix(int prix){
        this.prix = prix;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setStatut(String statut){
        this.statut = statut;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public int getEtage(){
        return this.etage;
    }

    public int getPrix(){
        return this.prix;
    }

    public int getNumero(){
        return this.numero;
    }

    public String getType(){
        return this.type;
    }

    public String getStatut(){
        return this.statut;
    }

    public Hotel getHotel(){
        return this.hotel;
    }

    public void addIntervention(Intervention intervention){
        this.listIntervention.add(intervention);
    }

    public void removeIntervention(Intervention intervention){
        this.listIntervention.remove(intervention);
    }

    public List<Intervention> getListIntervention(){
        return this.listIntervention;
    }

    public void addReservation(Reservation reservation){
        if (!listReservation.contains(reservation)) {
            listReservation.add(reservation);
    }
    }
    public void removeReservation(Reservation reservation){
        this.listReservation.remove(reservation);
    }
    public List<Reservation> getListReservation(){
        return this.listReservation;
    }

    public Reservation getDerniereReservation() {
        if (!this.listReservation.isEmpty()) {
            return this.listReservation.lastElement();
        }
        return null;
    }


    /**
     * Change cycliquement le statut : libre  occupe  maintenance  libre
     */
    public void changeStatut(){
        if(this.statut.equals("libre")){
            this.statut = "occupe";
        } else if(this.statut.equals("occupe")){
            this.statut = "fin";
        } else if(this.statut.equals("fin")){
            this.statut = "maintenance";
        }else if(this.statut.equals("maintenance")){
            this.statut = "libre";
        }
    }

    /**
     * Retourne true si la chambre est disponible (statut = "libre")
     */
    public boolean isDisponible(){
        return this.statut.equalsIgnoreCase("libre");
    }

    /**
     * Libère la chambre (change son statut à "libre")
     */
    public void liberer(){
        this.statut = "libre";
    }

}
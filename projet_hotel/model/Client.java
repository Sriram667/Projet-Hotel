package model;

import java.util.List;
import java.util.Vector;

/**
 * 
 */
public class Client {

    /**
     * Default constructor
     */
    public Client() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String prenom;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private String email;

        /**
     * 
     */
    private Vector<Reservation> listReservation = new Vector<Reservation>();

    /**
     * 
     */
    private Vector<AvisClient> listAvisClient = new Vector<AvisClient>();

    /**
     * 
     */
    Hotel hotel;

    public Client(String nom, String prenom, String telephone, String email, Hotel h) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.hotel = h;
        h.addClient(this);
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return this.nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setHotel(Hotel h) {
        this.hotel = h;
    }
    public Hotel getHotel() {
        return this.hotel;
    }

    public String toString() {
        return "Nom: " + nom + "\nPrenom: " + prenom + "\nTelephone: " + telephone + "\nEmail: " + email;
    }
    public void addReservation(Reservation reservation) {
        if (!this.listReservation.contains(reservation)) {
            this.listReservation.add(reservation);
            reservation.setClient(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        this.listReservation.remove(reservation);
    }   

    public void addAvis(AvisClient avisClient) {
        this.listAvisClient.add(avisClient);
    }
    public void removeAvis(AvisClient avisClient) {
        this.listAvisClient.remove(avisClient);
    }
    
    public List<Reservation> getReservation() {
        return this.listReservation;
    }
    
    public List<AvisClient> getListAvis() {
        return this.listAvisClient;
    }
}
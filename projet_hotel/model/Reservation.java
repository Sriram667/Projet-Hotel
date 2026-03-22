package model;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation() {
    }

    /**
     * 
     */
    private int num_reservation;

    /**
     * 
     */
    private Date date_debut;

    /**
     * 
     */
    private Date date_fin;

    /**
     * 
     */
    private String statut;

    /**
     * 
     */
    private Vector<Chambre> listChambre = new Vector<Chambre>();

    /**
     * 
     */
    private Vector<Consommation> listConsommation = new Vector<Consommation>();

    /**
     * 
     */
    Receptionniste receptionniste;

    /**
     * 
     */
    Client client;

    public Reservation(int num_reservation, Date date_debut, Date date_fin, String statut,Receptionniste receptionniste, Client client) {
        this.num_reservation = num_reservation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut = statut;
        receptionniste.addReservation(this);
        client.addReservation(this);
    }

    public void setNumReservation(int num) {
        this.num_reservation = num;
    }
    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }
    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public int getNumReservation() {
        return this.num_reservation;
    }
    public Date getDateDebut() {
        return this.date_debut;
    }
    public Date getDateFin() {
        return this.date_fin;
    }
    public String getStatut() {
        return this.statut;
    }

    public void setReceptionniste(Receptionniste receptionniste) {
        this.receptionniste = receptionniste;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Receptionniste getReceptionniste(){
        return this.receptionniste;
    }

    public Client getClient(){
        return this.client;
    }

    public void addChambre(Chambre chambre) {
        this.listChambre.add(chambre);
        chambre.addReservation(this);
    }
    public void removeChambre(Chambre chambre) {
        this.listChambre.remove(chambre);
    }

    public void addConsommation(Consommation consommation) {
        this.listConsommation.add(consommation);
    }
    public void removeConsommation(Consommation consommation) {
        this.listConsommation.remove(consommation);
    }

    public List<Chambre> getListChambre() {
        return this.listChambre;
    }
    public List<Consommation> getListConsommation() {
        return this.listConsommation;
    }

    public boolean peutProlonger(Date dateProlongation) {
        // Vérifier si la nouvelle date de prolongation ne chevauche aucune réservation future.
        for (Chambre chambre : this.listChambre) { 
            for (Reservation r : chambre.getListReservation()) { 
                if (r.getDateDebut().before(dateProlongation) && r.getDateFin().after(dateProlongation)) {
                    // Si la date de prolongation chevauche une réservation future, on ne peut pas prolonger.
                    return false;
                }
            }
        }
        return true;
    }

    public int getDuree() {
        long diff = this.date_fin.getTime() - this.date_debut.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public String toString() {
        return "Reservation :" + this.num_reservation + "\n" +
                "Date de debut : " + this.date_debut + "\n" +
                "Date de fin : " + this.date_fin + "\n" +
                "Statut : " + this.statut + "\n" +
                "Receptionniste : " + (receptionniste != null ? receptionniste.getNom() : "inconnu")+ "\n" +
                "Client : " + this.client.getNom() + "\n";
    }
}
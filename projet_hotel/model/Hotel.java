package model;
import java.util.*;

/**
 * 
 */
public class Hotel {

    /**
     * Default constructor
     */
    public Hotel() {
    }

    /**
     * 
     */
    private String nom_hotel;

    /**
     * 
     */
    private float note;

/**
     * 
     */
    private Vector<Chambre> listChambre = new Vector<Chambre>();

    /**
     * 
     */
    private Vector<Personnel> listPersonnel = new Vector<Personnel>();

    /**
     * 
     */
    private Vector<Client> listClient = new Vector<Client>();    

    /**
     * 
     */
    private Vector<Produit> listProduit = new Vector<Produit>();

    public Hotel(String nom_hotel, float note) {
        this.nom_hotel = nom_hotel;
        this.note = note;
    }

    public String getNom() {
        return nom_hotel;
    }

    public void setNom(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Vector<Chambre> getListChambre() {
        return listChambre;
    }
    public Vector<Personnel> getListPersonnel() {
        return listPersonnel;
    }
    public Vector<Client> getListClient() {
        return listClient;
    }
    public Vector<Produit> getListProduit() {
        return listProduit;
    }
    public void addChambre(Chambre chambre) {
        listChambre.add(chambre);
    }
    public void addPersonnel(Personnel personnel) {
        listPersonnel.add(personnel);
    }
    public void addClient(Client client) {
        listClient.add(client);
    }
    public void addProduit(Produit produit) {
        listProduit.add(produit);
    }

    public void removeChambre(Chambre chambre) {
        listChambre.remove(chambre);
    }
    public void removePersonnel(Personnel personnel) {
        listPersonnel.remove(personnel);
    }
    public void removeClient(Client client) {
        listClient.remove(client);
    }
    public void removeProduit(Produit produit) {
        listProduit.remove(produit);
    }

    /**
     * Retourne le nombre de chambres disponibles pour un type donné
     */
    public int nbChambresDisponibles(String typeChambre) {
        int count = 0;
        for (Chambre chambre : listChambre) {
            if (chambre.getType().equalsIgnoreCase(typeChambre) && chambre.isDisponible()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retourne la première chambre disponible d’un type donné
     */
    public Chambre getChambreDisponible(String typeChambre) {
        for (Chambre chambre : listChambre) {
            if (chambre.getType().equalsIgnoreCase(typeChambre) && chambre.isDisponible()) {
                return chambre;
            }
        }
        return null;
    }
    public float getNoteMoyenne() {
        int total = 0;
        int count = 0;

        for (Client client : listClient) {
            for (AvisClient avis : client.getListAvis()) {
                total += avis.getNote();
                count++;
            }
        }

        if (count == 0) {
            this.note = 0;
        } else {
            this.note = Math.round((float) total / count * 10) / 10.0f; // moyenne arrondie à 1 décimale
        }

        return this.note;
    
    }
}
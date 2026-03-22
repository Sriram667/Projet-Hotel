package model;

/**
 * 
 */
public class Personnel {

    /**
     * Default constructor
     */
    public Personnel() {
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
    private int num_badge;

    /**
     * 
     */
    Hotel hotel;

    public Personnel(String nom, String prenom, int num_badge, Hotel h) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_badge = num_badge;
        this.hotel = h;
        h.addPersonnel(this);
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setNumBadge(int num_badge) {
        this.num_badge = num_badge;
    }
    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public int getNumBadge() {
        return this.num_badge;
    }
    public Hotel getHotel() {
        return this.hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    

}
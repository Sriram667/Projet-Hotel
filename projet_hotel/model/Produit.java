package model;

/**
 * 
 */
public class Produit {

    /**
     * Default constructor
     */
    public Produit() {
    }

    /**
     * 
     */
    private String nom_produit;

    /**
     * 
     */
    private int prix;

    /**
     * 
     */
    Hotel hotel;

    /**
     * 
     */
    Consommation consommation;

    public Produit(String nom_produit, int prix, Hotel h, Consommation c) {
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.hotel = h;
        h.addProduit(this);
        c.addProduit(this);
    }

    public void setNom_produit(String nom_produit){
        this.nom_produit = nom_produit;
    }
    public void setPrix(int prix){
        this.prix = prix;
    }
    public String getNom_produit(){
        return this.nom_produit;
    }
    public int getPrix(){
        return this.prix;
    }

    public void setConsommation(Consommation consommation){
        this.consommation = consommation;
    }

    public Consommation getConsommation(){
        return this.consommation;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public Hotel getHotel(){
        return this.hotel;
    }


}
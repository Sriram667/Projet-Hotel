package model;
import java.util.*;

/**
 * 
 */
public class AvisClient {

    /**
     * Default constructor
     */
    public AvisClient() {
    }
    /**
     * 
     */
    private int note;

    /**
     * 
     */
    private Date date_avis;

    Client client;

    /**
     * Default constructor
     */
    public AvisClient(int note, Date date_avis, Client client) {
        this.note = note;
        this.date_avis = date_avis;
        client.addAvis(this);
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }

    public int getNote() {
        return note;
    }
    public void setNote(int note) {
        this.note = note;
    }

    public Date getDate_avis() {
        return date_avis;
    }
    public void setDate_avis(Date date_avis) {
        this.date_avis = date_avis;
    }



    

}
package controleur;

import model.*;
import view.ConsommationView;
import view.FactureView;

import javax.swing.*;
import java.util.Vector;

public class CConsommation {

    public CConsommation(FactureView factureView, Reservation reservation, Vector<Produit> produitsDispo) {

        new ConsommationView(factureView, produitsDispo, produitAjoute -> {

            Consommation conso;
            if (reservation.getListConsommation().isEmpty()) {// si pas de conso
                conso = new Consommation(0, reservation);
                reservation.addConsommation(conso);
            } else {// si conso déjà existante
                conso = reservation.getListConsommation().get(0);
            }

            conso.addProduit(produitAjoute);

            factureView.ajouterConsommationDansVue(produitAjoute);

            JOptionPane.showMessageDialog(factureView,
                    produitAjoute.getNom_produit() + " ajouté à la facture.");
        });
    }
}

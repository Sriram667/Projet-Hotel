package controleur;

import java.util.Vector;
import javax.swing.*;
import model.*;
import view.*;

public class CFacture {

    public CFacture(FactureView vue, Reservation reservation, Vector<Produit> produitsDispo, BienvenueView accueil) {

        vue.getBtnConsommation().addActionListener(e -> {
            new CConsommation(vue, reservation, produitsDispo);
        });

        vue.getBtnValider().addActionListener(e -> {
            
            reservation.setStatut("passe"); 

            double total = vue.getTotal();
            String note = vue.getNoteTexte();

            new RecapFactureView(reservation, total, note);
            });

        vue.getBtnFinir().addActionListener(e -> {
            String noteTexte = vue.getNoteTexte().trim();

            if (!noteTexte.isEmpty()) {
                try {
                    int note = Integer.parseInt(noteTexte);
                    if (note >= 0 && note <= 5) {
                        new AvisClient(note, new java.util.Date(), vue.getClient());
                        accueil.updateNote(); // mise à jour dynamique de BienvenueView
                    } else {
                        JOptionPane.showMessageDialog(vue, "Note entre 0 et 5 seulement.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vue, "Note invalide. Entrez un chiffre.");
                    return;
                }
            }

            for (Chambre ch : reservation.getListChambre()) {
                ch.changeStatut();
            }

            vue.dispose();
        });
    }
}

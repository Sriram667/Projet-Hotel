package controleur;

import java.awt.event.*;
import javax.swing.*;
import model.*;
import view.*;

public class CCreation implements ActionListener {

    private CreationView view;
    private Hotel hotel;

    public CCreation(CreationView view, Hotel hotel) {
        this.view = view;
        this.hotel = hotel;

        this.view.btnValider.addActionListener(this);
        this.view.btnFermer.addActionListener(e -> view.dispose());
        this.view.btnRetour.addActionListener(e -> {
            view.dispose();
            new BienvenueView(hotel).setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = view.tfNom.getText().trim();
        String prenom = view.tfPrenom.getText().trim();
        String badgeStr = view.tfNumBadge.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || badgeStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Tous les champs doivent être remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numBadge;
        try {
            numBadge = Integer.parseInt(badgeStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Le numéro de badge doit être un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!view.rbReceptionniste.isSelected() && !view.rbMaintenance.isSelected()) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un type de personnel", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Personnel nouveauPersonnel;
        if (view.rbReceptionniste.isSelected()) {
            nouveauPersonnel = new Receptionniste(nom, prenom, numBadge, hotel);
        } else {
            nouveauPersonnel = new Personnel_de_Maintenance(nom, prenom, numBadge, hotel);
        }

        JOptionPane.showMessageDialog(view, "Personnel créé avec succès : " + nom + " " + prenom);
        view.dispose();
        new BienvenueView(hotel).setVisible(true);
    }
}

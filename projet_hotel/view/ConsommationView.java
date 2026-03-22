package view;

import model.Produit;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.function.Consumer;

public class ConsommationView extends JDialog {

    public ConsommationView(Component parent, Vector<Produit> produitsDispo, Consumer<Produit> onProduitSelected) {
        super((JFrame) null, "Ajout Consommation", true); // simplifié

        setLayout(new GridLayout(produitsDispo.size(), 1));

        for (Produit p : produitsDispo) {
            JButton btn = new JButton(p.getNom_produit() + " - " + p.getPrix() + " €");
            btn.addActionListener(e -> {
                onProduitSelected.accept(p); //ajouter le produit à la consommation
                dispose();
            });
            add(btn);
        }

        setSize(300, produitsDispo.size() * 50);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}

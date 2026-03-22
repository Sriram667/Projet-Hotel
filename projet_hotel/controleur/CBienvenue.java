package controleur;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import model.*;
import view.*;

public class CBienvenue implements ActionListener {

    private JTextField idField;
    private BienvenueView bienvenueView;
    private List<Personnel> persos;
    private JButton btnValider, btnAide;
    private Hotel hotel;

    public CBienvenue(JTextField idField, BienvenueView bienvenueView, List<Personnel> persos, JButton btnValider,
            JButton btnAide, Hotel hotel) {
        this.idField = idField;
        this.bienvenueView = bienvenueView;
        this.persos = persos;
        this.btnValider = btnValider;
        this.btnAide = btnAide;
        this.hotel = hotel;

        this.btnValider.addActionListener(this);
        this.btnAide.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnValider) {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                show("Veuillez entrer un numéro de badge");
                return;
            }

            if (id.equals("0")) {
                CreationView creationView = new CreationView();
                new CCreation(creationView, hotel);
                creationView.setVisible(true);
                bienvenueView.dispose();
                return;
            }

            for (Personnel p : persos) {
                if (String.valueOf(p.getNumBadge()).equals(id)) {
                    ouvrirInterfacePour(p);
                    bienvenueView.dispose();
                    return;
                }
            }

            show("Badge inconnu");
        }

        if (src == btnAide) {
            new AideView().setVisible(true);
        }
    }

    private void ouvrirInterfacePour(Personnel p) {
        if (p instanceof Receptionniste r) {
            CheckIn v = new CheckIn(hotel, r);
            new CCheckIn(v, hotel, r, bienvenueView);
            v.setVisible(true);
        } else if (p instanceof Personnel_de_Maintenance m) {
            new CMaintenance(hotel, m); // le contrôleur crée la vue
        } else {
            show("Rôle non reconnu");
        }
    }

    private void show(String msg) {
        JOptionPane.showMessageDialog(bienvenueView, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

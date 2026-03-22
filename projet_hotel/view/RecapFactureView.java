package view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import model.*;

public class RecapFactureView extends JFrame {

    public RecapFactureView(Reservation reservation, double total, String note) {
        setTitle("Récapitulatif de la facture");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//impose un format

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.add(new JLabel("Client : " + reservation.getClient().getNom()));
        infoPanel.add(new JLabel("Numéro de réservation : " + reservation.getNumReservation()));
        infoPanel.add(new JLabel("Date de début : " + sdf.format(reservation.getDateDebut())));
        infoPanel.add(new JLabel("Date de fin : " + sdf.format(reservation.getDateFin())));
        infoPanel.add(new JLabel("Durée : " + reservation.getDuree() + " nuit(s)"));

        infoPanel.add(new JLabel("Chambres :"));
        //afficher les chambres réservées
        for (Chambre c : reservation.getListChambre()) {
            infoPanel.add(new JLabel("- " + c.getType() + " à " + c.getPrix() + " €"));
        }

        infoPanel.add(new JLabel("Note donnée : " + (note.isEmpty() ? "Aucune" : note)));
        infoPanel.add(new JLabel("Total à payer : " + total + " €"));

        add(infoPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}

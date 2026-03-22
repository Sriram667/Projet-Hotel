package view;

import java.awt.*;
import javax.swing.*;
import model.*;

public class AnnulerReservation {
    public AnnulerReservation(Reservation r, CheckOut parentView) {
        Client cl = r.getClient();
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(new JLabel("Nom client : " + cl.getNom()));
        panel.add(new JLabel("Prénom client : " + cl.getPrenom()));
        panel.add(new JLabel("Email : " + cl.getEmail()));
        panel.add(new JLabel("Téléphone : " + cl.getTelephone()));

        int confirm = JOptionPane.showConfirmDialog(null, panel, "annuler ?", JOptionPane.YES_NO_OPTION);//pop up pour confirmer l'annulation
        if (confirm == JOptionPane.CANCEL_OPTION) {
            return;
        }
        if (confirm == JOptionPane.YES_OPTION) {
            for (Chambre c : r.getListChambre()) {
                c.removeReservation(r);
                c.setStatut("libre");
            }
            r.setStatut("annulée");
            parentView.getIdReservationField().setText("");
        }
    }
}

package view;

import java.util.Vector;
import javax.swing.*;
import model.Chambre;
import model.Client;
import model.Receptionniste;

public class PopupConfirmation {

    public static boolean afficher(int idResa, String dateDebut, String dateFin, Receptionniste personnel, Client client, Vector<Chambre> chambres) {

        String chambresStr = "";
        for (Chambre c : chambres) {
            chambresStr += "- chambre " + c.getType() + " (n° " + c.getNumero() + ", étage " + c.getEtage() + ")<br>";
        }

        String message =
            "<html><b>Veuillez confirmer la réservation :</b><br><br>" +
            "ID Réservation : " + idResa + "<br>" +
            "Client : " + client.getNom() + " " + client.getPrenom() + "<br>" +
            "Email : " + client.getEmail() + "<br>" +
            "Téléphone : " + client.getTelephone() + "<br><br>" +
            "Chambres attribuées :<br>" + chambresStr + "<br>" +
            "Du : " + dateDebut + "<br>" +
            "Au : " + dateFin + "</html>";

        int result = JOptionPane.showConfirmDialog(null, new JLabel(message), "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Réservation confirmée !");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Réservation annulée.");
            return false;
        }
    }
}

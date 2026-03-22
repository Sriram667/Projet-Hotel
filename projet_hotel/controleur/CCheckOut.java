package controleur;

import java.awt.*;
import javax.swing.*;
import model.*;
import view.*;

public class CCheckOut {

    private CheckOut view;
    private Hotel hotel;
    private Receptionniste personnel;
    private BienvenueView bienvenueView;

    public CCheckOut(CheckOut view, Hotel hotel, Receptionniste personnel, BienvenueView bienvenueView) {
        this.view = view;
        this.hotel = hotel;
        this.personnel = personnel;
        this.bienvenueView = bienvenueView;

        view.getValiderButton().addActionListener(e -> chercher());
        view.getDeconnexionButton().addActionListener(e -> deconnexion());
        view.getCheckInButton().addActionListener(e -> versCheckIn());
        view.getAfficherResButton().addActionListener(e -> versAfficherReservations());
    }

    private void chercher() {
        String idStr = view.getIdReservationField().getText().trim();
        if (idStr.isEmpty()) {
            message("veuillez saisir un numero de reservation");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            message("numero invalide");
            return;
        }

        Reservation r = trouver(id);
        if (r == null) {
            message("reservation introuvable");
            return;
        }

        switch (r.getStatut().toLowerCase()) {
            case "futur":
                new AnnulerReservation(r, view);
                break;
            case "fini":
                FactureView f = new FactureView(r);
                new CFacture(f, r, hotel.getListProduit(), bienvenueView);
                f.setVisible(true);
                break;
            case "en cours":
                ProlongationView v = new ProlongationView(r);
                new CProlongation(v, r);
                v.setVisible(true);
                break;
            case "passe":
                message("facture deja payee");
                break;
            default:
                message("statut inconnu");
        }
    }

    private Reservation trouver(int id) {
        for (Chambre c : hotel.getListChambre()) {
            for (Reservation r : c.getListReservation()) {
                if (r.getNumReservation() == id && !r.getStatut().equalsIgnoreCase("annulee")) {// on ne veut pas afficher les annulees
                    return r;
                }
            }
        }
        return null;
    }

    private void deconnexion() {
        fermerToutesLesFenetres();
        new BienvenueView(hotel).setVisible(true);
    }

    private void versCheckIn() {
        fermerToutesLesFenetres();
        CheckIn v = new CheckIn(hotel, personnel);
        new CCheckIn(v, hotel, personnel, bienvenueView);
        v.setVisible(true);
    }

    private void versAfficherReservations() {
        AfficherReservationsView v = new AfficherReservationsView();
        new CAfficherReservations(v, hotel);
        v.setVisible(true);
    }

    private void fermerToutesLesFenetres() {
        for (Window w : Window.getWindows()) {//obtient toutes les fenetres
            if (w instanceof JFrame) w.dispose();
        }
    }

    private void message(String msg) {
        JOptionPane.showMessageDialog(view, msg, "info", JOptionPane.INFORMATION_MESSAGE);
    }
}

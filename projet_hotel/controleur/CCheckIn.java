package controleur;

import java.text.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.*;

public class CCheckIn {

    private CheckIn view;
    private Hotel hotel;
    private Receptionniste personnel;
    private BienvenueView bienvenueView;

    public CCheckIn(CheckIn view, Hotel hotel, Receptionniste personnel, BienvenueView bienvenueView) {
        this.view = view;
        this.hotel = hotel;
        this.personnel = personnel;
        this.bienvenueView = bienvenueView;

        view.getValiderButton().addActionListener(e -> valider());
        view.getCheckOutButton().addActionListener(e -> switchVersCheckOut());
        view.getDeconnexionButton().addActionListener(e -> deconnexion());
        view.getAfficherResButton().addActionListener(e->switchVersAfficherRes());
    }

    private void valider() {
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String tel = view.getTelField().getText().trim();
        String idStr = view.getIdReservationField().getText().trim();
        String dateDebutStr = view.getDateDebutField().getText().trim();
        String dateFinStr = view.getDateFinField().getText().trim();

        int nbSingle = view.getNbSingle();
        int nbDouble = view.getNbDouble();
        int nbSuite = view.getNbSuite();
        int nbSuitePres = view.getNbSuitePres();

        if (!estEmailValide(email)) {
            JOptionPane.showMessageDialog(view, "Adresse email invalide (ex: exemple@domaine.com)", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!estTelephoneValide(tel)) {
            JOptionPane.showMessageDialog(view, "Numéro de téléphone invalide (10 chiffres attendus)", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty()
                || idStr.isEmpty() || dateDebutStr.isEmpty() || dateFinStr.isEmpty()) {
            alerte("veuillez remplir tous les champs");
            return;
        }

        if (nbSingle + nbDouble + nbSuite + nbSuitePres == 0) {
            alerte("aucune chambre selectionnee");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            alerte("id de reservation invalide");
            return;
        }

        if (existeReservation(id)) {
            alerte("id deja utilise");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date debut, fin;
        try {
            debut = sdf.parse(dateDebutStr);
            fin = sdf.parse(dateFinStr);
        } catch (ParseException e) {
            alerte("format de date invalide");
            return;
        }

        if (fin.before(debut)) {
            alerte("la date de fin doit etre apres la date de debut");
            return;
        }

        Date today = new Date();
        String statut;
        if (sdf.format(debut).equals(sdf.format(today))) {
            statut = "en cours";
        } else if (debut.after(today)) {
            statut = "futur";
        } else {
            alerte("date de debut deja passee");
            return;
        }

        Client client = new Client(nom, prenom, tel, email, hotel);
        Reservation r = new Reservation(id, debut, fin, statut, personnel, client);

        try {
            ajouterChambres(r, "Single", nbSingle);
            ajouterChambres(r, "Double", nbDouble);
            ajouterChambres(r, "Suite", nbSuite);
            ajouterChambres(r, "Suite Présidentielle", nbSuitePres);
        } catch (Exception e) {
            alerte("erreur : " + e.getMessage());
            return;
        }

        if (!PopupConfirmation.afficher(id, dateDebutStr, dateFinStr, personnel, client, new Vector<Chambre>(r.getListChambre()))) return;
        view.resetChamps();
        view.mettreAJourDisponibilites();
    }

    private void ajouterChambres(Reservation r, String type, int nb) throws Exception {
        for (int i = 0; i < nb; i++) {
            Chambre c = hotel.getChambreDisponible(type);
            if (c == null) throw new Exception("plus de " + type.toLowerCase() + " disponible");
            r.addChambre(c);
            c.addReservation(r);
            c.changeStatut();
        }
    }

    private boolean existeReservation(int id) {
        for (Chambre c : hotel.getListChambre()) {
            for (Reservation r : c.getListReservation()) {
                if (r.getNumReservation() == id) return true;
            }
        }
        return false;
    }

    private void switchVersCheckOut() {
        view.dispose();
        CheckOut v = new CheckOut(hotel, personnel);
        new CCheckOut(v, hotel, personnel, bienvenueView); 
        v.setVisible(true);
    }

    private void deconnexion() {
        for (java.awt.Window w : java.awt.Window.getWindows()) {
            if (w instanceof javax.swing.JFrame) w.dispose();
        }
        new BienvenueView(hotel).setVisible(true);
    }

    private void switchVersAfficherRes() {
        AfficherReservationsView v = new AfficherReservationsView();
        new CAfficherReservations(v, hotel);
        v.setVisible(true);
    }

    private boolean estEmailValide(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");// le mail doit respecter le format, entre crochets on a les caracteres autorises
    }

    private boolean estTelephoneValide(String tel) {
        return tel.matches("^[0-9]{10}$"); // le tel doit etre un nombre de 10 chiffres
    }


    private void alerte(String msg) {
        JOptionPane.showMessageDialog(view, msg, "info", JOptionPane.INFORMATION_MESSAGE);
    }
}

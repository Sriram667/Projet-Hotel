package controleur;

import java.text.SimpleDateFormat;
import java.util.Vector;
import model.*;
import view.AfficherReservationsView;

public class CAfficherReservations {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public CAfficherReservations(AfficherReservationsView view, Hotel hotel) {
        StringBuilder sb = new StringBuilder();
        Vector<Chambre> listChambres = hotel.getListChambre();
        Vector<Reservation> reservations = new Vector<>(); // evite doublons 

        for (Chambre chambre : listChambres) {
            for (Reservation r : chambre.getListReservation()) {// on ajoute les reservations de chaque chambre
                if (!reservations.contains(r)) {
                    reservations.add(r);
                }
            }
        }

        if (reservations.isEmpty()) {
            sb.append("aucune reservation trouvee\n");
        } else {
            for (Reservation r : reservations) {
                sb.append("reservation n").append(r.getNumReservation()).append("\n");
                sb.append("client : ").append(r.getClient().getNom()).append(" ").append(r.getClient().getPrenom()).append("\n");
                sb.append("email : ").append(r.getClient().getEmail()).append("\n");
                sb.append("telephone : ").append(r.getClient().getTelephone()).append("\n");
                sb.append("date debut : ").append(sdf.format(r.getDateDebut())).append("\n");
                sb.append("date fin : ").append(sdf.format(r.getDateFin())).append("\n");
                sb.append("chambres : ");
                for (Chambre c : r.getListChambre()) {
                    sb.append(c.getNumero()).append(" ");
                }
                sb.append("\nstatut : ").append(r.getStatut()).append("\n");
                sb.append("-------------------------\n");
            }
        }

        view.getTextArea().setText(sb.toString());
    }
}

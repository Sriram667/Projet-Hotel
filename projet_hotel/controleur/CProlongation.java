package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Reservation;
import view.ProlongationView;

public class CProlongation {

    private ProlongationView prolongationView;
    private Reservation reservation;

    public CProlongation(ProlongationView view, Reservation reservation) {
        this.reservation = reservation;
        this.prolongationView = view;

        // Action du bouton de prolongation
        prolongationView.getValiderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    String inputDate = prolongationView.getDateProlongationField().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date newEndDate = sdf.parse(inputDate);//convertir la date de String à Date

                    if (newEndDate.before(reservation.getDateFin())) {
                        JOptionPane.showMessageDialog(prolongationView, "La nouvelle date de prolongation ne peut pas être avant la date de fin initiale.");
                    } else {
                        reservation.setDateFin(newEndDate); 
                        JOptionPane.showMessageDialog(prolongationView, "Prolongation réussie !");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(prolongationView, "Date invalide.");
                }
                prolongationView.dispose();
            }
        });
    }
}

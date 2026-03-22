package controleur;

import model.Chambre;
import model.Hotel;
import model.Personnel_de_Maintenance;
import view.BienvenueView;
import view.MaintenanceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import view.ChambreView;

public class CMaintenance {

    private Hotel hotel;
    private Personnel_de_Maintenance personnel;
    private MaintenanceView vue;

    public CMaintenance(Hotel hotel, Personnel_de_Maintenance personnel) {
        this.hotel = hotel;
        this.personnel = personnel;
        this.vue = new MaintenanceView(hotel, personnel);

        vue.getBtnDeconnexion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vue.dispose();
                new BienvenueView(hotel).setVisible(true);
            }
        });

        vue.getBtnChambre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChambreView v = new ChambreView();
                new CChambre(hotel, v);
                v.setVisible(true);
            }
        });


        mettreAJourAffichage();
    }

    private void mettreAJourAffichage() {
        //affiche uniquement les chambre en maintenance en supprimant celle selectionner
        Vector<Chambre> chambres = hotel.getListChambre();
        vue.afficherChambres(chambres, new MaintenanceView.ActionListenerCheckbox() {
            @Override
            public void onCheck(Chambre chambre) {
                chambre.setStatut("libre");
                mettreAJourAffichage();
            }
        });
    }
}

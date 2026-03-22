package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Chambre;
import model.Hotel;
import view.ChambreView;

public class CChambre {

    private Hotel hotel;
    private ChambreView vue;

    public CChambre(Hotel hotel, ChambreView vue) {
        this.hotel = hotel;
        this.vue = vue;

        vue.getActionBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ajout = vue.getActionBox().getSelectedItem().equals("Ajout");
                vue.getTypeBox().setEnabled(ajout);
                vue.getEtageField().setEnabled(ajout);
                vue.getPrixField().setEnabled(ajout);
            }
        });

        vue.getBtnValider().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = (String) vue.getActionBox().getSelectedItem();
                String numStr = vue.getNumeroField().getText().trim();

                if (numStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vue, "numero obligatoire");
                    return;
                }

                int numero;
                try {
                    numero = Integer.parseInt(numStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vue, "numero invalide");
                    return;
                }

                if (action.equals("Ajout")) {
                    String type = (String) vue.getTypeBox().getSelectedItem();
                    String etageStr = vue.getEtageField().getText().trim();
                    String prixStr = vue.getPrixField().getText().trim();

                    if (etageStr.isEmpty() || prixStr.isEmpty()) {
                        JOptionPane.showMessageDialog(vue, "tous les champs sont obligatoires");
                        return;
                    }

                    for (Chambre c : hotel.getListChambre()) {
                        if (c.getNumero() == numero) {
                            JOptionPane.showMessageDialog(vue, "une chambre avec ce numero existe deja");
                            return;
                        }
                }

                    try {
                        int etage = Integer.parseInt(etageStr);
                        int prix = Integer.parseInt(prixStr);

                        Chambre chambre = new Chambre(numero, etage, type, prix, "libre", hotel);
                        hotel.getListChambre().add(chambre);

                        JOptionPane.showMessageDialog(vue, "chambre ajoutee");
                        vue.dispose();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vue, "format invalide pour etage ou prix");
                    }

                } else if (action.equals("Suppression")) {
                    Chambre toRemove = null;
                    for (Chambre c : hotel.getListChambre()) {
                        if (c.getNumero() == numero) {
                            toRemove = c;
                            break;
                        }
                    }
                    if (toRemove != null) {
                        hotel.getListChambre().remove(toRemove);
                        JOptionPane.showMessageDialog(vue, "chambre supprimee");
                    } else {
                        JOptionPane.showMessageDialog(vue, "aucune chambre trouvee");
                    }
                    vue.dispose();
                }
            }
        });
    }
}

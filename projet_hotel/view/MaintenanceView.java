package view;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import model.*;

public class MaintenanceView extends JFrame {

    private JButton btnDeconnexion, btnChambre;
    private JPanel panelChambres;
    private JScrollPane scrollPane;
    private Hotel hotel;
    private Personnel_de_Maintenance personnel;

    public MaintenanceView(Hotel hotel, Personnel_de_Maintenance personnel) {
        this.hotel = hotel;
        this.personnel = personnel;

        setTitle("Interface Maintenance");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel labelTitre = new JLabel("Chambres en maintenance", JLabel.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelTitre, BorderLayout.NORTH);

        panelChambres = new JPanel(new GridLayout(0, 1));
        scrollPane = new JScrollPane(panelChambres);
        add(scrollPane, BorderLayout.CENTER);

        // Panel du bas avec deux sous-panels
        JPanel panelBas = new JPanel(new BorderLayout());

        btnDeconnexion = new JButton("Déconnexion");
        btnChambre = new JButton("Chambre");

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(btnDeconnexion);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(btnChambre);

        panelBas.add(leftPanel, BorderLayout.WEST);
        panelBas.add(rightPanel, BorderLayout.EAST);

        add(panelBas, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnChambre() {
        return btnChambre;
    }

    public JButton getBtnDeconnexion() {
        return btnDeconnexion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Personnel_de_Maintenance getPersonnel() {
        return personnel;
    }

    public void afficherChambres(Vector<Chambre> chambres, ActionListenerCheckbox actionListener) {
        panelChambres.removeAll();
        //parcours de la liste des chambres et ajout d'une case à cocher pour chaque chambre en maintenance
        for (Chambre c : chambres) {
            if ("maintenance".equals(c.getStatut())) {
                JCheckBox cb = new JCheckBox("Chambre " + c.getNumero());
                cb.addActionListener(e -> actionListener.onCheck(c));
                panelChambres.add(cb);
            }
        }
        panelChambres.revalidate();
        panelChambres.repaint();
    }

    public interface ActionListenerCheckbox {
        void onCheck(Chambre chambre);
    }
}

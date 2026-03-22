package view;

import java.awt.*;
import javax.swing.*;

public class CreationView extends JFrame {

    public JRadioButton rbReceptionniste, rbMaintenance;
    public JTextField tfNom, tfPrenom, tfNumBadge;
    public JButton btnValider, btnFermer, btnRetour;

    public CreationView() {
        setTitle("Creation Personnel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // panel choix type
        JPanel typePanel = new JPanel();
        typePanel.setBorder(BorderFactory.createTitledBorder("Type de personnel"));
        rbReceptionniste = new JRadioButton("Receptionniste");
        rbMaintenance = new JRadioButton("Personnel de maintenance");
        ButtonGroup groupe = new ButtonGroup();
        groupe.add(rbReceptionniste);
        groupe.add(rbMaintenance);
        typePanel.add(rbReceptionniste);
        typePanel.add(rbMaintenance);

        // panel infos
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informations"));
        infoPanel.add(new JLabel("Nom :"));
        tfNom = new JTextField();
        infoPanel.add(tfNom);
        infoPanel.add(new JLabel("Prénom :"));
        tfPrenom = new JTextField();
        infoPanel.add(tfPrenom);
        infoPanel.add(new JLabel("Numéro Badge :"));
        tfNumBadge = new JTextField();
        infoPanel.add(tfNumBadge);

        // initialisation boutons
        btnRetour = new JButton("Retour");
        btnValider = new JButton("Valider");
        btnFermer = new JButton("Fermer");

        // panel boutons regroupés
        JPanel btnPanel = new JPanel(new BorderLayout());

        JPanel btn1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btn1Panel.add(btnRetour);

        JPanel btn2Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btn2Panel.add(btnValider);
        btn2Panel.add(btnFermer);

        btnPanel.add(btn1Panel, BorderLayout.WEST);
        btnPanel.add(btn2Panel, BorderLayout.EAST);

        setLayout(new BorderLayout(10,10));
        add(typePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}

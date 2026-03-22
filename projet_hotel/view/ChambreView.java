package view;

import javax.swing.*;
import java.awt.*;

public class ChambreView extends JFrame {

    private JComboBox<String> actionBox;
    private JComboBox<String> typeBox;
    private JTextField numeroField, etageField, prixField;
    private JButton btnValider;

    public ChambreView() {
        setTitle("Gestion Chambre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal avec bordure et layout vertical
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));//BorderFactory permet de créer facilement des bordures personnalisées (vide, ligne, titre, etc...)

        // panel central avec les champs
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        actionBox = new JComboBox<>(new String[]{"Ajout", "Suppression"}); //choix entre ajout et suppression d'une chambre
        typeBox = new JComboBox<>(new String[]{"Single", "Double", "Suite", "Suite Présidentielle"});
        numeroField = new JTextField();
        etageField = new JTextField();
        prixField = new JTextField();
        btnValider = new JButton("Valider");

        fieldsPanel.add(new JLabel("Action :"));
        fieldsPanel.add(actionBox);

        fieldsPanel.add(new JLabel("Type :"));
        fieldsPanel.add(typeBox);

        fieldsPanel.add(new JLabel("Numéro :"));
        fieldsPanel.add(numeroField);

        fieldsPanel.add(new JLabel("Étage :"));
        fieldsPanel.add(etageField);

        fieldsPanel.add(new JLabel("Prix :"));
        fieldsPanel.add(prixField);

        // bouton centré
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btnValider);

        // ajout au panel principal
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public JComboBox<String> getActionBox() { return actionBox; }
    public JComboBox<String> getTypeBox() { return typeBox; }
    public JTextField getNumeroField() { return numeroField; }
    public JTextField getEtageField() { return etageField; }
    public JTextField getPrixField() { return prixField; }
    public JButton getBtnValider() { return btnValider; }
}

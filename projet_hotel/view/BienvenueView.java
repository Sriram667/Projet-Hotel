package view;

import controleur.CBienvenue;
import java.awt.*;
import javax.swing.*;
import model.Hotel;

public class BienvenueView extends JFrame {

    private JTextField identifiantField;
    private JButton aideButton, validerButton;
    private JLabel noteLabel;
    private Hotel hotel;

    public BienvenueView(Hotel hotel) {
        this.hotel = hotel;

        setTitle("Identification");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);

        // conteneur principal
        JPanel container = new JPanel(new BorderLayout(20, 20));
        container.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));//BorderFactory permet de créer facilement des bordures personnalisées (vide, ligne, titre, etc...)

        // titre
        JLabel titre = new JLabel("Bienvenue", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 40));
        JLabel sousTitre = new JLabel("Sur " + hotel.getNom(), SwingConstants.CENTER);
        sousTitre.setFont(new Font("SansSerif", Font.ITALIC, 22));
        sousTitre.setForeground(new Color(70, 130, 180));

        noteLabel = new JLabel("Note moyenne : " + hotel.getNoteMoyenne() + " / 5", SwingConstants.CENTER);
        noteLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        noteLabel.setForeground(new Color(100, 100, 100));

        JPanel panelTitre = new JPanel(new GridLayout(3, 1)); // 3 lignes maintenant
        panelTitre.add(titre);
        panelTitre.add(sousTitre);
        panelTitre.add(noteLabel);

        // champ badge
        JLabel labelBadge = new JLabel("Entrez votre numéro de badge :");
        identifiantField = new JTextField();
        identifiantField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        identifiantField.setPreferredSize(new Dimension(200, 30));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(labelBadge);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(identifiantField);

        // boutons
        validerButton = new JButton("Valider");
        aideButton = new JButton("Aide");

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(validerButton, BorderLayout.WEST);
        buttonPanel.add(aideButton, BorderLayout.EAST);

        container.add(panelTitre, BorderLayout.NORTH);
        container.add(formPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(container);
        pack();
        setResizable(true);

        new CBienvenue(identifiantField, this, hotel.getListPersonnel(), validerButton, aideButton, hotel);
    }

    public void updateNote() {
        noteLabel.setText("Note moyenne : " + hotel.getNoteMoyenne() + " / 5");
    }

    public JTextField getIdentifiantField() {
        return identifiantField;
    }

    public JButton getAideButton() {
        return aideButton;
    }

    public JButton getValiderButton() {
        return validerButton;
    }
}

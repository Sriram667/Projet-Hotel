package view;

import java.awt.*;
import javax.swing.*;
import model.Hotel;
import model.Receptionniste;

public class CheckOut extends JFrame {

    private Hotel hotel;
    private Receptionniste personnel;

    private JLabel badgeLabel;
    private JTextField idReservationField;
    private JButton validerButton, checkInButton, afficherResButton, deconnexionButton;

    public CheckOut(Hotel hotel, Receptionniste personnel) {
        this.hotel = hotel;
        this.personnel = personnel;

        setTitle(hotel.getNom() + " - Check-Out");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        // menu gauche (vertical)
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(40, 40, 40));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));

        // badge
        badgeLabel = new JLabel("Badge : " + personnel.getNumBadge(), SwingConstants.CENTER);
        badgeLabel.setForeground(Color.WHITE);
        badgeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        badgeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel badgeContainer = new JPanel();
        badgeContainer.setBackground(new Color(40, 40, 40));
        badgeContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        badgeContainer.add(badgeLabel);

        // boutons
        checkInButton = new JButton("Check-In");
        checkInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkInButton.setMaximumSize(new Dimension(180, 35));

        afficherResButton = new JButton("Afficher Réservations");
        afficherResButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        afficherResButton.setMaximumSize(new Dimension(180, 35));

        deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deconnexionButton.setMaximumSize(new Dimension(180, 35));

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setLayout(new BoxLayout(boutonsPanel, BoxLayout.Y_AXIS));
        boutonsPanel.setBackground(new Color(40, 40, 40));
        boutonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        boutonsPanel.add(checkInButton);
        boutonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        boutonsPanel.add(afficherResButton);
        boutonsPanel.add(Box.createVerticalGlue());
        boutonsPanel.add(deconnexionButton);

        menuPanel.add(badgeContainer);
        menuPanel.add(boutonsPanel);

        add(menuPanel, BorderLayout.WEST);

        // panel principal centre (vertical)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();// GridBagConstraints permet un placement précis et flexible des composants (position, taille, alignement, marges, remplissage)
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titre = new JLabel("Check-Out Réservation");
        titre.setFont(new Font("SansSerif", Font.BOLD, 26));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(titre, gbc);

        JLabel idLabel = new JLabel("ID Réservation :");
        idLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        centerPanel.add(idLabel, gbc);

        idReservationField = new JTextField(15);
        idReservationField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridx = 1;
        centerPanel.add(idReservationField, gbc);

        validerButton = new JButton("Valider");
        validerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        validerButton.setPreferredSize(new Dimension(120, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(validerButton, gbc);
    }

    // getters
    public JButton getValiderButton() { return validerButton; }
    public JButton getCheckInButton() { return checkInButton; }
    public JButton getAfficherResButton() { return afficherResButton; }
    public JButton getDeconnexionButton() { return deconnexionButton; }
    public JTextField getIdReservationField() { return idReservationField; }
}

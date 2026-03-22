package view;

import java.awt.*;
import javax.swing.*;
import model.Hotel;
import model.Receptionniste;

public class CheckIn extends JFrame {

    private Hotel hotel;
    private Receptionniste personnel;

    private JLabel badgeLabel;
    private JTextField nomField, prenomField, emailField, telField;
    private JTextField idReservationField, dateDebutField, dateFinField;
    private JButton validerButton, checkOutButton, afficherResButton, deconnexionButton;

    private JComboBox<Integer> singleBox, doubleBox, suiteBox, suitePresBox;
    private JLabel singleDispo, doubleDispo, suiteDispo, suitePresDispo;

    public CheckIn(Hotel hotel, Receptionniste personnel) {
        this.hotel = hotel;
        this.personnel = personnel;

        setTitle(hotel.getNom() + " - Réservation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // menu gauche
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setPreferredSize(new Dimension(200, 600));
        menu.setBackground(new Color(40, 40, 40));

        // panneau pour afficher le badge avec marges
        JPanel badgePanel = new JPanel();
        badgePanel.setBackground(new Color(40, 40, 40));
        badgePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        badgeLabel = new JLabel("Badge : " + personnel.getNumBadge(), SwingConstants.CENTER);
        badgeLabel.setForeground(Color.WHITE);
        badgeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        badgePanel.add(badgeLabel);

        // boutons
        checkOutButton = new JButton("CHECK-OUT");
        checkOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        afficherResButton = new JButton("Afficher Réservations");
        afficherResButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        deconnexionButton = new JButton("Déconnexion");
        deconnexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // panneau des boutons avec marges
        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setLayout(new BoxLayout(boutonsPanel, BoxLayout.Y_AXIS));
        boutonsPanel.setBackground(new Color(40, 40, 40));
        boutonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        boutonsPanel.add(checkOutButton);
        boutonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        boutonsPanel.add(afficherResButton);
        boutonsPanel.add(Box.createVerticalGlue());
        boutonsPanel.add(deconnexionButton);

        // ajout au menu
        menu.add(badgePanel);
        menu.add(boutonsPanel);
        
        add(menu, BorderLayout.WEST);

        // centre
        JPanel centre = new JPanel(new BorderLayout());
        centre.setBackground(Color.WHITE);

        JLabel titre = new JLabel("Formulaire de Réservation", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        centre.add(titre, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(11, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        form.setBackground(Color.WHITE);

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        telField = new JTextField();
        idReservationField = new JTextField();
        dateDebutField = new JTextField();
        dateFinField = new JTextField();

        // Création des comboBox dynamiques
        singleBox = new JComboBox<>();
        doubleBox = new JComboBox<>();
        suiteBox = new JComboBox<>();
        suitePresBox = new JComboBox<>();

        for (int i = 0; i <= hotel.nbChambresDisponibles("Single"); i++) singleBox.addItem(i);
        for (int i = 0; i <= hotel.nbChambresDisponibles("Double"); i++) doubleBox.addItem(i);
        for (int i = 0; i <= hotel.nbChambresDisponibles("Suite"); i++) suiteBox.addItem(i);
        for (int i = 0; i <= hotel.nbChambresDisponibles("Suite Présidentielle"); i++) suitePresBox.addItem(i);

        form.add(new JLabel("Nom :")); form.add(nomField);
        form.add(new JLabel("Prénom :")); form.add(prenomField);
        form.add(new JLabel("Email :")); form.add(emailField);
        form.add(new JLabel("Téléphone :")); form.add(telField);
        form.add(new JLabel("ID Réservation :")); form.add(idReservationField);
        form.add(new JLabel("Date début (jj/mm/aaaa) :")); form.add(dateDebutField);
        form.add(new JLabel("Date fin (jj/mm/aaaa) :")); form.add(dateFinField);

        form.add(new JLabel("Nb Single :")); form.add(singleBox);
        form.add(new JLabel("Nb Double :")); form.add(doubleBox);
        form.add(new JLabel("Nb Suite :")); form.add(suiteBox);
        form.add(new JLabel("Nb Suite Présidentielle :")); form.add(suitePresBox);

        centre.add(form, BorderLayout.CENTER);

        validerButton = new JButton("Valider la réservation");
        JPanel validerPanel = new JPanel();
        validerPanel.setBackground(Color.WHITE);
        validerPanel.add(validerButton);

        centre.add(validerPanel, BorderLayout.SOUTH);
        add(centre, BorderLayout.CENTER);

        // panel droit
        JPanel dispoPanel = new JPanel();
        dispoPanel.setLayout(new BoxLayout(dispoPanel, BoxLayout.Y_AXIS));
        dispoPanel.setPreferredSize(new Dimension(220, 600));
        dispoPanel.setBorder(BorderFactory.createTitledBorder("Disponibilités"));
        dispoPanel.setBackground(new Color(230, 230, 230));

        singleDispo = new JLabel();
        doubleDispo = new JLabel();
        suiteDispo = new JLabel();
        suitePresDispo = new JLabel();

        Font fontDispo = new Font("SansSerif", Font.PLAIN, 16);

        dispoPanel.add(Box.createVerticalStrut(30));
        dispoPanel.add(formatLigne("Single :", singleDispo, fontDispo));
        dispoPanel.add(Box.createVerticalStrut(10));
        dispoPanel.add(formatLigne("Double :", doubleDispo, fontDispo));
        dispoPanel.add(Box.createVerticalStrut(10));
        dispoPanel.add(formatLigne("Suite :", suiteDispo, fontDispo));
        dispoPanel.add(Box.createVerticalStrut(10));
        dispoPanel.add(formatLigne("Suite Présidentielle :", suitePresDispo, fontDispo));

        add(dispoPanel, BorderLayout.EAST);

        // Mise à jour affichage
        mettreAJourDisponibilites();
    }
    // Méthode pour formater les lignes de disponibilité
    private JPanel formatLigne(String nom, JLabel valeur, Font font) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 230, 230));
        JLabel label = new JLabel(nom);
        label.setFont(font);
        valeur.setFont(font);
        valeur.setForeground(Color.BLUE);
        panel.add(label, BorderLayout.WEST);
        panel.add(valeur, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        return panel;
    }

    // getters
    public JButton getValiderButton() { return validerButton; }
    public JButton getCheckOutButton() { return checkOutButton; }
    public JButton getDeconnexionButton() { return deconnexionButton; }
    public JButton getAfficherResButton() { return afficherResButton; }

    public JTextField getNomField() { return nomField; }
    public JTextField getPrenomField() { return prenomField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getTelField() { return telField; }
    public JTextField getIdReservationField() { return idReservationField; }
    public JTextField getDateDebutField() { return dateDebutField; }
    public JTextField getDateFinField() { return dateFinField; }

    public int getNbSingle() { return (int) singleBox.getSelectedItem(); }
    public int getNbDouble() { return (int) doubleBox.getSelectedItem(); }
    public int getNbSuite() { return (int) suiteBox.getSelectedItem(); }
    public int getNbSuitePres() { return (int) suitePresBox.getSelectedItem(); }

    public void resetChamps() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        telField.setText("");
        idReservationField.setText("");
        dateDebutField.setText("");
        dateFinField.setText("");
        singleBox.setSelectedIndex(0);
        doubleBox.setSelectedIndex(0);
        suiteBox.setSelectedIndex(0);
        suitePresBox.setSelectedIndex(0);
    }

    public void mettreAJourDisponibilites() {
        singleDispo.setText(String.valueOf(hotel.nbChambresDisponibles("Single")));
        doubleDispo.setText(String.valueOf(hotel.nbChambresDisponibles("Double")));
        suiteDispo.setText(String.valueOf(hotel.nbChambresDisponibles("Suite")));
        suitePresDispo.setText(String.valueOf(hotel.nbChambresDisponibles("Suite Présidentielle")));
    }
}

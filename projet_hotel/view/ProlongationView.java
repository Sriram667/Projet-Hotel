package view;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import model.Reservation;

public class ProlongationView extends JFrame {

    private JLabel dateInitialeLabel;
    private JTextField dateProlongationField;
    private JButton validerButton;

    public ProlongationView(Reservation reservation) {
        setTitle("Prolongation de séjour");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);

        // conteneur principal
        JPanel container = new JPanel(new BorderLayout(20, 20));
        container.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // titre
        JLabel titre = new JLabel("Prolonger un séjour", SwingConstants.CENTER);//affiche le texte au centre
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel sousTitre = new JLabel("Veuillez entrer une nouvelle date de fin", SwingConstants.CENTER);
        sousTitre.setFont(new Font("SansSerif", Font.ITALIC, 18));
        sousTitre.setForeground(new Color(70, 130, 180));

        JPanel panelTitre = new JPanel(new GridLayout(2, 1));
        panelTitre.add(titre);
        panelTitre.add(sousTitre);

        // partie centrale : info + champ
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateInitialeLabel = new JLabel("Date de fin actuelle : " + sdf.format(reservation.getDateFin()));
        JLabel nouvelleDateLabel = new JLabel("Nouvelle date (jj/mm/aaaa) :");
        dateProlongationField = new JTextField();
        dateProlongationField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        dateProlongationField.setPreferredSize(new Dimension(200, 30));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(dateInitialeLabel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(nouvelleDateLabel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(dateProlongationField);

        // bouton de validation
        validerButton = new JButton("Valider la prolongation");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(validerButton);

        // placement
        container.add(panelTitre, BorderLayout.NORTH);
        container.add(formPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(container);
        pack();
        setResizable(true);
    }

    public JTextField getDateProlongationField() {
        return dateProlongationField;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public JLabel getDateInitialeLabel() {
        return dateInitialeLabel;
    }
}

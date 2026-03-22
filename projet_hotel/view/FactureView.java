package view;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Vector;
import model.*;

public class FactureView extends JFrame {

    private JTextArea consoArea;
    private JLabel totalLabel;
    private JButton btnConsommation;
    private JButton btnValider;
    private JButton btnFinir;
    private JTextField noteField;

    private double total = 0;
    private Vector<Produit> consommationsFacture = new Vector<>();
    public Hotel hotel;
    public Client client;

    public FactureView(Reservation reservation) {
        setTitle("Facture");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//impose un format 

        client = reservation.getClient();
        Chambre chambre = reservation.getListChambre().isEmpty() ? null : reservation.getListChambre().get(0);
        hotel = chambre != null ? chambre.getHotel() : null;

        long duree = (reservation.getDateFin().getTime() - reservation.getDateDebut().getTime()) / (1000 * 60 * 60 * 24);//convertir en jours

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Type de chambre : " + (chambre != null ? chambre.getType() : "Non spécifiée")));
        infoPanel.add(new JLabel("Séjour : du " + sdf.format(reservation.getDateDebut()) + " au " + sdf.format(reservation.getDateFin())));
        infoPanel.add(new JLabel("Durée : " + duree + " nuit(s)"));

        infoPanel.add(new JLabel("Chambres réservées :"));
        // Afficher les chambres réservées 
        for (Chambre ch : reservation.getListChambre()) {
            infoPanel.add(new JLabel(" - " + ch.getType() + " à " + ch.getPrix() + " €"));
            total += ch.getPrix() * reservation.getDuree();
        }

        add(infoPanel, BorderLayout.NORTH);

        consoArea = new JTextArea("Consommations :\n");
        consoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel basPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total : " + total + " €");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        basPanel.add(totalLabel, BorderLayout.NORTH);

        // Champ note
        JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notePanel.add(new JLabel("Note (0 à 5) :"));
        noteField = new JTextField(5);
        notePanel.add(noteField);
        basPanel.add(notePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnConsommation = new JButton("Ajouter consommation");
        btnValider = new JButton("Valider la facture");
        btnFinir = new JButton("Finir");
        buttonPanel.add(btnConsommation);
        buttonPanel.add(btnValider);
        buttonPanel.add(btnFinir);
        basPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(basPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void ajouterConsommationDansVue(Produit produit) {
        consoArea.append("- " + produit.getNom_produit() + " : " + produit.getPrix() + " €\n");
        total += produit.getPrix();
        totalLabel.setText("Total : " + total + " €");
        consommationsFacture.add(produit);
    }

    public double getTotal() {
        return total;
    }

    public Vector<Produit> getConsommationsFacture() {
        return consommationsFacture;
    }

    public JButton getBtnConsommation() {
        return btnConsommation;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public JButton getBtnFinir() {
        return btnFinir;
    }

    public String getNoteTexte() {
        return noteField.getText();
    }
    
    public Client getClient() {
        return client;
    }

}

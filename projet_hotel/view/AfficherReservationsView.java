package view;

import java.awt.*;
import javax.swing.*;

public class AfficherReservationsView extends JFrame {

    private JTextArea textArea;
    private JButton refreshButton;

    public AfficherReservationsView() {
        super("Afficher les reservations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);


        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        setContentPane(panel);
        setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}

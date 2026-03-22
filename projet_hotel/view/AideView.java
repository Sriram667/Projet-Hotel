package view;

import javax.swing.*;
import java.awt.*;

public class AideView extends JFrame {

    public AideView() {
        setTitle("aide");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JTextArea texteAide = new JTextArea();
        texteAide.setText("entrez 0 pour crée un nouveau personnel \n entrez 3 pour entrer dans le menu du personnel de maintenance\n entrez 2020 pour entrer dans e menu des receptionniste\n ");
        texteAide.setWrapStyleWord(true);//retour à la ligne automatique
        texteAide.setLineWrap(true);//retour à la ligne automatique
        texteAide.setEditable(false);
        texteAide.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(texteAide);

        JButton fermer = new JButton("fermer");
        fermer.addActionListener(e -> dispose());

        JPanel bas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bas.add(fermer);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
        setVisible(true);
    }
}

package model;

import java.util.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        // Création de l'hôtel
        Hotel hotel = new Hotel("Hotel Ciel", 5);

        // Création des chambres
        Chambre chambre101 = new Chambre(101, 1, "Single", 100, "libre", hotel);
        Chambre chambre102 = new Chambre(102, 1, "Single", 150, "maintenance", hotel);
        Chambre chambre103 = new Chambre(103, 1, "Single", 200, "libre", hotel);

        Chambre chambre201 = new Chambre(201, 2, "Double", 150, "occupe", hotel);
        Chambre chambre202 = new Chambre(202, 2, "Double", 200, "maintenance", hotel);
        Chambre chambre203 = new Chambre(203, 2, "Double", 250, "libre", hotel);

        Chambre chambre301 = new Chambre(301, 3, "Suite", 200, "fin", hotel);
        Chambre chambre302 = new Chambre(302, 3, "Suite", 250, "libre", hotel);
        Chambre chambre303 = new Chambre(303, 3, "Suite", 300, "maintenance", hotel);

        Chambre chambre129 = new Chambre(129, 4, "Suite Présidentielle", 350, "libre", hotel);

        // Création des clients
        Client j_c = new Client("Chirac", "Jacquez", "0102030401", "j_c@wtf.dvm", hotel);
        Client s_o = new Client("Valentin", "Delamarre", "0102030402", "naps.martine@proton.com", hotel);
        Client m_n = new Client("Jean", "Bouin", "0102030403", "JeanXbouin@hotmail.fr", hotel);

        // Création des personnels
        Receptionniste sarah = new Receptionniste("Pou", "Sarah", 2020, hotel);
        Receptionniste marge = new Receptionniste("Simpson", "Marge", 2025, hotel);

        Personnel_de_Maintenance jeffrey = new Personnel_de_Maintenance("Glacon", "Jeffrey", 3, hotel);

        // Création des réservations
        Reservation reservation1 = new Reservation(1, new Date(2025-1900, 4, 1), new Date(2025-1900, 7, 10), "en cours", sarah, j_c);
        Reservation reservation2 = new Reservation(2, new Date(2025-1900, 3, 5), new Date(2025-1900,4, 10), "fini", sarah, s_o);
        Reservation reservation3 = new Reservation(3, new Date(2025-1900, 7, 10), new Date(2025-1900, 10, 20), "futur", marge, m_n);

        // Ajout des chambres aux réservations
        reservation1.addChambre(chambre201);
        reservation2.addChambre(chambre301);
        reservation3.addChambre(chambre101);

        // Ajout des réservations aux chambres
        chambre201.addReservation(reservation1);
        chambre301.addReservation(reservation2);
        chambre101.addReservation(reservation3);



       

       

        // Création d'une consommation et ajout des produits
        Consommation consommation101 = new Consommation(3, reservation1);
         // Création des produits
         Produit boisson = new Produit("boisson", 4, hotel, consommation101);
         Produit plat = new Produit("plat", 15, hotel, consommation101);
         Produit dessert = new Produit("dessert", 6, hotel, consommation101);
         Produit entree = new Produit("entree", 8, hotel, consommation101);
         Produit cocktail = new Produit("cocktail", 10, hotel, consommation101);
         Produit vin = new Produit("vin", 20, hotel, consommation101);
         Produit aperitif = new Produit("aperitif", 5, hotel, consommation101);
         Produit digestif = new Produit("digestif", 7, hotel, consommation101);
         Produit cafe = new Produit("cafe", 3, hotel, consommation101);
         Produit the = new Produit("the", 2, hotel, consommation101);

        // Lier les produits à la consommation
        boisson.setConsommation(consommation101);
        plat.setConsommation(consommation101);
        dessert.setConsommation(consommation101);
        entree.setConsommation(consommation101);
        cocktail.setConsommation(consommation101);
        vin.setConsommation(consommation101);
        aperitif.setConsommation(consommation101);
        digestif.setConsommation(consommation101);
        cafe.setConsommation(consommation101);
        the.setConsommation(consommation101);

        //Ajout des avis client
        AvisClient avis1 = new AvisClient(5, new Date(), j_c);
        AvisClient avis2 = new AvisClient(4, new Date(), s_o);
        AvisClient avis3 = new AvisClient(3, new Date(), m_n);



        new BienvenueView(hotel).setVisible(true);
    }
}


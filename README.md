Rapport de projet

Projet : Gestion Hôtel

Nous avons décidé de choisir le projet de la gestion d’un hôtel, en l’occurrence ici le nom de l’hôtel est Hotel Ciel, qui modélise un système complet de gestion d’un hôtel.

On veut simuler le fonctionnement d’un hôtel à travers une application java. Avec une interface graphique qui permet aux différents personnels d’interagir avec le système selon leur rôle, quant au client, il n’accède pas directement au système mais est entièrement géré via la réception.

Pour ce faire, nous avons d’abord commencé par effectuer un diagramme de classe sur le logiciel StarUML. Cette étape consistait à identifier toutes les classes dont nous aurions besoin afin de pouvoir modéliser au mieux notre hôtel. Une fois les classes identifiées on devait ensuite leur attribuer les différents attributs et identifiés les méthodes qui les composent. Enfin, il nous restait plus qu’à effectuer les liens entre chaque classe pour que notre modèle puisse être opérationnel. La conception de ce diagramme est utile pour qu’on puisse bien imaginer les liens entre toutes les classes et par la suite générer nos classes en java grâce au logiciel, après cette étape terminée il faut donc passer aux programmations des différentes méthodes.

Ainsi, pour notre modélisation de gestion d’hôtel, nous avons identifié 8 classes :

Classe Hotel : qui a comme attribut nom, liste de chambres, liste de clients, liste de personnels, liste de produits.
Elle contient les listes principales et gère les méthodes de recherche, d'ajout, de suppression, ainsi que le calcul de la note moyenne de l’hôtel.

Classe Client : qui a comme attribut nom, prénom, numéro de téléphone, réservations, avis client.
Elle permet d’ajouter une réservation, d’émettre une note, et d’historiser ses consommations.

Classe Reservation : qui a comme attribut numéro de réservation, date début, date fin, statut, client, chambres, consommations. Elle relie un client à un ensemble de chambres sur une période donnée. Gère les statuts et l’ajout de consommations. Elle permet aussi le calcul du montant total à facturer.

Classe Chambre : qui a comme attribut numéro, étage, type, prix, statut, réservations, interventions. Elle représente les chambres de l’hôtel et permet leur suivi selon leur statut.

Classe Personnel : qui a comme attribut nom, numéro de badge, hôtel.
Deux sous classe de personnels existent : réceptionniste et maintenance qui servent pour se connecter à l’application.

Classe Produit : qui a comme attribut nom du produit, prix, hôtel. Elle représente les consommations possibles. Géré avec un nom et un prix.

Classe Consommation : qui a comme attribut produits, réservation, quantité. Elle associe un ou plusieurs produits à une réservation (quantité et total à facturer).

Classe AvisClient : qui a comme attribut note (0 à 5), client Elle permet au client de donner une note (0 à 5). Toutes les notes sont ensuite utilisées pour calculer la moyenne générale de l’hôtel.

Une fois que nous avions eu nos 8 classes (12 en comptant les sous-classes non-cités avant) qui sont le model, nous avons créé l’interface graphique ce qui nécessitait la création de nouveaux packages :

Package model : contient toutes les classes métiers.

Package view : contient les interfaces Swing (Accueil, Réservation, Maintenance, Facture...).

Package controleur : gère les interactions entre la vue et le modèle (navigation, validation).

Et c’est ainsi que nous avons modélisé la gestion d’un hôtel. Nous allons voir maintenant comment notre logiciel fonctionne.

Tout d’abord, au lancement, la première fenêtre qui s’affiche est l’Accueil (BienvenueView) qui affiche le nom de l’hôtel, et la note de l’hôtel qui s’affiche dynamiquement, un bouton aide qui sert à savoir ce qu’il faut mettre dans la zone de texte et la zone permet de rentrer un numéro de badge qui soit ouvre la fenêtre du réceptionniste (2020) ou du personnel de maintenance (3). Si on rentre « 0 » cela vas ouvrir une fenêtre permettant de créer un nouveau personnel et lui attribuer son rôle avec son numéro de badge. Et renvoie un message d’erreur si le numéro entrée ne correspond pas.

Puis, quand on rentre un numéro de badge correspondant à un réceptionniste, la fenêtre du check-in(CheckIn) s’ouvre, qui a comme élément, le bouton checkout pour changer de page, un bouton deconnexion pour retourner à l’accueil, des zone de texte pour entrés les coordonnées du client (nom, prénom, téléphone, email) et pour pouvoir l’enregistrer avec un numéro de réservation et la date du séjour avec une sélection de chambres choisis en fonction des disponibilités, qui est afficher dans la page qui s’actualise dynamiquement. Un bouton afficher réservations qui permet de retrouver toutes les informations des réservations et des clients qu’elles soient déjà passés ou en cours ou bien enregistrer pour plus tard. Quand on a fini de rentrer toutes les informations sans erreur et qu’on valide, cela va créer une réservation (avec les infos du client et de la chambre) qu’on pourra récupérer grâce au numéro de réservation.

Quand on clique sur le bouton checkout c’est la page du checkout(CheckOut) donc qui s’ouvre avec comme éléments une zone de texte pour rentrer un numéro de réservation, et renvoie une erreur si elle n’existe pas, qui selon le statut permet de faire différentes actions. Si le numéro de réservation est lié à une réservation qui n’a pas encore eu lieu, le statut de la réservation sera donc « futur » et donc on aura la possibilité d’annuler le séjour ce qui va libérer les chambres et rendre le numéro en question de nouveau valable. Si le numéro correspond à une réservation actuelle le statut sera « en cours » et on aura la possibilité de prolonger le séjour en fonction des chambres disponibles. Et si la réservation est terminée cela va afficher la facture avec l’information de la réservation et le prix total. Qui aura un bouton ajouter consommation qui ouvrira une fenêtre pop-up pour avoir la possibilité d’ajouter des produits de consommation dans la facture si le client en a pris durant son séjour et cela va affecter le prix total de la facture. Et une zone de texte pour entrer une note entre 0 et 5 (sinon erreur) qui vas changer la note moyenne de l’hôtel. Quand on a fini on valide la facture et on appuie sur le bouton finir pour fermer la page.

Pour finir, si dans l’Accueil on met le numéro de badge d’un personnel de maintenance, cela va ouvrir la fenêtre maintenance qui vas afficher les chambres avec leur numéro qui sont finis et sont passés dans l’état maintenance pour permettre de choisir quand on veut remettre la chambre disponible (libre)en appuyant sur un bouton. Il y a aussi un bouton « chambre » qui permet de créer ou supprimer une chambre. Et le bouton déconnexion pour revenir à l’Accueil.

Ce projet nous a permis de modéliser et développer un système complet de gestion d’hôtel en Java, en appliquant les principes de la programmation orientée objet et du modèle MVC. Grâce à une interface claire et des fonctionnalités réalistes (réservation, facturation, maintenance), nous avons pu simuler le fonctionnement d’un hôtel tout en respectant des contraintes techniques. Ce projet nous a permis de renforcer nos compétences en modélisation, en programmation Java et en conception d’applications structurées et réalistes.

package com.javamcdt.view;

import com.javamcdt.domain.Personnel;
import com.javamcdt.model.dao.Impl.PersonnelDao;
import com.javamcdt.model.ConnexionDB;
import com.javamcdt.model.service.Impl.PersonnelService;
import com.javamcdt.model.service.ServiceInterface;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        // 1. Injection de dépendances
        ConnexionDB db = new ConnexionDB();
        PersonnelDao dao = new PersonnelDao(db);
        ServiceInterface<Personnel> service = new PersonnelService(dao);

        // TEST 1 : Ajouter un personnel (Matricule 9)
        System.out.println("--- Test 1 : Ajout ---");
        Personnel p1 = new Personnel(9, "Khadija Mansouri", 55000.00);
        boolean saved = service.save(p1);
        System.out.println("Personnel ajouté ? " + (saved ? "OUI" : "NON"));

        // TEST 2 : Chercher le personnel qu'on vient d'ajouter (Matricule 9)
        System.out.println("\n--- Test 2 : Récupérer par ID ---");
        Personnel pRecup = service.getById(9); 
        if (pRecup != null) {
            System.out.println("Trouvé : " + pRecup);
        } else {
            System.out.println("Aucun personnel avec cet ID.");
        }

        // TEST 3 : Afficher tout le personnel
        System.out.println("\n--- Test 3 : Afficher tout le monde ---");
        List<Personnel> liste = service.getAll();
        for (Personnel p : liste) {
            System.out.println(p);
        }

        // TEST 4 : Mettre à jour le salaire du personnel (Matricule 9)
        System.out.println("\n--- Test 4 : Mise à jour du salaire ---");
        if (p1 != null) {
            p1.setSalaire(60000.00);
            boolean updated = service.update(p1);
            System.out.println("Salaire mis à jour ? " + (updated ? "OUI" : "NON"));
        }
              
        // TEST 5 : Supprimer le personnel (Matricule 9)
        System.out.println("\n--- Test 5 : Suppression ---");
        boolean deleted = service.delete(9); 
        System.out.println("Personnel supprimé ? " + (deleted ? "OUI" : "NON"));

        // Fermeture de la connexion
        db.closeConnection();
    }
}
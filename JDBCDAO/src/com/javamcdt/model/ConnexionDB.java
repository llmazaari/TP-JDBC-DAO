package com.javamcdt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

    // 1. On stocke la connexion dans une variable statique (partagée)
    private static Connection connection = null;

    // 2. On rend la méthode statique pour l'appeler partout
    public static Connection getConnection() {
        try {
            // 3. On vérifie si la connexion existe déjà
            if (connection == null || connection.isClosed()) {
                
                // 4. Les informations de VOTRE base PostgreSQL
                String url = "jdbc:postgresql://localhost:5432/Entreprise";
                String utilisateur = "";
                String motdepasse = "";
                
                // Si non, on la crée
                connection = DriverManager.getConnection(url, utilisateur, motdepasse);
                System.out.println("Connexion à la base PostgreSQL 'Cours' réussie.");
            }
            
            return connection;
            
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données :");
            e.printStackTrace();
            return null;
        }
    }

    // 5. Méthode pour FERMER la connexion proprement
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée proprement.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
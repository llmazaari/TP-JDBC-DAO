package com.javamcdt.domain;

import java.util.Objects;

public class Personnel {
    // Attributs (identiques aux colonnes de la table)
    private int mat;
    private String nom;
    private double salaire;

    // Constructeur vide
    public Personnel() {
        super();
    }

    // Constructeur avec paramètres
    public Personnel(int mat, String nom, double salaire) {
        super();
        this.mat = mat;
        this.nom = nom;
        this.salaire = salaire;
    }

    // Getters et Setters
    public int getMat() { return mat; }
    public void setMat(int mat) { this.mat = mat; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }

    // toString (pour l'affichage)
    @Override
    public String toString() {
        return "Personnel [mat=" + mat + ", nom=" + nom + ", salaire=" + salaire + "]";
    }

    // equals et hashCode (bonnes pratiques)
    @Override
    public int hashCode() {
        return Objects.hash(mat, nom, salaire);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personnel other = (Personnel) obj;
        return Objects.equals(mat, other.mat) && 
               Objects.equals(nom, other.nom) && 
               Double.compare(other.salaire, salaire) == 0;
    }
}

 Objectif principal du projet
L'objectif de ce TP n'est pas seulement de faire fonctionner une base de données. Il est de vous apprendre à **structurer proprement une application Java**. 
Sans architecture, tout le code SQL serait mélangé avec l'interface utilisateur, rendant le code impossible à maintenir ou à tester. L'architecture DAO sépare les responsabilités :
1. **La couche Domaine** : Représente les données.
2. **La couche DAO** : Gère uniquement les requêtes SQL (le dialogue avec la base).
3. **La couche Service** : Gère les règles métier et la logique.
4. **La couche Vue** : Affiche les résultats (le point d'entrée).

---

##  Explication détaillée de l'architecture et du code

### 1. Le Domaine (`package domain`)
*   **Fichier :** `Personnel.java`
*   **Explication :** La classe `Personnel` sert à regrouper les données d'un employé dans un seul objet. Sans elle, le DAO devrait envoyer trois variables séparées (mat, nom, salaire), ce qui est difficile à gérer. Le DAO lit la base de données et crée un objet `Personnel` avec ces valeurs. L'application utilise ensuite cet objet unique sans se soucier de la base de données.
*   (sert à faire correspondre les lignes d'une table de votre base de données à des objets Java.)
*   Concrètement, une ligne de ta base de données :
(9, "Khadija", 55000.0)

Devient cet objet Java grâce à la classe :
Personnel p = new Personnel(9, "Khadija", 55000.0);

### 2. L'Interface DAO (`package model.dao`)
*   **Fichier :** `Dao.java`
*   **Explication :** C'est une interface générique `<T>` qui définit les méthodes CRUD (`save`, `getById`, `getAll`, `update`, `delete`). Si l'on change de base de données (PostgreSQL, MySQL, MongoDB), l'interface reste identique, seule l'implémentation change.

### 3. L'Implémentation DAO (`package model.dao.Impl`)
*   **Fichier :** `PersonnelDao.java`
*   **Déroulement de l'algorithme :** Cette classe implémente les méthodes de l'interface en utilisant l'API JDBC.
    *   **`save()`** : Prépare une requête `INSERT INTO`. On utilise `PreparedStatement` pour éviter les injections SQL. On remplace les `?` par les valeurs de l'objet, puis on exécute la mise à jour.
    *   **`getById()`** : Prépare un `SELECT * WHERE mat = ?`. On exécute la requête, et si `rs.next()` renvoie vrai (une ligne trouvée), on crée un nouvel objet `Personnel` avec les données du `ResultSet`.
    *   **`getAll()`** : Prépare un `SELECT *`. On parcourt le `ResultSet` avec une boucle `while`, et on ajoute chaque objet dans une `ArrayList`.
    *   **`update()` et `delete()`** : Même principe, on prépare la requête SQL et on exécute.

### 4. L'Interface Service (`package model.service`)
*   **Fichier :** `ServiceInterface.java`
*   **Explication :** C'est une interface qui définit les méthodes de la couche métier.

### 5. L'Implémentation Service (`package model.service.Impl`)
*   **Fichier :** `PersonnelService.java`
*   **Explication et Injection de Dépendance :** Cette couche contient les règles métier.
    *   Dans la méthode `save()`, avant d'appeler la base de données, le service vérifie les données (le matricule est-il positif ? le nom est-il vide ? le salaire est-il négatif ?).
    *   **Injection de dépendance :** Le constructeur prend un `Dao<Personnel>` en paramètre. On n'instancie pas `new PersonnelDao()` à l'intérieur de la classe. Cela permet de tester le service avec un faux DAO (tests unitaires) et réduit le couplage entre les classes.

### 6. La Vue / Point d'entrée (`package view`)
*   **Fichier :** `Main.java`
*   **Déroulement :** Cette classe instancie les dépendances.
    *   On instancie `ConnexionDB`, puis `PersonnelDao` (en lui passant la connexion), puis `PersonnelService` (en lui passant le DAO).
    *   On exécute 5 tests (Ajout, Récupération, Affichage, Modification, Suppression) pour valider le cycle CRUD complet.
    *   On ferme la connexion à la fin avec `db.closeConnection()`.

### 7. La Connexion (`package model`)
*   **Fichier :** `ConnexionDB.java`
*   **Explication :** Cette classe gère la connexion à la base de données. Elle utilise `DriverManager` et stocke la connexion dans une variable statique. La méthode `getConnection()` vérifie si la connexion est ouverte ; si oui, elle la réutilise, sinon, elle en crée une nouvelle.
*   remplacer avec votre propre mdps et utilisateur 

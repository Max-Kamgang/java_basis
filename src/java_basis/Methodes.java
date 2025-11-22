/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_basis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UltraBook 3.1
 */
public class Methodes {
CONNECTION my_connection = new CONNECTION();
    // Ce fichier vise à vous montrer Comment utiliser differnetes méthodes Basiques
    
    // Méthode D'insertion  dans la Base de données
    
    public void ajouterEmploye(String nom, String prenom, String email, int tel, int idService, int idPoste, String cni)  {
        // Ce qui est entre parenthèses représentes les attributs de la Classe Employé . Vous allez devoir le modifier 
        // en focntion de vos préférences
    String sql = "INSERT INTO employe (nom, prenom, email, tel, id_service, id_poste, cni) VALUES (?, ?, ?, ?, ?, ?, ?)";
    // Ceci représente la requête Sql utilisée . les ? sont là parce que que c'est l'utilisateur qui décidera sur l'interface ce 
    // qui sera enregistré dans la base de données donc du coup c'est une forme de faire la dynamique
    
    try (Connection con = my_connection.CreateConnection();
            // Appel de la Méthod de connexion à la base de donées avec un try {catch {}}
         PreparedStatement pstmt = con.prepareStatement(sql)) {
        
        // ci on indexe les Champs Correspondant dans la base de données avec leurs noms que vous avez entrés dans la base de données
        // et aussi en focntion de leur ordre . Je tiens à préciser qu'il ne faut pas rater cet ordre sinon les informations se retrouveront
        // plutot dans un champ qui ne leur est pas destiné et ça produira des erreurs dans certains cas de figures
        pstmt.setString(1, nom);
        pstmt.setString(2, prenom);
        pstmt.setString(3, email); // Assurez-vous que vous passez l'email ici
        pstmt.setInt(4, tel);
        pstmt.setInt(5, idService);
        pstmt.setInt(6, idPoste);
        pstmt.setString(7, cni);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        try {
            throw e; // Relancer l'exception pour la gérer dans l'appelant
        } catch (SQLException ex) {
            Logger.getLogger(Methodes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }
    
    // Méthodes pour la Modification d'un élément
    // En fait le principe est le même que dans la méthode Ajouter . C'est la requête qui Change
    public void modifierEmploye(int idEmploye, String nom, String prenom, int idService, int idPoste, String cni) {
        String sql = "UPDATE employe SET nom = ?, prenom = ?, id_service = ?, id_poste = ?, cni = ? WHERE id_employe = ?";
        
        try (Connection con = my_connection.CreateConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setInt(3, idService);
            pstmt.setInt(4, idPoste);
            pstmt.setString(5, cni);
            pstmt.setInt(6, idEmploye);
            pstmt.executeUpdate();
            System.out.println("Employé modifié avec succès !");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode Pour Supprimer un Employé 
    // En fait c'est aussi le même process à la seule différence que pour supprimer un employé dans une dbase de donées
    //nous n'avons besoin que de son identifiant 
    public void supprimerEmploye(int idEmploye) {
        String sql = "DELETE FROM employe WHERE id_employe = ?";
        
        try (Connection con = my_connection.CreateConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setInt(1, idEmploye);
            pstmt.executeUpdate();
            System.out.println("Employé supprimé avec succès !");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Méthode pour Charge les Informations dans un JTable (Cette méthode est biaisée et a deux façon de la réaliser à ma connaissance)
    // Vous allez les chercher par vous-même
    
    // Méthodes Extra 
    
    // Méthodes pour Générer par exemple un matricule dynamique à un Employé du genre 25GL012
    // Alors le Process est simple 
    
    public String generateClientsCode() {
      String lastCode = null;
    int lastIncrement = 0;

    // Récupérer le dernier code d'employé
    String query = "SELECT `Matricule_cli` FROM `client` ORDER BY `Matricule_cli` DESC LIMIT 1"; // Ajustez le nom de la colonne

    try (Connection con = my_connection.CreateConnection();
          
         PreparedStatement st = con.prepareStatement(query);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            lastCode = rs.getString("Matricule_cli");
        }
        if (lastCode != null && lastCode.length() >= 2) {
            // Extraire les deux derniers chiffres
           lastIncrement = Integer.parseInt(lastCode.substring(lastCode.length() - 2)) + 1; // Incrémenter
        }
    } catch (SQLException ex) {
        Logger.getLogger(Methodes.class.getName()).log(Level.SEVERE, null, ex);
    }
    // Générer le nouveau code
    return "25cli_" + String.format("%02d", lastIncrement);
    }
}


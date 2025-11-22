/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_basis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author UltraBook 3.1
 */
public class CONNECTION {
     public Connection CreateConnection(){
         
        java.sql.Connection con = null; // Initaialiszation de la connection à null 
        
        MysqlDataSource mds = new MysqlDataSource();
        mds.setServerName("localhost"); // Indexation du serveur à utiliser 
        mds.setPortNumber(3306); // Indexation du port à utiliser (Ici on utilise Mysql et Aurora pour le port 3306) 
        mds.setUser("root"); // Indexation du nom d'utilisateur dans le serveur  que vous utilisez
        mds.setPassword(""); // Mot de passe du serveur que vous utilisez
        mds.setDatabaseName("vente"); // Indexation de la base de données que vous utilisez c'est à dir ele nom de la base de données
        
        
       
        try {
            con = mds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CONNECTION.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
        
        return con; 
           
    
    
}
    
}

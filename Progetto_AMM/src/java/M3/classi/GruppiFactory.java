/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Federico
 */
public class GruppiFactory {
    private static GruppiFactory singleton;

    public static GruppiFactory getInstance() {
        if (singleton == null) {
            singleton = new GruppiFactory();
        }
        return singleton;
    }
    
    //Gestione DB
    private String connectionString;
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
            return this.connectionString;
    }
    //Fine gestione DB
    
    private ArrayList<Gruppo> listaGruppi = new ArrayList<>();
    
    private GruppiFactory() {        
    }

    public Gruppo getGruppoById(int id) {
        try{  
            UtenteFactory utenteFactory = UtenteFactory.getInstance();
            
            String query =  "select * from gruppo where gruppo_id = ?";
            
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            Gruppo current = new Gruppo();
            
            if (res.next()) {
                current.setId(res.getInt("gruppo_id"));
                current.setNome(res.getString("nome"));
                current.setFrasePresentazione(res.getString("frasePresentazione"));
                current.setUrlFotoGruppo(res.getString("urlFotoProfilo"));
                current.setAdmin(utenteFactory.getUtenteById(res.getInt("amministratore")));
            }
            return current; 
        
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Gruppo> getListaGruppi(){
        try {
            UtenteFactory utenteFactory = UtenteFactory.getInstance();
            ArrayList<Gruppo> list = new ArrayList<>();
        
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query =  "select * from gruppo ";
           
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            while (res.next()) {
                Gruppo current = new Gruppo();
                
                current.setId(res.getInt("gruppo_id"));
                current.setNome(res.getString("nome"));
                current.setFrasePresentazione(res.getString("frasePresentazione"));
                current.setUrlFotoGruppo(res.getString("urlFotoProfilo"));
                current.setAdmin(utenteFactory.getUtenteById(res.getInt("amministratore")));
                
                list.add(current);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    //funzione che controlla l'appartenenza ad un gruppo
    public boolean controllaApp(int idUtente, int idGruppo)
    {
        try{
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query =  "select * from membriGruppo where membro = ? AND groups = ? ";
           
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, idUtente);
            stmt.setInt(2, idGruppo);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            if(res.next())
                return true;
            else 
                return false;
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void addAtGroup(int user, int gruppo)
    {
        try{
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");

            String query =  "INSERT INTO membriGruppo (membro, groups)  VALUES (?, ?) ";
            String query2 =  "INSERT INTO membriGruppo (membro, groups)  VALUES (?, ?) ";
            
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            
            stmt.setInt(1, user);
            stmt.setInt(2, gruppo);
            stmt2.setInt(1, gruppo);
            stmt2.setInt(2, user);
            
            // Esecuzione query
            stmt.executeUpdate();
            stmt2.executeUpdate();
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

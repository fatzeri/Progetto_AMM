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

/**
 *
 * @author Federico
 */
public class UtenteFactory {
    //Pattern Design Singleton
    private static UtenteFactory singleton;

    public static UtenteFactory getInstance() {
        if (singleton == null) {
            singleton = new UtenteFactory();
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

    private UtenteFactory() {
    }

    public Utente getUtenteById(int id) {
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query = 
                      "select * from utente "
                    + "where utente_id = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, id);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                Utente current = new Utente();
                current.setId(res.getInt("utente_id"));
                current.setNome(res.getString("nome"));
                current.setCognome(res.getString("cognome"));
                current.setDataNascita(res.getDate("dataNascita"));
                current.setFrasePresentazione(res.getString("frasePresentazione"));
                current.setEmail(res.getString("email"));
                current.setPassword(res.getString("password"));
                current.setUrlFotoProfilo(res.getString("urlFotoProfilo"));

                stmt.close();
                conn.close();
                return current;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getIdByUserAndPassword(String user, String password){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query = 
                      "select utente_id from utente "
                    + "where nome = ? and password = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setString(1, user);
            stmt.setString(2, password);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                int id = res.getInt("utente_id");

                stmt.close();
                conn.close();
                return id;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
        
    }
    public ArrayList<Utente> getListaUtenti() {
        try {
        ArrayList<Utente> listaUtenti = new ArrayList<>();
        
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query =  "select * from utente ";
           
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            while (res.next()) {
                Utente current = new Utente();
                
                current.setId(res.getInt("utente_id"));
                current.setNome(res.getString("nome"));
                current.setCognome(res.getString("cognome"));
                current.setDataNascita(res.getDate("dataNascita"));
                current.setFrasePresentazione(res.getString("frasePresentazione"));
                current.setEmail(res.getString("email"));
                current.setPassword(res.getString("password"));
                current.setUrlFotoProfilo(res.getString("urlFotoProfilo"));
                //l'utente amministratore non lo inserisco nella lista utenti
                if(current.getId() != 0)
                    listaUtenti.add(current);
            }
            return listaUtenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean aggiornaProfilo(Utente user)
    {
        try{ 
        // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
        
            String query =  "UPDATE utente SET "
                    + "nome = ?, "
                    + "cognome = ?, "
                    + "dataNascita = ?, "
                    + "frasePresentazione = ?, "
                    + "email = ?, "
                    + "password = ?, "
                    + "urlFotoProfilo = ? "
                    + "where utente_id = ? ";
           
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getCognome());
            stmt.setDate(3, user.getDataNascita());
            stmt.setString(4, user.getFrasePresentazione());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getUrlFotoProfilo());
            stmt.setInt(8, user.getId());
            
            // Esecuzione query
            int res = stmt.executeUpdate();
            
            if(res > 0)
                return true;
            return false;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }  
    
    public boolean cancellaUtente( Utente user) 
    {
        boolean flag = false;
        PreparedStatement stmt_post = null;
        PreparedStatement stmt_amicizie = null;
        PreparedStatement stmt_membriGruppo = null;
        PreparedStatement stmt_utente = null;
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
        
            // Caricamento utenti
            try {
                 // Inizio transazione
                conn.setAutoCommit(false);

                // Eliminazione dei post creati dall'utente
                stmt_post = conn.prepareStatement("DELETE FROM post WHERE autore = ?");
                stmt_post.setInt(1, user.getId());
                stmt_post.executeUpdate();

                // Eliminazione dei post sulla bacheca dell'utente
                stmt_post = conn.prepareStatement("DELETE FROM post WHERE utenteDest = ?");
                stmt_post.setInt(1, user.getId());
                stmt_post.executeUpdate();
                
                // Eliminazione amicizie
                stmt_amicizie = conn.prepareStatement("DELETE FROM amicizie WHERE follower = ? OR followed = ?");
                stmt_amicizie.setInt(1, user.getId());
                stmt_amicizie.setInt(2, user.getId());
                stmt_amicizie.executeUpdate();

                // Eliminazione iscrizioni ai gruppi
                stmt_membriGruppo = conn.prepareStatement("DELETE FROM membriGruppo WHERE membro = ?");
                stmt_membriGruppo.setInt(1, user.getId());
                stmt_membriGruppo.executeUpdate();

                //Cambio l'amministratore del gruppo con l'utente amministratore
                stmt_membriGruppo = conn.prepareStatement("UPDATE gruppo SET amministratore = 0 WHERE amministratore = ?");
                stmt_membriGruppo.setInt(1, user.getId());
                stmt_membriGruppo.executeUpdate();
                
                // Eliminazione utente
                stmt_utente = conn.prepareStatement("DELETE FROM utente WHERE utente_id = ?");
                stmt_utente.setInt(1, user.getId());
                stmt_utente.executeUpdate();
                
                conn.commit();
                flag= true;
            } catch (SQLException ex) {

                // Errore SQL, rollback
                if(conn != null)
                    conn.rollback();

                ex.printStackTrace();
            } finally {

                // Chiusura statements
                if(stmt_post != null)
                    stmt_post.close();
                if(stmt_amicizie!= null)
                    stmt_amicizie.close();
                if(stmt_membriGruppo != null)
                    stmt_membriGruppo.close();
                if(stmt_utente != null)
                    stmt_utente.close();

                // Chiusura connessione
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }
    
    //funzione che controlla l'amicizia
    public boolean controllaApp(int idUtente, int idUser)
    {
        boolean flag = false;
        if(idUtente == idUser)
            flag = true;
        else
        {
            try{
                Connection conn = DriverManager.getConnection(connectionString, "username", "password");

                String query =  "select * from amicizie where follower = ? AND followed = ? ";

                // Prepared Statement
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, idUtente);
                stmt.setInt(2, idUser);
                // Esecuzione query
                ResultSet res = null;
                res = stmt.executeQuery();

                if(res.next())
                    flag = true;
                else 
                    flag = false;

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    
    public void addHearths(int user1, int user2)
    {
        try{
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");

            String query =  "INSERT INTO amicizie (follower, followed)  VALUES (?, ?) ";
            String query2 =  "INSERT INTO amicizie (follower, followed)  VALUES (?, ?) ";
            
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt2.setInt(1, user2);
            stmt2.setInt(2, user1);
            
            // Esecuzione query
            stmt.executeUpdate();
            stmt2.executeUpdate();
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

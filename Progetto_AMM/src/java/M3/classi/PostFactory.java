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
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Federico
 */

public class PostFactory {
    //Pattern Design Singleton
    private static PostFactory singleton;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
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
    
    private PostFactory() {
    }

    public Post getPostById(int id) {
        UtenteFactory utenteFactory = UtenteFactory.getInstance();
        
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query = 
                      "select * from post "
                    + "join postType on post.tipo = postType.postType_id "
                    + "where post_id = ?";    
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, id);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                Post current = new Post();
                //imposto id del post
                current.setId(res.getInt("post_id"));
                
                //impost il contenuto del post
                current.setContent(res.getString("content"));
                
                //imposto il tipo del post
                current.setPostType(this.postTypeFromString(res.getString("postType_nome")));
                
                //imposto l'autore del post
                Utente autore = utenteFactory.getUtenteById(res.getInt("user"));
                current.setUser(autore);

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

    public List getPostList(Utente utente) {
        UtenteFactory utenteFactory = UtenteFactory.getInstance();
        ArrayList<Post> listaPost = new ArrayList<>();
        
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query = 
                      "select * from post "
                    + "join postType on post.tipo = postType.postType_id "
                    + "left join membriGruppo on post.groupDest = membriGruppo.groups "
                    + "where post.utenteDest = ? OR membriGruppo.membro = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, utente.getId());
            stmt.setInt(2, utente.getId());
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            while (res.next()) {
                
                Post current = new Post();
                //imposto id del post
                current.setId(res.getInt("post_id"));
                
                //imposto l'autore del post
                current.setUser(utenteFactory.getUtenteById(res.getInt("autore")));
                
                //imposto text del post
                current.setText(res.getString("text"));
                
                //impost il contenuto del post
                current.setContent(res.getString("content"));
                
                //imposto il tipo del post
                current.setPostType(this.postTypeFromString(res.getString("nome")));
                /*
                //imposto il gruppo destinatario del post
                current.setGroupDest(res.getInt("groupDest"));
                
                //imposto il destinatario del post
                current.setUtenteDest(res.getInt("utenteDest"));*/
                
                
                listaPost.add(current);
            }

            stmt.close();
            conn.close();
            return listaPost;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }  
    
    private Post.Type postTypeFromString(String type){
        
        if(type.equals("IMAGE"))
            return Post.Type.IMAGE;
        
        return Post.Type.TEXT;
    }
    
    public List getPostList(Gruppo group) {
        UtenteFactory utenteFactory = UtenteFactory.getInstance();
        ArrayList<Post> listaPost = new ArrayList<>();
        
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
            
            String query = 
                      "select * from post "
                    + "join postType on post.tipo = postType.postType_id "
                    + "where post.groupDest = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, group.getId());
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            while (res.next()) {
                
                Post current = new Post();
                //imposto id del post
                current.setId(res.getInt("post_id"));
                
                //imposto l'autore del post
                current.setUser(utenteFactory.getUtenteById(res.getInt("autore")));
               
                //imposto text del post
                current.setText(res.getString("text"));
                
                //impost il contenuto del post
                current.setContent(res.getString("content"));
                
                //imposto il tipo del post
                current.setPostType(this.postTypeFromString(res.getString("tipo")));
                
                //imposto il gruppo destinatario del post
                current.setGroupDest(res.getInt("groupDest"));
                
                //imposto il destinatario del post
                current.setUtenteDest(res.getInt("utenteDest"));
                
                
                listaPost.add(current);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPost;
    }  
    
    private int postTypeFromEnum(Post.Type type){
        //È realizzabile in modo più robusto rispetto all'hardcoding degli indici
        if(type == Post.Type.TEXT)
                return 1;
            else
                return 2;
    }
    
    public void inserimentoPost(Utente autore, String mex, String allegato, String postType, int groupDest,int utenteDest)
    {
        try{ 
        // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "username", "password");
        
            String query =  "INSERT into post (post_id, autore, text, content, tipo, groupdest, utenteDest) "
                    + "values (default, ?, ?, ?, ?, ?, ?)";
           
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, autore.getId());
            stmt.setString(2, mex);
            stmt.setString(3, allegato);
            if(postType.equals("TEXT"))
                stmt.setInt(4, 1);
            if(postType.equals("IMAGE"))
                stmt.setInt(4, 2);
            if(postType.equals("LINK"))
                stmt.setInt(4, 3);
            if(groupDest == 0)
                stmt.setNull(5, java.sql.Types.INTEGER);
            else
            {
                stmt.setInt(5, groupDest);
            }
            if(utenteDest == 0)
            {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            else
            {
                stmt.setInt(6, utenteDest);
            }
            
            // Esecuzione query
            int res = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

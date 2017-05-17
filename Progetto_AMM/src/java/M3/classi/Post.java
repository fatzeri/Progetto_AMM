/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

/**
 *
 * @author Federico
 */
public class Post {

    private int id;
    private Utente user;
    private String text;
    private String content;
    private Type postType;
    private int groupDest;
    private int utenteDest;
            
    public enum Type {
        TEXT, IMAGE, LINK
    };
    
    public Post() {
        id = 0;
        user = null;
        text="";
        content = "";
        postType = Type.TEXT;
        groupDest = -1;
        utenteDest = -1;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public Utente getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Utente user) {
        this.user = user;
    }
    
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the user to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the postType
     */
    public Type getPostType() {
        return postType;
    }

    /**
     * @param postType the postType to set
     */
    public void setPostType(Type postType) {
        this.postType = postType;
    }
    
    /**
     * @return the groupDest
     */
    public int getGroupDest() {
        return groupDest;
    }

    /**
     * @param groupDest the groupDest to set
     */
    public void setGroupDest(int groupDest) {
        this.groupDest = groupDest;
    }

    /**
     * @return the utenteDest
     */
    public int getUtenteDest() {
        return utenteDest;
    }

    /**
     * @param utenteDest the utenteDest to set
     */
    public void setUtenteDest(int utenteDest) {
        this.utenteDest = utenteDest;
    }
}

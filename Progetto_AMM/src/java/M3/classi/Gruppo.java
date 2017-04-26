/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

import java.util.ArrayList;
import M3.classi.Gruppo;
import M3.classi.GruppiFactory;
import M3.classi.Post;
import M3.classi.PostFactory;
import M3.classi.Utente;
import M3.classi.UtenteFactory;
/**
 *
 * @author Federico
 */
public class Gruppo {
   
    private int id;
    private String nome;
    private String frasePresentazione;    
    private String urlFotoGruppo;
    private ArrayList<Utente> utenti = new ArrayList<>();
    final static int DIM = 50;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the frasePresentazione
     */
    public String getFrasePresentazione() {
        return frasePresentazione;
    }

    /**
     * @param frasePresentazione the frasePresentazione to set
     */
    public void setFrasePresentazione(String frasePresentazione) {
        this.frasePresentazione = frasePresentazione;
    }

    /**
     * @return the urlFotoGruppo
     */
    public String getUrlFotoGruppo() {
        return urlFotoGruppo;
    }

    /**
     * @param urlFotoGruppo the urlFotoGruppo to set
     */
    public void setUrlFotoGruppo(String urlFotoGruppo) {
        this.urlFotoGruppo = urlFotoGruppo;
    }
    
    public Utente getUtente(int id)
    {
        return (Utente) UtenteFactory.getInstance().getUtenteById(id);
    }
    
    //controlla se un utente appartiene al gruppo
    public boolean controlUtente(Utente x)
    {
        int i;
        for (Utente utente : this.getUtenti()){
            if(utente.getId() == x.getId())
            return true;
        }  
        return false; 
    }
    
    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(ArrayList<Utente> utenti) {
        this.utenti = utenti;
    }
    
    /**
     * @return the utenti
     */
    public ArrayList<Utente> getUtenti() {
        return utenti;
    }
}

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
public class Gruppo {
    
    private int id;
    private String nome;
    private String frasePresentazione;    
    private String urlFotoGruppo;
    private Utente utenti[];
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

    /**
     * @return the utenti
     */
    public Utente[] getUtenti() {
        return utenti;
    }

    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(Utente[] utenti) {
        this.utenti = utenti;
    }
    
    public Utente getUtente(int id)
    {
        return utenti[id];
    }
    
    public void editUtente(Utente utente)
    {
        this.utenti[utente.getId()] = utente;
    }
    //controlla se un utente appartiene al gruppo
    public boolean controlUtente(Utente utente)
    {
        int i;
        for(i=0; i < DIM; i++)
        {
            if(this.utenti[i].getId() == utente.getId())
                return true;
        }
        return false;
    }
}

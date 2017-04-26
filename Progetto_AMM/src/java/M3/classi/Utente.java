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
public class Utente 
{ 
    private int id;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String frasePresentazione;
    private String email;
    private String password;
    private String urlFotoProfilo;

    public Utente() {
        id = 0;
        nome = "";
        cognome = "";
        dataNascita = "";
        frasePresentazione = "";
        email = "";
        password = "";
        urlFotoProfilo= "";
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
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the dataNascita
     */
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * @param dataNascita the dataNascita to set
     */
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the urlFotoProfilo
     */
    public String getUrlFotoProfilo() {
        return urlFotoProfilo;
    }

    /**
     * @param urlFotoProfilo the urlFotoProfilo to set
     */
    public void setUrlFotoProfilo(String urlFotoProfilo) {
        this.urlFotoProfilo = urlFotoProfilo;
    }
    
    public boolean controllaProfilo()
    {
        if(this.cognome != "" &&
           this.urlFotoProfilo != "" &&
           this.dataNascita != "" &&
           this.frasePresentazione != "")
            return true;
        return false;
    }
    /*
    @Override
    public boolean equals(Object altroUtente) {
        if (altroUtente instanceof Utente)
            if (this.getId() == ((Utente)altroUtente).getId()) return true;
        return false;
    }*/
}


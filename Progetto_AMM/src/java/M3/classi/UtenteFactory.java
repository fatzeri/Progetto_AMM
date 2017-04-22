/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

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

    private ArrayList<Utente> listaUtenti = new ArrayList<Utente>();

    private UtenteFactory() {
        //Creazione utenti

        //Federico
        Utente utente1 = new Utente();
        utente1.setId(0);
        utente1.setNome("Andonio");
        utente1.setCognome("Cipolla");
        utente1.setDataNascita("16/02/1980");
        utente1.setFrasePresentazione("Non c'è niente di meglio che un piatto di pasta con fagioli");
        utente1.setEmail("utente1@gmail.com");
        utente1.setPassword("123");
        utente1.setUrlFotoProfilo("img/utente1.jpg");

        //Achille
        Utente utente2 = new Utente();
        utente2.setId(1);
        utente2.setNome("Djanni");
        utente2.setCognome("Tocco");
        utente2.setDataNascita("16/05/2010");
        utente2.setFrasePresentazione("Miaooooooooooo");
        utente2.setEmail("utente2@gmail.com");
        utente2.setPassword("123");
        utente2.setUrlFotoProfilo("img/utente2.jpg");
        
        //Dario
        Utente utente3 = new Utente();
        utente3.setId(2);
        utente3.setNome("Javier");
        utente3.setCognome("Zanetti");
        utente3.setDataNascita("10/08/1973");
        utente3.setFrasePresentazione("FOZZA INDà");
        utente3.setEmail("utente3@gmail.com");
        utente3.setPassword("123");
        utente3.setUrlFotoProfilo("img/utente3.jpg");

        listaUtenti.add(utente1);
        listaUtenti.add(utente2);
        listaUtenti.add(utente3);
    }

    public Utente getUtenteById(int id) {
        for (Utente utente : this.listaUtenti) {
            if (utente.getId() == id) {
                return utente;
            }
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

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

    private ArrayList<Gruppo> listaGruppi = new ArrayList<Gruppo>();
    
    private GruppiFactory() {
        //Creazione gruppi
        UtenteFactory utenteFactory = UtenteFactory.getInstance();
        
        //Clash of clans
        Gruppo gruppo1 = new Gruppo();
        gruppo1.setId(0);
        gruppo1.setNome("Clash Of clans");
        gruppo1.setFrasePresentazione("Solo per utenti pro!");
        gruppo1.getUtenti().add(utenteFactory.getUtenteById(0));
        gruppo1.getUtenti().add(utenteFactory.getUtenteById(1));
        gruppo1.setUrlFotoGruppo("img/gruppo1.jpg");
        

        //Interisti per sempre
        Gruppo gruppo2 = new Gruppo();
        gruppo2.setId(1);
        gruppo2.setNome("Interisti per sempre");
        gruppo2.setFrasePresentazione("#AMALA");
        gruppo2.setUrlFotoGruppo("img/gruppo2.jpg");
        gruppo2.getUtenti().add(utenteFactory.getUtenteById(0));
        gruppo2.getUtenti().add(utenteFactory.getUtenteById(2));
        
        listaGruppi.add(gruppo1);
        listaGruppi.add(gruppo2);
        
    }

    public Gruppo getGruppoById(int id) {
        for (Gruppo gruppo : this.listaGruppi) {
            if (gruppo.getId() == id) {
                return gruppo;
            }
        }
        return null;
    }
    
    public ArrayList<Gruppo> getListaGruppi(){
        return listaGruppi;
    }
}
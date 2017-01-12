package com.sabel.Kontaktverwaltung.model;

import java.util.ArrayList;

/**
 * Created by Matthias on 12.01.2017.
 */
public class Datenbank {
    private ArrayList<Kontakt> kontakte;

    public Datenbank(){
        kontakte = new ArrayList<>();
        generiereTestdaten();
    }

    public int anzahlKontakte(){
        return kontakte.size();
    }

    public Kontakt gibKontakt(int index){
        return kontakte.get(index);
    }

    public void hinzufuegen(Kontakt kontakt){
        kontakte.add(kontakt);
    }

    public Kontakt suchen(String name){
        for (int i=0; i<kontakte.size();i++) {
            if (kontakte.get(i).getName().equals(name)){
                return kontakte.get(i);
            }
        }
        return null;
    }

    private void generiereTestdaten(){
        Kontakt k1 = new Kontakt("Erster");
        Kontakt k2 = new Kontakt("Zweiter");
        Kontakt k3 = new Kontakt("Dritter");
        k1.emailHinzufuegen("privat","heinz@irgend.wo");
        k2.emailHinzufuegen("privat","hugo@web.de");
        k3.emailHinzufuegen("privat","heino@orf.at");
        k1.telefonNummerHinzufuegen("privat","017048110815");
        k2.telefonNummerHinzufuegen("privat","015632546515");
        k3.telefonNummerHinzufuegen("privat","089 3395545");

        kontakte.add(k1);
        kontakte.add(k2);
        kontakte.add(k3);

    }

}

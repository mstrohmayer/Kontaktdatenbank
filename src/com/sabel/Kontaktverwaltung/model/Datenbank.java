package com.sabel.Kontaktverwaltung.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Matthias on 12.01.2017.
 */
@SuppressWarnings("ALL")
public class Datenbank {
    private final List<Kontakt> kontakte;

    public Datenbank() {
        kontakte = new ArrayList<>();
        generiereTestdaten();
    }

    public int anzahlKontakte() {
        return kontakte.size();
    }

    public Kontakt gibKontakt(int index) {
        return kontakte.get(index);
    }

    public void hinzufuegen(Kontakt kontakt) {
        kontakte.add(kontakt);
    }

    public Kontakt suchen(String name) {
        Kontakt foundKontakt = null;
        Iterator<Kontakt> it = kontakte.iterator();
        while (it.hasNext()){
            Kontakt k = it.next();
            if (k.getName().equals(name)){
                foundKontakt = k;
                return foundKontakt;
            }
        }
        return null;
    }

    private void generiereTestdaten() {
        Kontakt k1 = new Kontakt("Erster");
        Kontakt k2 = new Kontakt("Zweiter");
        Kontakt k3 = new Kontakt("Dritter");
        k1.emailHinzufuegen("heinz@irgend.wo");
        k2.emailHinzufuegen("hugo@web.de");
        k3.emailHinzufuegen("heino@orf.at");
        k1.telefonNummerHinzufuegen("017048110815");
        k2.telefonNummerHinzufuegen("015632546515");
        k3.telefonNummerHinzufuegen("089 3395545");
        kontakte.add(k1);
        kontakte.add(k2);
        kontakte.add(k3);
    }

}

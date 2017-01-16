package com.sabel.Kontaktverwaltung.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthias on 12.01.2017.
 */
public class Kontakt {
    private static int letzteID;

    private Map<String,String> emailAdressen;
    private int id;
    private String name;
    private Map<String,String> telefonNummern;
    private String vorname;

    public Kontakt(){
        super();
        id = ++letzteID;
        emailAdressen = new HashMap<>();
        telefonNummern = new HashMap<>();
    }

    public Kontakt(String name){
        this();
        this.name = name;
    }

    public void emailHinzufuegen(String art, String email){
        emailAdressen.put(art,email);
    }
    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getVorname(){
        return vorname;
    }

    public String gibEmail(String art){
        if(emailAdressen.containsKey(art)){
            return emailAdressen.get(art);
        }
        return null;
    }

    public String gibNummer(String art){
        if(telefonNummern.containsKey(art)){
            return telefonNummern.get(art);
        }
        return null;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }
    public void telefonNummerHinzufuegen(String art, String nummer){
        telefonNummern.put(art,nummer);
    }
}


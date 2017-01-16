package com.sabel.Kontaktverwaltung.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthias on 12.01.2017.
 */
@SuppressWarnings("ALL")
public class Kontakt {
    private static int letzteID;

    private final Map<String,String> emailAdressen;
    private final int id;
    private String name;
    private final Map<String,String> telefonNummern;
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

    public void emailHinzufuegen(String email){
        emailAdressen.put("privat",email);
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

    public String gibEmail(){
        if(emailAdressen.containsKey("privat")){
            return emailAdressen.get("privat");
        }
        return null;
    }

    public String gibNummer(){
        if(telefonNummern.containsKey("privat")){
            return telefonNummern.get("privat");
        }
        return null;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }
    public void telefonNummerHinzufuegen(String nummer){
        telefonNummern.put("privat",nummer);
    }
}


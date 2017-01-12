package com.sabel.Kontaktverwaltung.gui;

import com.sabel.Kontaktverwaltung.model.Kontakt;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.FlowLayout;

/**
 * Created by Matthias on 12.01.2017.
 */
public class KontaktPanel extends JPanel {
    private Kontakt kontakt;
    private JPanel[] jPanels;
    private JTextField[] jTextFields;
    private JLabel[] jLabels;

    public KontaktPanel(){
        initComponents();
    }
    private void initComponents() {
        String[] labelText = {"Name","Vorname","Telefon","Email"};
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        jPanels = new JPanel[4];
        jTextFields = new JTextField[4];
        jLabels = new JLabel[4];
        for (int i = 0; i < jLabels.length; i++){
            jPanels[i] = new JPanel();
            jPanels[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            jLabels[i] = new JLabel(labelText[i]);
            jTextFields[i] = new JTextField(30);
            jPanels[i].add(jLabels[i]);
            jPanels[i].add(jTextFields[i]);
            this.add(jPanels[i]);
        }
        //Borderscheißdreck

    }

    public Kontakt getKontakt(){
        return kontakt;
    }

    public void activateFields(){
        for (int i = 0; i<jTextFields.length;i++){
            jTextFields[i].setEnabled(true);
        }
    }
    public void deactivateFields(){
        for (int i = 0; i<jTextFields.length;i++){
            jTextFields[i].setEnabled(false);
        }
    }

    public void setKontakt(Kontakt kontakt){
        this.kontakt = kontakt;
        jTextFields[0].setText(kontakt.getName());
        jTextFields[1].setText(kontakt.getVorname());
        jTextFields[2].setText(kontakt.gibNummer("privat"));
        jTextFields[3].setText(kontakt.gibEmail("privat"));
    }

    public void storeKontakt(){
        kontakt.setName(jTextFields[0].getText());
        kontakt.setVorname(jTextFields[1].getText());
        kontakt.telefonNummerHinzufuegen("privat",jTextFields[2].getText());
        kontakt.emailHinzufuegen("privat",jTextFields[3].getText());
    }
}

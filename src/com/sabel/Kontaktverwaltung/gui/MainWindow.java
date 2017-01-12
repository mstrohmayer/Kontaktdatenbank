package com.sabel.Kontaktverwaltung.gui;

import com.sabel.Kontaktverwaltung.model.Datenbank;
import com.sabel.Kontaktverwaltung.model.Kontakt;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Matthias on 12.01.2017.
 */
public class MainWindow extends JFrame{
    private Container c;
    private Datenbank db;
    private KontaktPanel kontaktPanel;
    private Kontakt angezeigerKontakt;
    private JPanel jpWest, jpEast, jpSouth;
    private JToggleButton jtbNew, jtbEdit;
    private JButton jbLeft, jbRight, jbSearch;
    private int kontaktIndex;

    public MainWindow(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        this.initEvents();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void initComponents() {
        c = this.getContentPane();
        jpWest = new JPanel();
        jbLeft = new JButton("<");
        jpWest.add(jbLeft);
        c.add(jpWest, BorderLayout.WEST);
        jpEast = new JPanel();
        jbRight = new JButton(">");
        jpEast.add(jbRight);
        c.add(jpEast, BorderLayout.EAST);
        jpSouth = new JPanel();
        jbSearch = new JButton("Suchen");
        jtbEdit = new JToggleButton("Bearbeiten");
        jtbNew = new JToggleButton("Neu");
        jpSouth.add(jbSearch);
        jpSouth.add(jtbEdit);
        jpSouth.add(jtbNew);
        c.add(jpSouth, BorderLayout.SOUTH);  //Zeit wäre jetzt aus :-(
        kontaktPanel = new KontaktPanel();
        c.add(kontaktPanel, BorderLayout.CENTER);

        db = new Datenbank();
        angezeigerKontakt = db.gibKontakt(0);
        kontaktIndex = 0;
        kontaktPanel.setKontakt(angezeigerKontakt);
        kontaktPanel.deactivateFields();

    }

    private void update(){
        kontaktPanel.setKontakt(db.gibKontakt(kontaktIndex));
    }

    private void toggleButtons(boolean status){
        jbSearch.setEnabled(status);
        jbLeft.setEnabled(status);
        jbRight.setEnabled(status);
    }

    private void initEvents() {
        jbLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int size = db.anzahlKontakte();
                kontaktIndex = ((size + kontaktIndex) - 1) % size;
                update();
            }
        });
        jbRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int size = db.anzahlKontakte();
                kontaktIndex = (kontaktIndex + 1) % size;
                update();
            }
        });

        jbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String suche = JOptionPane.showInputDialog(MainWindow.this , "gesuchter Name: ");
                if  (suche == null){
                    return;
                }
                if (db.suchen(suche) != null){
                    angezeigerKontakt = db.suchen(suche);
                    kontaktIndex = angezeigerKontakt.getID();
                    update();
                }else{
                    JOptionPane.showMessageDialog(MainWindow.this,"Kontakt nicht gefunden!");
                    return;
                }
            }
        });
        jtbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jtbEdit.isSelected()){
                    jtbNew.setEnabled(false);
                    toggleButtons(false);
                    jtbEdit.setText("Fertig");
                    kontaktPanel.activateFields();
                }else {
                    kontaktPanel.storeKontakt();
                    kontaktPanel.deactivateFields();
                    jtbEdit.setText("Bearbeiten");
                    toggleButtons(true);
                    jtbNew.setEnabled(true);
                    update();
                }
            }
        });
        jtbNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jtbNew.isSelected()){
                    jtbEdit.setEnabled(false);
                    toggleButtons(false);
                    jtbNew.setText("Fertig");
                    kontaktPanel.activateFields();
                    kontaktPanel.setKontakt(new Kontakt());
                }else {
                    kontaktPanel.storeKontakt();
                    angezeigerKontakt = kontaktPanel.getKontakt();
                    db.hinzufuegen(angezeigerKontakt);
                    kontaktIndex = db.anzahlKontakte()-1;
                    kontaktPanel.deactivateFields();
                    jtbNew.setText("Neu");
                    toggleButtons(true);
                    jtbEdit.setEnabled(true);
                    update();
                }
            }
        });
    }
}

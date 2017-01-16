package com.sabel.Kontaktverwaltung.gui;

import com.sabel.Kontaktverwaltung.model.Datenbank;
import com.sabel.Kontaktverwaltung.model.Kontakt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Matthias on 12.01.2017.
 */
public class MainWindow extends JFrame{
    private Container c;
    private KontaktPanel kontaktPanel;
    private JPanel jpWest, jpEast, jpSouth, jpCenter;
    private JToggleButton jtbNew, jtbEdit;
    private JButton jbLeft, jbRight, jbSearch;

    private Datenbank db;
    private Kontakt angezeigerKontakt;
    private int index;

    public MainWindow(){
        super("Kontakte");
        db = new Datenbank();
        index = 0;
        angezeigerKontakt = db.gibKontakt(index);

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

        jpEast = new JPanel();
        jbRight = new JButton(">");
        jpEast.add(jbRight);

        jpSouth = new JPanel();
        jbSearch = new JButton("Suchen");
        jtbEdit = new JToggleButton("Bearbeiten");
        jtbNew = new JToggleButton("Neu");
        jpSouth.add(jbSearch);
        jpSouth.add(jtbEdit);
        jpSouth.add(jtbNew);

        jpCenter = new JPanel();
        kontaktPanel = new KontaktPanel();
        kontaktPanel.setKontakt(angezeigerKontakt);
        jpCenter.add(kontaktPanel);

        c.add(jpWest, BorderLayout.WEST);
        c.add(jpSouth, BorderLayout.SOUTH);
        c.add(jpEast, BorderLayout.EAST);
        c.add(jpCenter, BorderLayout.CENTER);
    }

    private void akutalisiereKontakt(){
        angezeigerKontakt = db.gibKontakt(index);
        kontaktPanel.setKontakt(angezeigerKontakt);
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
                index--;
                if (index == -1){
                    index = db.anzahlKontakte()-1;
                }
                akutalisiereKontakt();
            }
        });
        jbRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                index = (index + 1) % db.anzahlKontakte();
                akutalisiereKontakt();
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
                    index = angezeigerKontakt.getID()-1;
                    akutalisiereKontakt();
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

                    angezeigerKontakt = new Kontakt();
                    kontaktPanel.setKontakt(angezeigerKontakt);
                }else {
                    kontaktPanel.storeKontakt();
                    db.hinzufuegen(angezeigerKontakt);
                    index = db.anzahlKontakte()-1;

                    kontaktPanel.deactivateFields();
                    jtbNew.setText("Neu");
                    toggleButtons(true);
                    jtbEdit.setEnabled(true);
                }
            }
        });
    }
}

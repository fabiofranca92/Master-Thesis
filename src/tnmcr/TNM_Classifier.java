/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import static tnmcr.ColonAndRectum_GUI.cr;
import static tnmcr.ColonAndRectum_GUI.isNumeric;

/**
 *
 * @author fabiofranca
 */
public class TNM_Classifier extends javax.swing.JFrame {

    Preferences pref;

    /**
     * Creates new form Home
     */
    public TNM_Classifier() throws OWLOntologyCreationException {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Choose Ontology");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jButton1)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jButton1)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            pref = new Preferences();
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(TNM_Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OWL FILES", "owl", "owl");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String pathCsv = selectedFile.getAbsolutePath();
            System.out.println(pathCsv);
            if (pathCsv.contains("colorectal")) {
                if (pathCsv.contains("7")) {
                    try {
                        pref.setColonOntology_7(selectedFile);
                        turnColon();
                    } catch (OWLOntologyCreationException ex) {
                        Logger.getLogger(TNM_Classifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else if (pathCsv.contains("6")) {
                    try {
                        pref.setColonOntology_6(selectedFile);
                        turnColon();
                    } catch (OWLOntologyCreationException ex) {
                        Logger.getLogger(TNM_Classifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }else if(pathCsv.contains("breast")){
                try {
                    pref.setBreastOntology_7(selectedFile);
                    turnBreast();
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(TNM_Classifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
            }
                
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void turnColon(){
     pref.setOntologyIRI();
            
            Ontology_Handler oh = new Ontology_Handler(this.pref);

            ColonAndRectum colon = new ColonAndRectum(oh);
            ColonAndRectum_GUI gui = new ColonAndRectum_GUI(oh, colon);

            gui.setVisible(true);
            this.setVisible(false);
}
    
    public void turnBreast(){
         pref.setOntologyIRI();
            
            Ontology_Handler oh = new Ontology_Handler(this.pref);
            MammaryGland mg = new MammaryGland(oh);
           
            MammaryGland_GUI gui = new MammaryGland_GUI(oh, mg);

            gui.setVisible(true);
            this.setVisible(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TNM_Classifier().setVisible(true);
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(TNM_Classifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

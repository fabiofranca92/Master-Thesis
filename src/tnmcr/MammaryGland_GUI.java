/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import static tnmcr.ColonAndRectum_GUI.oh;

/**
 *
 * @author fabiofranca
 */
public class MammaryGland_GUI extends javax.swing.JFrame {

    OWLOntologyManager manager;
    static String iri;
    Preferences pref;
    static OWLDataFactory factory;
    static String iribtl2;
    static OWLReasoner reasoner;
    static MammaryGland mg;
    static Ontology_Handler oh;
    String quality;
    String value;
    Object place;
    boolean rlnAss;
    int nrMetas = 0;
    String[] Metas;
    DefaultListModel<Object> listModel = new DefaultListModel<>();
    boolean checkVersion;
    int version;
    boolean ulc = false;
    boolean infla = false;
    boolean pag = false;

    /**
     * Creates new form MammaryGland_GUI
     */
    public MammaryGland_GUI(Ontology_Handler oh, MammaryGland mg) {
        this.oh = oh;
        this.mg = mg;
        this.pref = oh.pref;
        this.version = pref.getVersion();
        initComponents();

        enableComponents(primary, false);
        enableComponents(inSitu, false);
        enableComponents(confinedPanel, false);
        enableComponents(invasivePanel, false);

        quality = "AssessmentQuality";
        value = "NoAssessment";

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        primaryTumor = new javax.swing.JComboBox();
        regionalLN = new javax.swing.JComboBox();
        distantMetastasis = new javax.swing.JComboBox();
        primary = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        primaryEvidence = new javax.swing.JComboBox();
        confined = new javax.swing.JCheckBox();
        invasive = new javax.swing.JCheckBox();
        situ = new javax.swing.JCheckBox();
        inSitu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inSituExtension = new javax.swing.JComboBox();
        paget = new javax.swing.JCheckBox();
        confinedPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        size = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        invasivePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        extension = new javax.swing.JComboBox();
        inflammatory = new javax.swing.JCheckBox();
        ulcerating = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        classify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        explain = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Assessment"));

        jLabel2.setText("Primary Tumor");

        jLabel3.setText("Regional Lymph Nodes");

        jLabel4.setText("Distant Metastasis");

        primaryTumor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        primaryTumor.setSelectedIndex(1);
        primaryTumor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primaryTumorActionPerformed(evt);
            }
        });

        regionalLN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        regionalLN.setSelectedIndex(1);

        distantMetastasis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        distantMetastasis.setSelectedIndex(1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(primaryTumor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(regionalLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(distantMetastasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(primaryTumor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regionalLN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(distantMetastasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        primary.setBorder(javax.swing.BorderFactory.createTitledBorder("Primary Tumor"));

        jLabel5.setText("Evidence Of Primary Tumor");

        primaryEvidence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        primaryEvidence.setSelectedIndex(1);
        primaryEvidence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primaryEvidenceActionPerformed(evt);
            }
        });

        confined.setText("Confined");
        confined.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confinedActionPerformed(evt);
            }
        });

        invasive.setText("Invasive");
        invasive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invasiveActionPerformed(evt);
            }
        });

        situ.setText("Carcinoma In Situ");
        situ.setToolTipText("");
        situ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                situActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout primaryLayout = new javax.swing.GroupLayout(primary);
        primary.setLayout(primaryLayout);
        primaryLayout.setHorizontalGroup(
            primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(primaryLayout.createSequentialGroup()
                        .addGroup(primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(confined)
                            .addGroup(primaryLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(primaryEvidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(primaryLayout.createSequentialGroup()
                        .addComponent(situ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invasive)
                        .addGap(137, 137, 137))))
        );
        primaryLayout.setVerticalGroup(
            primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(primaryEvidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(primaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confined)
                    .addComponent(invasive)
                    .addComponent(situ))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        inSitu.setBorder(javax.swing.BorderFactory.createTitledBorder("Carcinoma In Situ"));

        jLabel1.setText("Confined :");

        inSituExtension.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lobule of Lactiferous Gland", "Lactiferous Duct", "Other" }));
        inSituExtension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inSituExtensionActionPerformed(evt);
            }
        });

        paget.setText("Paget Disease");
        paget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inSituLayout = new javax.swing.GroupLayout(inSitu);
        inSitu.setLayout(inSituLayout);
        inSituLayout.setHorizontalGroup(
            inSituLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inSituLayout.createSequentialGroup()
                .addComponent(inSituExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paget))
            .addGroup(inSituLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inSituLayout.setVerticalGroup(
            inSituLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inSituLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inSituLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inSituExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paget))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confinedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Confined Primary Tumor"));

        jLabel8.setText("Size");

        size.setText("(mm)");
        size.setToolTipText("");
        size.setMinimumSize(new java.awt.Dimension(115, 28));
        size.setSize(new java.awt.Dimension(115, 28));
        size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeActionPerformed(evt);
            }
        });

        jLabel9.setText("mm");

        javax.swing.GroupLayout confinedPanelLayout = new javax.swing.GroupLayout(confinedPanel);
        confinedPanel.setLayout(confinedPanelLayout);
        confinedPanelLayout.setHorizontalGroup(
            confinedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confinedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confinedPanelLayout.setVerticalGroup(
            confinedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confinedPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(confinedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        invasivePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Invasive Primary Tumor"));

        jLabel7.setText("Extension of Invasion :");

        extension.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chest Wall", "Skin", " ", " " }));
        extension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extensionActionPerformed(evt);
            }
        });

        inflammatory.setText("Inflammatory");
        inflammatory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inflammatoryActionPerformed(evt);
            }
        });

        ulcerating.setText("Ulcerating");
        ulcerating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulceratingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout invasivePanelLayout = new javax.swing.GroupLayout(invasivePanel);
        invasivePanel.setLayout(invasivePanelLayout);
        invasivePanelLayout.setHorizontalGroup(
            invasivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invasivePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(invasivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(invasivePanelLayout.createSequentialGroup()
                        .addComponent(extension, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(invasivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ulcerating)
                            .addComponent(inflammatory))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        invasivePanelLayout.setVerticalGroup(
            invasivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invasivePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(invasivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inflammatory))
                .addGap(12, 12, 12)
                .addComponent(ulcerating)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Classification"));

        classify.setText("Classify");
        classify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classifyActionPerformed(evt);
            }
        });

        explain.setColumns(20);
        explain.setRows(5);
        jScrollPane1.setViewportView(explain);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classify)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(classify))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(invasivePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(primary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inSitu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confinedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(primary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confinedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inSitu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invasivePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classifyActionPerformed
        oh.eraseIndividuals();
        explain.setText("");
        
        if(inSituExtension.isEnabled()) place = inSituExtension.getSelectedItem().toString();
        if(extension.isEnabled()) place = extension.getSelectedItem().toString();
        if(inflammatory.isEnabled()) infla = inflammatory.isSelected();
        if(ulcerating.isEnabled()) ulc = ulcerating.isSelected();
        if(paget.isEnabled()) pag = paget.isSelected();
        
        if (size.getText().equals("(mm)")) {
            size.setText("0");
        }
        float length = 0;
        if (!isNumeric(size.getText())) {
            JOptionPane.showMessageDialog(new JFrame("Error"), "Invalid Size");

        } else {

            length = Float.parseFloat(size.getText());

            try {
                mg.createIndividualPrimaryTumor("PrimaryTumor", place, quality, value, infla,ulc,pag,length);
            } catch (OWLOntologyStorageException ex) {
                Logger.getLogger(MammaryGland_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            oh.startReasoner();

            ArrayList<OWLClass> Tclassif = oh.classify("PrimaryTumor", "Breast");
            OWLClass res = mg.prioritizeT(Tclassif);
            explain.append(mg.replaceIri(res));
//            for (OWLClass i : Tclassif) {
//                if (mg.replaceIri(i).replace("BreastTNM_", "").contains("p")) {
//                    explain.append(mg.replaceIri(i).replace("BreastTNM_", "") + " ");
//                }
//            }
        }
    }//GEN-LAST:event_classifyActionPerformed

    private void sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeActionPerformed

    private void inflammatoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inflammatoryActionPerformed
       
    }//GEN-LAST:event_inflammatoryActionPerformed

    private void ulceratingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulceratingActionPerformed
      
    }//GEN-LAST:event_ulceratingActionPerformed

    private void primaryTumorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryTumorActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        Object newItem = cb.getSelectedItem();

        if (newItem.equals("Yes")) {
            enableComponents(primary, true);
            quality = "AssessmentQuality";
            value = "NoEvidence";
            situ.setEnabled(false);
            invasive.setEnabled(false);
            confined.setEnabled(false);
            primaryEvidence.setSelectedItem("No");
        } else {
            quality = "AssessmentQuality";
            value = "NoAssessment";
            enableComponents(primary, false);
           enableComponents(inSitu, false);
            enableComponents(confinedPanel, false);
            enableComponents(invasivePanel, false);   
            situ.setSelected(false);
        }

    }//GEN-LAST:event_primaryTumorActionPerformed

    private void primaryEvidenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryEvidenceActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        Object newItem = cb.getSelectedItem();

        if (newItem.equals("Yes")) {
            enableComponents(inSitu, true);
            situ.setEnabled(true);
            invasive.setEnabled(true);
            confined.setEnabled(true);
            quality = "Confinement";
            value = "CarcinomaInSituValueRegion";
            situ.setSelected(true);
            paget.setEnabled(false);
        } else {

            situ.setEnabled(false);
            invasive.setEnabled(false);
            confined.setEnabled(false);
            quality = "AssessmentQuality";
            value = "NoEvidence";
            situ.setSelected(false);
            enableComponents(inSitu, false);
        }
    }//GEN-LAST:event_primaryEvidenceActionPerformed

    private void extensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extensionActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        Object newItem = cb.getSelectedItem();

        place = mg.verifyPlace(newItem);
    }//GEN-LAST:event_extensionActionPerformed

    private void situActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_situActionPerformed

        JCheckBox cb = (JCheckBox) evt.getSource();
        boolean newItem = cb.isSelected();

        if (newItem) {
            quality = "Confinement";
            value = "CarcinomaInSituValueRegion";
            enableComponents(inSitu, true);
            size.setText("(mm)");
            infla = false;
            ulc = false;
            confined.setSelected(false);
            invasive.setSelected(false);
            enableComponents(invasivePanel, false);
            enableComponents(confinedPanel, false);
            paget.setEnabled(false);
        }
        if (!newItem) {
            enableComponents(inSitu, false);
            paget.setSelected(false);
        }
    }//GEN-LAST:event_situActionPerformed

    private void inSituExtensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inSituExtensionActionPerformed
        if (inSituExtension.getSelectedItem().toString().equals("Other")) {
            paget.setEnabled(true);
        } else {
            paget.setEnabled(false);
            paget.setSelected(false);
        }
    }//GEN-LAST:event_inSituExtensionActionPerformed

    private void confinedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confinedActionPerformed

        JCheckBox cb = (JCheckBox) evt.getSource();
        boolean newItem = cb.isSelected();

        if (newItem) {
            quality = "Confinement";
            value = "Confined";
            enableComponents(confinedPanel, true);
            size.setText("(mm)");
            invasive.setSelected(false);
            situ.setSelected(false);
            enableComponents(invasivePanel, false);
            enableComponents(inSitu, false);
            paget.setSelected(false);
        }
        if (!newItem) {
            enableComponents(confinedPanel, false);
            size.setText("(mm)");
        }
    }//GEN-LAST:event_confinedActionPerformed

    private void invasiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invasiveActionPerformed
        JCheckBox cb = (JCheckBox) evt.getSource();
        boolean newItem = cb.isSelected();

        if (newItem) {
            quality = "Confinement";
            value = "Invasive";
            enableComponents(invasivePanel, true);
            size.setText("(mm)");
            confined.setSelected(false);
            situ.setSelected(false);
            enableComponents(inSitu, false);
            enableComponents(confinedPanel, false);
            paget.setSelected(false);
        }
        if (!newItem) {
            enableComponents(invasivePanel, false);

            size.setText("(mm)");
        }
    }//GEN-LAST:event_invasiveActionPerformed

    private void pagetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagetActionPerformed


    }//GEN-LAST:event_pagetActionPerformed

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton classify;
    private javax.swing.JCheckBox confined;
    private javax.swing.JPanel confinedPanel;
    private javax.swing.JComboBox distantMetastasis;
    private javax.swing.JTextArea explain;
    private javax.swing.JComboBox extension;
    private javax.swing.JPanel inSitu;
    private javax.swing.JComboBox inSituExtension;
    private javax.swing.JCheckBox inflammatory;
    private javax.swing.JCheckBox invasive;
    private javax.swing.JPanel invasivePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox paget;
    private javax.swing.JPanel primary;
    private javax.swing.JComboBox primaryEvidence;
    private javax.swing.JComboBox primaryTumor;
    private javax.swing.JComboBox regionalLN;
    private javax.swing.JCheckBox situ;
    private javax.swing.JTextField size;
    private javax.swing.JCheckBox ulcerating;
    // End of variables declaration//GEN-END:variables
}
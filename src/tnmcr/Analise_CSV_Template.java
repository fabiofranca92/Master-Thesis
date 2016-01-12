/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.awt.FlowLayout;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import static tnmcr.ColonAndRectum_GUI.cr;

/**
 *
 * @author fabiofranca
 */
public class Analise_CSV_Template {

    boolean deposits = false;
    boolean peritonuem = false;
    static ColonAndRectum colon;
    static Ontology_Handler oh;
    String pathCsv;
    String nrColumn;
    String out;
    String[] classification = new String[3];
    int pAssessment = 1;
    int pEvidence = 1;
    int pConfinement = 0;
    boolean rAssessment = false;
    int version;
    public Analise_CSV_Template(ColonAndRectum colon, Ontology_Handler oh, String pathCsv, int version) {
        this.colon = colon;
        this.oh = oh;
        this.pathCsv = pathCsv;
        this.version = version;
        
    }
    
    public void main() throws OWLOntologyStorageException, FileNotFoundException, IOException {
        
        FileReader fr = new FileReader(pathCsv);
        BufferedReader br = new BufferedReader(fr);
        
        File fileOut = new File("/Users/fabiofranca/Desktop/colon_classified.csv");
        FileWriter fw = new FileWriter(fileOut);
        CSVWriter writer = new CSVWriter(fw);
        CSVReader reader = new CSVReader(br);
        fileOut.setWritable(true);
        
        fw.write("Assessment,Evidence,Extension of Invasion,Assessment,Nr of Lymph Nodes,Tumor Positive Lymph Nodes,Presence of Tumor Deposits/ Satellite(s),Evidence of Metastasis,Nr of Metastasis,Peritoneum,Pathologist6T,Pathologist6N,Pathologist6M,Pathologist7T,Pathologist7N,Pathologist7M,V6T,V6N,V6M,V7T,V7N,V7M\n");
        //Read CSV line by line and use the string array as you want
        String[] next;
        
       br.readLine();
        br.readLine();
        
        while ((next = reader.readNext()) != null) {
            
            oh.eraseIndividuals();

            //CsvReader has a small bug that needs this transformation
            String ne = Arrays.toString(next).replace("[", "").replace("]", "");
            String[] csvLine = new String[23];            
         //   System.out.println(ne);
            
            for (int i = 0; i < 16; i++) {
                csvLine[i] = ne.split("\\;")[i + 1];
         //       System.out.println(i);
            }
            //
            
            createIndividuals(version, csvLine);
            
            
            csvLine = classifyIndividuals(version, csvLine, writer);
  
            System.out.println(Arrays.toString(csvLine));
            //  System.out.println(Arrays.toString(csvLine));
           
            
            
            writer.writeNext(csvLine);
            writer.flush();
        
        
       
            
           
    }
        writer.close();
    }
    public void createIndividuals(int version, String[] csvLine) throws OWLOntologyStorageException{
          System.out.println(Arrays.toString(csvLine));
            //  System.out.println(Arrays.toString(csvLine));
            if (csvLine[0].equals("no")) {
                
                pAssessment = 0;
                
            } else if (csvLine[1].equals("no")) {
                pEvidence = 1;
                
            } else if (csvLine[2].equals("confined") || csvLine[2].equals("lamina propria") || csvLine[2].equals("intraepithelial")) {
                pConfinement = 0;
            } else {
                pConfinement = 1;
                
            }
            
            colon.createIndividualPrimaryTumor("PrimaryTumor", csvLine[2], pAssessment, pEvidence, pConfinement);
            
                if(csvLine[3].equals("yes")){
                    rAssessment = true;
                }else{
                    rAssessment = false;
                }
         
                if (csvLine[6].equals("yes")) {
                    deposits = true;
                } else {
                    deposits = false;
                }
                
                colon.createIndividualRegionalLymphNodes("RegionalLymphNodes", Integer.parseInt(csvLine[5]), rAssessment, version, deposits);
                
          
            
                if (csvLine[9].equals("yes")) {
                    peritonuem = true;
                } else {
                   
                    peritonuem = false;
                }
                colon.createIndividualMetastasis("DistantMetastasis", Integer.parseInt(csvLine[8]), peritonuem, version);
          
            
            oh.runReasoner(false);
            
            
        
    }
    
    public String[] classifyIndividuals(int version, String[] csvLine, CSVWriter writer) throws IOException{
        
         oh.runReasoner(false);
                  ArrayList<OWLClass> Tclas = oh.classify("PrimaryTumor", "Colon");
          
           
            
            for (OWLClass t : Tclas) {
                if (cr.replaceIri(t).replace("ColonRectumTNM_", "").contains("p")) {
                    System.out.println(cr.replaceIri(t).replace("ColonRectumTNM_", "") + " ");
                    if(version == 6){
                    csvLine[16] = cr.replaceIri(t).replace("ColonRectumTNM_", "");
                    }else{
                    csvLine[19] = cr.replaceIri(t).replace("ColonRectumTNM_", "");
                }
                }
            }
       ArrayList<OWLClass>       Nclas = oh.classify("RegionalLymphNodes", "Colon");
            for (OWLClass n : Nclas) {
                if (cr.replaceIri(n).replace("ColonRectumTNM_", "").contains("p")) {
                    //         System.out.println(cr.replaceIri(n).replace("ColonRectumTNM_", "") + " ");
                    if(version == 6){
                    csvLine[17] = cr.replaceIri(n).replace("ColonRectumTNM_", "");
                    }else{
                         csvLine[20] = cr.replaceIri(n).replace("ColonRectumTNM_", "");
                    }
                }
                
            }
         ArrayList<OWLClass>     Mclas = oh.classify("DistantMetastasis", "Colon");
            for (OWLClass m : Mclas) {

                //            System.out.println(cr.replaceIri(m).replace("ColonRectumTNM_", "") + " ");
                if(version == 6){
                csvLine[18] = cr.replaceIri(m).replace("ColonRectumTNM_", "");
                }else{
                     csvLine[21] = cr.replaceIri(m).replace("ColonRectumTNM_", ""); 
                }
            }
            
            
        return csvLine;
    }
   
}

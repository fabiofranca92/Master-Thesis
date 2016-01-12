/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
/**
 *
 * @author fabiofranca
 */
public class Preferences {

    private String btl2IRI = "http://purl.org/biotop/btl2.owl#" ;
     
    public String ontologyPath;
    public OWLOntologyManager manager  ;
    public OWLOntology ontology;
    public OWLOntologyFactory factory ;
    public IRI iri;
    public IRI mainIri = IRI.create("http://purl.org/tnmo/TNM-O.owl");
    public String mainPath = "/Users/fabiofranca/NetBeansProjects/tmnCR/owl/tnm_main.owl";
    public int version;
    


   

    public Preferences() throws OWLOntologyCreationException {
        this.manager = OWLManager.createOWLOntologyManager();  
        

        
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
        
    public String getMainIri() {
    return mainIri.toString().concat("#");
    }
    
    public OWLOntology getOntology() {
        return ontology;
    }

    public void setColonOntology_6(File file) throws OWLOntologyCreationException {
        
        this.ontology = manager.loadOntologyFromOntologyDocument(file);
        manager.addIRIMapper(new SimpleIRIMapper(mainIri, IRI.create(mainPath)));
        this.setVersion(6);
    }
    
      public void setColonOntology_7(File file) throws OWLOntologyCreationException {
        
        this.ontology = manager.loadOntologyFromOntologyDocument(file);
        
        OWLImportsDeclaration importDeclaraton = manager.getOWLDataFactory().getOWLImportsDeclaration(IRI.create("/Users/fabiofranca/NetBeansProjects/tmnCR/owl/tnm_main.owl"));
        
        manager.addIRIMapper(new SimpleIRIMapper(IRI.create("owl/colon_rectumTNM_7.owl"),IRI.create("file:owl/tnm_main.owl")));
        
         this.setVersion(7);
    }
      
      public void setBreastOntology_7(File file) throws OWLOntologyCreationException {
        
        this.ontology = manager.loadOntologyFromOntologyDocument(file);
        manager.addIRIMapper(new SimpleIRIMapper(mainIri, IRI.create(mainPath)));
         this.setVersion(7);
    }
      
    public String getOntologyIri() {
        
       return this.iri.toString().concat("#");
       
     
    }
    public void setOntologyIRI(){
       this.iri = ontology.getOntologyID().getOntologyIRI();
    }
    
    
    public String getBtl2IRI() {
        return btl2IRI;
    }

    public void setBtl2IRI(String btl2IRI) {
        this.btl2IRI = btl2IRI;
    }

 
    public void setOntologyPath(String ontologyPath) {
        this.ontologyPath = ontologyPath;
    }
           
     public String getOntologyPath() {
        return ontologyPath;
     }

 

   
    
   
    
    
}

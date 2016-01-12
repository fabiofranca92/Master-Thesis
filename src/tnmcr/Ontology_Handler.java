/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import java.util.Set;

import javax.swing.SwingUtilities;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;

import org.semanticweb.owlapi.model.OWLClass;

import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;

import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.OWLEntityRemover;

/**
 *
 * @author fabiofranca
 */
public class Ontology_Handler {

   

    public OWLReasoner reasoner;
    public OWLOntology ontology;
    public OWLOntologyManager manager;
    public Preferences pref;
    String filepath;
    String iri;
    String iribtl2;
    File file;
    String mainIri;
 
    

    public Ontology_Handler(Preferences pref){
        this.pref = pref;
       
        this.iri = pref.getOntologyIri();
        this.iribtl2 = pref.getBtl2IRI();
 
        this.ontology = pref.getOntology();
        this.manager = pref.manager;
        this.mainIri = pref.getMainIri();
        
    }

    
    
   
   
    
 
    protected void loadOntology() {
        try {
            
            manager = OWLManager.createOWLOntologyManager();
            
            // Now do the loading
            ontology = manager.loadOntologyFromOntologyDocument(IRI.create(file));

        } catch (final Exception e) {
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            };
            SwingUtilities.invokeLater(runnable);

        }
    }
    
  


    public void startReasoner(){
        
        runReasoner(false);
        isConsistent();
    }
    protected Set<OWLClass> getSubClasses(String className , String iri) {

        OWLDataFactory fac = manager.getOWLDataFactory();
        
        OWLClass cl = fac.getOWLClass(IRI.create(iri + className));
       // System.out.println(iri+className);
        Set<OWLClass> subClses = reasoner.getSubClasses(cl, false).getFlattened();
//        for(OWLClass x  : subClses){
//             System.out.println(x.toString());
//        }
       
        return subClses;
    }

    protected void addIndividual(String name) {

        OWLDataFactory factory = manager.getOWLDataFactory();

        OWLIndividual individual = factory.getOWLNamedIndividual(IRI
                .create(iri + name));

    }

    public void getTypesIndividual(String indname) {

      

        OWLDataFactory factory = manager.getOWLDataFactory();

        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + indname));

        Set<OWLClass> typesSet = reasoner.getTypes(ind, false).getFlattened();

        //  System.out.println("\nindividual: " + ind);
        Object[] inferredTypes = typesSet.toArray();
        for (int i = 0; i < inferredTypes.length; i++) {
            OWLClassExpression inf = (OWLClassExpression) inferredTypes[i];
                //System.out.println("inferred types" + inf.toString().replace(iri, "").replace(iribtl2, ""));

        }
    }

    public Set[] getClasses(String organ){
        Set[] res = new Set[2];
        
        if(organ.equals("Colon")){
            res[0] = getColonAndRectumTumorClasses();
            res[1] = getColonAndRectumClassificationClasses();
        }else if(organ.equals("Breast")){
            res[0] = getBreastTumorClasses();
            res[1] = getBreastTumorClassificationClasses();
        }
        return res;
    }
    public ArrayList<OWLClass> classify(String indname,String Organ) {
        
        ArrayList<OWLClass> res = new ArrayList<>();
        ArrayList<OWLClass> p = new ArrayList<>();

        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + indname));
       // System.out.println("tumor classes");
        
        Set[] classes = getClasses(Organ);
        Set<OWLClass> tumorclasses = classes[0];
        Set<OWLClass> classifications = classes[1];
         // System.out.println("done");
        Set<OWLClass> typesSet = reasoner.getTypes(ind, false).getFlattened();

        // System.out.println("\nindividual: " + ind);
        Object[] inferredTypes = typesSet.toArray();
        for (int i = 0; i < inferredTypes.length; i++) {
            OWLClassExpression inf = (OWLClassExpression) inferredTypes[i];
       //     System.out.println("Inferred "+inferredTypes[i].toString());

            for (OWLClass tc : tumorclasses) {

             //     System.out.println("TUMOR  "+tc.toString());
                if (tc.equals(inf)) {
                    p.add(tc);

                }
            }
        }

        for (OWLClass inf : p) {

           
            Set<OWLClassAxiom> set = ontology.getAxioms(inf);

            for (OWLClassAxiom x : set) {
                OWLAxiom axiom = x.getAxiomWithoutAnnotations();

                for (OWLClass classif : classifications) {
                    //System.out.println(classif.toString());
                    if (axiom.toString().contains(classif.toString())) {
                        res.add(classif);
                       // System.out.println(classif.toString());
                    }
                }
                   //System.out.println(axiom.toString());

            }
        }
        return eliminateDuplicates(res);
    }

    protected void saveOntology() throws OWLOntologyStorageException {
        File file = new File("/Users/fabiofranca/Desktop/local.owl");
        manager.saveOntology(ontology, IRI.create(file.toURI()));
    }

    public void runReasoner(boolean b) {
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
        OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
        Configuration configuration = new Configuration();

        configuration.ignoreUnsupportedDatatypes = true;
        if (b) {
            reasoner = reasonerFactory.createReasoner(ontology, config);
        } else {
            reasoner = reasonerFactory.createReasoner(ontology);
        }
    }

    public void eraseIndividuals() {
        OWLEntityRemover remover = new OWLEntityRemover(manager, Collections.singleton(ontology));
        for (OWLNamedIndividual ind : ontology.getIndividualsInSignature()) {
            ind.accept(remover);
        }
        manager.applyChanges(remover.getChanges());
        remover.reset();
    }

    public void isConsistent() {
        reasoner.precomputeInferences();
        boolean consistent = reasoner.isConsistent();
        System.out.println("Consistent: " + consistent);
    }

    private Set<OWLClass> getColonAndRectumTumorClasses() {
        Set<OWLClass> res = getSubClasses("ColonAndRectumTumor",iri);
        res.addAll(getSubClasses("TumorOfColonAndRectumAggregate",iri));
//        for(OWLClass cl : res){
//            System.out.println(cl.toString());
//        }
        return res;
    }

    private Set<OWLClass> getColonAndRectumClassificationClasses() {
        Set<OWLClass> res = getSubClasses("RepresentationalUnitInColonRectumTNMClassification",iri);
//         for(OWLClass cl : res){
//            System.out.println(cl.toString());
//        }
      
        return res;
    }
 private Set<OWLClass> getBreastTumorClasses() {
    // System.out.println(iri);
        Set<OWLClass> res = getSubClasses("BreastTumor",iri);
        res.addAll(getSubClasses("BreastTumorAggregate",iri));
//        for(OWLClass cl : res){
//            System.out.println(cl.toString());
//        }
        return res;
    }

    private Set<OWLClass> getBreastTumorClassificationClasses() {
        Set<OWLClass> res = getSubClasses("RepresentationalUnitInBreastTNMClassification",iri);
//         for(OWLClass cl : res){
//            System.out.println(cl.toString());
//        }
      
        return res;
    }

    private ArrayList<OWLClass> eliminateDuplicates (ArrayList<OWLClass> res) {

       
// add elements to al, including duplicates
        HashSet hs = new HashSet();
        hs.addAll(res);
        res.clear();
        res.addAll(hs);

        return res;
    }

}

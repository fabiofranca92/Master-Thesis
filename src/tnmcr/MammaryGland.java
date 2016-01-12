/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author fabiofranca
 */
public class MammaryGland {

    OWLOntologyManager manager;
    OWLOntology ontology;
    Preferences pref;
    Ontology_Handler oh;
    String iri;
    String iribtl2;
    String main;

    public MammaryGland(Ontology_Handler oh) {
        this.oh = oh;
        this.iri = oh.iri;
        this.iribtl2 = oh.iribtl2;
        this.manager = oh.manager;
        this.ontology = oh.ontology;
        this.main = oh.mainIri;
    }

    public OWLClassExpression AddQuality(String quality, String regionValue) {
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLObjectProperty isBearerOf = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isBearerOf"));
        OWLObjectProperty projectsOnto = factory.getOWLObjectProperty(IRI.create(iribtl2 + "projectsOnto"));

        Set<OWLClassExpression> val = new HashSet<>();
        Set<OWLClassExpression> qual = new HashSet<>();

        OWLClassExpression cQuality = factory.getOWLClass(IRI.create(iri + quality));
        qual.add(cQuality);

        //SomeQuality
        OWLClassExpression TumorQualityValuesRegion = factory.getOWLClass(IRI.create(iri + regionValue));

        OWLClassExpression someTumorQuality = factory.getOWLObjectSomeValuesFrom(projectsOnto, TumorQualityValuesRegion);
        val.add(someTumorQuality);
        //OnlyQuality
        OWLClassExpression allTumorQuality = factory.getOWLObjectAllValuesFrom(projectsOnto, TumorQualityValuesRegion);
        val.add(allTumorQuality);

        OWLObjectIntersectionOf valuesIntersect = factory.getOWLObjectIntersectionOf(val);
        qual.add(valuesIntersect);
        OWLObjectIntersectionOf qualityValuesIntersect = factory.getOWLObjectIntersectionOf(qual);
        OWLClassExpression qualityPart = factory.getOWLObjectSomeValuesFrom(isBearerOf, qualityValuesIntersect);

        //    System.out.println("final  :" + qualityPart.toString().replace(iri, "").replace(iribtl2, ""));
        return qualityPart;

    }

    private OWLClassExpression addNotPlace(String place) {

        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClassExpression placePart;
        OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));
        OWLClassExpression tumorPlace = factory.getOWLClass(IRI.create(iri + place));
        placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, tumorPlace);
        return placePart.getObjectComplementOf();
    }

    private OWLClassExpression addSomePlace(String place) {
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClassExpression placePart;
        OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));

        if (place.equals("Organ")) {
            OWLClassExpression tumorPlace = factory.getOWLClass(IRI.create(main + place));
            placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, tumorPlace);
            return placePart;
        } else {
            OWLClassExpression tumorPlace = factory.getOWLClass(IRI.create(iri + place));
            placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, tumorPlace);
            return placePart;
        }
    }

    private OWLClassExpression addOnlyPlace(String place) {
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClassExpression placePart;
        OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));

        OWLClassExpression tumorPlace = factory.getOWLClass(IRI.create(iri + place));
        placePart = factory.getOWLObjectAllValuesFrom(isIncludedIn, tumorPlace);
        return placePart;
    }

    public void createIndividualPrimaryTumor(String indname, Object place, String quality, String value, boolean infla, boolean ulce, boolean paget, float size) throws OWLOntologyStorageException {
        OWLDataFactory factory = manager.getOWLDataFactory();
        Set<OWLClassExpression> res = new HashSet<>();

        OWLClassExpression tumorType = factory.getOWLClass(IRI.create(iri + "BreastTumor"));
        res.add(tumorType);

        OWLClassExpression conf = AddQuality(quality, value);
        res.add(conf);
        if (paget) {
            tumorType = factory.getOWLClass(IRI.create(iri + "CarcinomaInSitu_Paget"));
            res.add(tumorType);
        } else {

            if (infla) {
                OWLClassExpression qual = AddQuality("TumorQuality", "Inflammatory");
                res.add(qual);
            }
            if (ulce) {
                OWLClassExpression qual = AddQuality("TumorQuality", "Ulcerating");
                res.add(qual);
            }
            if (place != null) {

                OWLClassExpression placePart = addSomePlace(verifyPlace(place).toString());
                res.add(placePart);
            }
            if (size != 0) {
                OWLClassExpression qual = addSize(size);
                res.add(qual);
            }
        }
        createIndividual(indname, factory, res);

    }

    public OWLClassExpression addSize(float size) {
        OWLClassExpression qual = null;

        if (size > 50) {
            qual = AddQuality("Length", "LengthAbove5cm");
        }
        if (size <= 50 && size > 20) {
            qual = AddQuality("Length", "LengthBetween2and5cm");
        }
        if (size <= 20) {

            if (size <= 1) {
                qual = AddQuality("Length", "LengthBelow0.1cm");
            }
            if (size > 1 && size <= 5) {
                qual = AddQuality("Length", "LengthBetween0.1and0.5cm");
            }
            if (size > 5 && size <= 10) {
                qual = AddQuality("Length", "LengthBetween0.5and1cm");
            }
            if (size > 10 && size <= 20) {
                qual = AddQuality("Length", "LengthBetween1and2cm");
            }
        }
        System.out.print(qual.toString());
        return qual;
    }

    public Object verifyPlace(Object place) {

        if (place != null) {
            if (place.equals("Lobule of Lactiferous Gland")) {
                place = "LobuleOfLactiferousGland";
            }
            if (place.equals("Lactiferous Duct")) {
                place = "LactiferousDuct";
            }
            if (place.equals("Chest Wall")) {
                place = "ChestWall";
            }
            if (place.equals("Skin")) {
                place = "Skin";
            }
        }

        return place;
    }

    public void createIndividual(String indname, OWLDataFactory factory, Set<OWLClassExpression> res) throws OWLOntologyStorageException {

        OWLObjectIntersectionOf allParts = factory.getOWLObjectIntersectionOf(res);
        OWLIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + indname));
        OWLClassAssertionAxiom ax = factory.getOWLClassAssertionAxiom(allParts, ind);
        manager.addAxiom(ontology, ax);
        manager.saveOntology(ontology);
    }

    public String replaceIri(OWLClass owlClass) {

        String res = owlClass.toString().replace(iri, "").replace("<", "").replace(">", "");

        return res;
    }

    public OWLClass[] orderedArray() {
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClass[] ordered = new OWLClass[16];

        ordered[0] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pTisDCIS"));
        ordered[1] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pTisLCIS"));
        ordered[2] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pTisPaget"));
        ordered[3] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pTis"));
        ordered[4] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT4d"));
        ordered[5] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT4c"));
        ordered[6] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT4b"));
        ordered[7] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT4a"));
        ordered[8] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT4"));
        ordered[9] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT3"));
        ordered[10] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT2"));
        ordered[11] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT1c"));
        ordered[12] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT1b"));
        ordered[13] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT1a"));
        ordered[14] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pT0"));
        ordered[15] = factory.getOWLClass(IRI.create(iri + "BreastTNM_pTX"));

        return ordered;
    }

    public OWLClass prioritizeT(ArrayList<OWLClass> raw) {
        OWLClass[] ordered = orderedArray();
        OWLClass[] unordered = new OWLClass[raw.size()];
        OWLClass res =null;
        int z = 0;
        for(OWLClass x : raw){
            unordered[z] = x;
            z++;
        }
        
        for (int i = 0; i < ordered.length; i++) {

            for (int j = 0; j < unordered.length; j++) {
                if (unordered[j] == ordered[i]) {
                    res = unordered[j];
                    System.out.println("RES "+res.toString()); 
                    return res;
                }
            }
        }
      return res ;
    }
}

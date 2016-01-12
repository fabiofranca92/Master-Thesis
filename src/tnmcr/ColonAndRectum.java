/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmcr;

import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.AddAxiom;
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
public class ColonAndRectum {

    OWLOntologyManager manager;
    OWLOntology ontology;
    Preferences pref;
    Ontology_Handler oh;
    String iri;
    String iribtl2;
    String main;

    public ColonAndRectum(Ontology_Handler oh) {
        this.oh = oh;
        this.iri = oh.iri;
        this.iribtl2 = oh.iribtl2;
        this.manager = oh.manager;
        this.ontology = oh.ontology;
        this.main = oh.mainIri;
    }

    //Defines the num
    private OWLClass[] rln_7(int nrlymph) {
        OWLClass[] res = new OWLClass[2];
        OWLDataFactory factory = manager.getOWLDataFactory();

        if (nrlymph < 4) {

            res[0] = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWith1to3MetastaticRegionalLymphNodes"));

            if (nrlymph == 1) {
                res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality1"));

            } else {
                res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality2or3"));

            }
        } else {
            res[0] = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWith4orMoreMetastaticRegionalLymphNodes"));

            if (nrlymph < 7) {

                res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality4to6"));
            } else {
                res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality7orMore"));
            }

        }

        return res;

    }

    private OWLClass[] rln_6(int nrlymph) {
        OWLClass[] res = new OWLClass[2];
        OWLDataFactory factory = manager.getOWLDataFactory();

        if (nrlymph < 4) {

            res[0] = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWith1to3MetastaticRegionalLymphNodes"));
            res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality1to3"));
        } else {
            res[0] = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWith4orMoreMetastaticRegionalLymphNodes"));
            res[1] = factory.getOWLClass(IRI.create(iri + "Cardinality4orMore"));
        }

        return res;

    }

    public String replaceIri(OWLClass owlClass) {

        String res = owlClass.toString().replace(iri, "").replace("<", "").replace(">", "");

        return res;
    }

    public void createIndividualRegionalLymphNodes(String indname, int nrlymph, boolean assessment, int version, boolean deposits) throws OWLOntologyStorageException {
        OWLDataFactory factory = manager.getOWLDataFactory();
        Set<OWLClassExpression> res = new HashSet<>();
        OWLClass tumor = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumAggregate"));

        if (assessment && nrlymph > 0) {
            if (version == 6) {
                res.add(rln_6(nrlymph)[0]);
                res.add(AddQuality("Cardinality", replaceIri(rln_6(nrlymph)[1])));
                createIndividual(indname, factory, res);
            } else {

                res.add(rln_7(nrlymph)[0]);
                res.add(AddQuality("Cardinality", replaceIri(rln_7(nrlymph)[1])));
                createIndividual(indname, factory, res);
            }
        } else if (!assessment) {
            res.add(tumor);
            res.add(AddQuality("AssessmentQuality", "NoAssessment"));
            createIndividual(indname, factory, res);

        } else if (nrlymph == 0) {

            if (deposits == true && version == 7) {
                OWLClass dep = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWithTumorDeposits"));
                res.add(dep);
            } else {
                res.add(tumor);
                res.add(AddQuality("AssessmentQuality", "NoEvidence"));
            }
            createIndividual(indname, factory, res);

        } else {
            //Invalid value !!
        }

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
        OWLClassExpression tumorPlace;
        OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));
        if(place.equals("Organ")){
            tumorPlace = factory.getOWLClass(IRI.create(main + place));
        }else{
            tumorPlace = factory.getOWLClass(IRI.create(iri + place));
        }
        
            placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, tumorPlace);
            return placePart;
        
    }

    private OWLClassExpression addOnlyPlace(String place) {
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLClassExpression placePart;
        OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));

        
        OWLClassExpression tumorPlace = factory.getOWLClass(IRI.create(iri + place));
        placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, tumorPlace);
        return placePart;
    }

    public Object verifyPlace(Object place) {

        String res = place.toString().toLowerCase();
        if (place != null) {
            if (res.contains("muscular layer")) {
                place = "MuscularLayerOfLargeIntestine";
            }
            if (res.contains("visceral peritoneum")) {
                place = "VisceralPeritoneum";
            }
            if (res.contains("other")) {
                place = "other";
            }
            if (res.contains("lamina propria")) {
                place = "LaminaPropriaOfLargeIntestine";
            }
            if (res.contains("subserosa")) {
                place = "SubserosaOfLargeIntestine";
            }
            if (res.contains("submucosa")) {
                place = "SubmucosaOfLargeIntestine";
            }
            if (res.contains("adventitia")) {
                place = "AdventitiaOfLargeIntestine";
            }
        }

        return place;
    }

    public void createIndividualPrimaryTumor(String indname, Object place, int assessment, int evidence, int confinement) throws OWLOntologyStorageException {
        OWLDataFactory factory = manager.getOWLDataFactory();
        Set<OWLClassExpression> res = new HashSet<>();

        OWLClassExpression tumorType = factory.getOWLClass(IRI.create(iri + "ColonAndRectumTumor"));
        res.add(tumorType);
        if (assessment == 0) {
                OWLClassExpression qual = AddQuality("AssessmentQuality", "NoAssessment");
                res.add(qual);
        } else if (evidence == 0) {
                OWLClassExpression qual = AddQuality("AssessmentQuality", "NoEvidence");
                res.add(qual);
        } else {
            if (confinement == 0) {
                OWLClassExpression qual = AddQuality("Confinement", "Confined");
                res.add(qual);
            } else {
                OWLClassExpression qual = AddQuality("Confinement", "Invasive");
                res.add(qual);
            }

            System.out.println("PLACE" + place);
            if (place != null) {

                OWLClassExpression placePart = addSomePlace(verifyPlace(place).toString());

                if (place.equals("other")) {
                    placePart = addSomePlace("Organ");
                    OWLClassExpression intestine = factory.getOWLClass(IRI.create(iri+"LargeIntestine"));
                     res.add(placePart);
                     OWLObjectProperty isIncludedIn = factory.getOWLObjectProperty(IRI.create(iribtl2 + "isIncludedIn"));
                   placePart = factory.getOWLObjectSomeValuesFrom(isIncludedIn, intestine);
                   res.add(placePart.getObjectComplementOf());
                    System.out.println(placePart.toString());
                  
                   

                } else {
                    res.add(placePart);
                }

            }
        }
        createIndividual(indname, factory, res);

    }

    public void createIndividualMetastasis(String indname, int organs, boolean peritoneum, int version) throws OWLOntologyStorageException {
        OWLDataFactory factory = manager.getOWLDataFactory();
        Set<OWLClassExpression> res = new HashSet<>();
        Set<OWLClassExpression> res1 = new HashSet<>();
        OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(iribtl2 + "hasPart"));
        OWLClass distant = factory.getOWLClass(IRI.create(iri + "DistantMetastasisOfColonAndRectumTumor"));
        if (organs == 0) {
            OWLClassExpression tumorType = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWithNoDistantMetastasis"));
            res.add(tumorType);
        } else {
            OWLClassExpression tumorType = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWithDistantMetastasis"));
            res.add(tumorType);
            if (organs == 1 && version == 7) {

                if (peritoneum) {

                    OWLClassExpression some = factory.getOWLObjectSomeValuesFrom(hasPart, distant);
                    res1.add(some);
                    res1.add(addSomePlace("Peritoneum"));
                    OWLClassExpression intersect = factory.getOWLObjectIntersectionOf(res1);
                    res.add(intersect);

                } else {
                    OWLClassExpression type = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWithDistantMetastasisConfinedTo1Organ"));
                    res.add(type);
                }
            } else if (organs > 1 && version == 7) {

                if (peritoneum) {

                    res.add(addSomePlace("Peritoneum"));

                }
                OWLClassExpression type = factory.getOWLClass(IRI.create(iri + "TumorOfColonAndRectumWithDistantMetastasisInMoreThan1Organ"));
                res.add(type);

            }
        }

        createIndividual(indname, factory, res);

    }

    public void createIndividual(String indname, OWLDataFactory factory, Set<OWLClassExpression> res) throws OWLOntologyStorageException {

        OWLObjectIntersectionOf allParts = factory.getOWLObjectIntersectionOf(res);
        OWLIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + indname));
        OWLClassAssertionAxiom ax = factory.getOWLClassAssertionAxiom(allParts, ind);
        manager.addAxiom(ontology, ax);
        manager.saveOntology(ontology);
    }
}

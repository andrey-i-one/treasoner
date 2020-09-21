package ontologycomparer;

import TReasonerFactory.TReasoner;
import eu.trowl.owlapi3.rel.reasoner.dl.RELReasonerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import uk.ac.manchester.cs.jfact.JFactFactory;

/**
 * Class searches all axioms that are different in two ontologies.
 * @author Andrey Grigoryev
 */
public class OntologyComparer {

    private static OWLOntologyManager m;
    
    /**
     * Метод вычисляет время классификации онтологий для выбранных ризонеров и записывает их в файл.
     * @param ont_name Имя онтологии.
     * @param S Ссылка на запись в файл.
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    private static void compareTimeOfTwoReasoners(String ont_name, PrintWriter S) throws InstantiationException, IllegalAccessException {
        boolean b = true;
        OWLOntologyManager m = OWLManager.createOWLOntologyManager();
        OWLOntology o = null;
        try {
            o = m.loadOntologyFromOntologyDocument(new File(ont_name));
        } catch (OWLOntologyCreationException ex) {
            System.err.println("Error can't load the ontology.");
            return;
        }
        
        long jfact_total = 0, hermit_total = 0, chainsaw_total = 0, my_total1 = 0, my_total2 = 0, fpp_total = 0, trowl_total = 0, more_total = 0;
        long start = System.currentTimeMillis();
        /*try {
            start = System.currentTimeMillis();
            TReasonerFactory.TReasoner tReasoner = new TReasoner();
            tReasoner.loadOntology(ont_name, false, true, true, true, false, 300000);
            if(tReasoner.count > 1000) return;
            tReasoner.classifyOntology(null, true);
            my_total1 = System.currentTimeMillis() - start;
            System.out.println("TReasoner wo end.");
        } catch (Exception e) {
            return;
        }
        
        try {
            start = System.currentTimeMillis();
            Reasoner.ReasonerFactory hrf = Reasoner.ReasonerFactory.class.newInstance();
            OWLReasoner hermit = hrf.createReasoner(o);
            hermit.precomputeInferences(InferenceType.CLASS_HIERARCHY);
            hermit_total = System.currentTimeMillis() - start;
            System.out.println("HermiT end.");
        } catch (Exception e) {
            return;
        }

        try {
            start = System.currentTimeMillis();
            JFactFactory jff = JFactFactory.class.newInstance();
            OWLReasoner jfact = jff.createReasoner(o);
            jfact.precomputeInferences(InferenceType.CLASS_HIERARCHY);
            jfact_total = System.currentTimeMillis() - start;
            System.out.println("JFact end.");
        } catch (Exception e) {
            return;
        }

        /*try {
            start = System.currentTimeMillis();
            FaCTPlusPlusReasonerFactory fpprf = FaCTPlusPlusReasonerFactory.class.newInstance();
            OWLReasoner fpp = fpprf.createReasoner(o);
            fpp.precomputeInferences(InferenceType.CLASS_HIERARCHY);
            fpp_total = System.currentTimeMillis() - start;
        } catch (Exception e) {
            return;
        }

        try {
            start = System.currentTimeMillis();
            RELReasonerFactory rrf = RELReasonerFactory.class.newInstance();
            OWLReasoner trowl = rrf.createReasoner(o);
            trowl.precomputeInferences(InferenceType.CLASS_HIERARCHY);
            trowl_total = System.currentTimeMillis() - start;
            System.out.println("TrOWL end.");
        } catch (Exception e) {
            return;
        }*/
        
        /*try {
            start = System.currentTimeMillis();
            TReasonerFactory.TReasoner tReasoner = new TReasoner();
            tReasoner.loadOntology(ont_name, false, true, true, true, false, 300000);
            if(tReasoner.count > 2000) return;
            tReasoner.classifyOntology(null, true);
            my_total1 = System.currentTimeMillis() - start;
            System.out.println("TReasoner wo end.");
        } catch (Exception e) {
            return;
        }*/

        try {
            start = System.currentTimeMillis();
            TReasonerFactory.TReasoner tReasoner = new TReasoner();
            tReasoner.loadOntology(ont_name, false, true, false, false, false, 300000);
            tReasoner.classifyOntology(null, true);
            hermit_total = System.currentTimeMillis() - start;
            //System.out.println("TReasoner end.");
        } catch (Exception e) {
            return;
        }

        try {
            start = System.currentTimeMillis();
            TReasonerFactory.TReasoner tReasoner = new TReasoner();
            tReasoner.loadOntology(ont_name, true, false, false, false, false, 300000);
            tReasoner.classifyOntology(null, true);
            my_total1 = System.currentTimeMillis() - start;
            //System.out.println("TReasoner end.");
        } catch (Exception e) {
            return;
        }

        try {
            start = System.currentTimeMillis();
            TReasonerFactory.TReasoner tReasoner = new TReasoner();
            tReasoner.loadOntology(ont_name, true, true, false, false, false, 300000);
            tReasoner.classifyOntology(null, true);
            my_total2 = System.currentTimeMillis() - start;
            //System.out.println("TReasoner end.");
        } catch (Exception e) {
            return;
        }

        if(b) {
            //System.out.println(ont_name);
            System.out.println(ont_name + "\t" + jfact_total + "\t" + 0 + "\t" + trowl_total + "\t" + hermit_total + "\t" + my_total1 + "\t" + my_total2);
            //S.println(ont_name + "\t" + jfact_total + "\t" + 0 + "\t" + trowl_total + "\t" + hermit_total + "\t" + my_total1 + "\t" + my_total2);
        }
    }
    
    /**
     * Метод запускает два ризоенра: TReasoner и другой state-of-the-art, затем сравнивает две 
     * получившихся классификации и выдает аксиомы которые отсутствуют в той или другой
     * классификации.
     * @param ont_name Путь к файлу онтологии для сравнения.
     */
    private static void compareTwoReasonerOnOntology(String ont_name, PrintWriter S) throws InstantiationException, IllegalAccessException {
        boolean b = true;
        System.out.println(ont_name);
        OWLOntologyManager m = OWLManager.createOWLOntologyManager();
        OWLOntology o = null;
        try {
            o = m.loadOntologyFromOntologyDocument(new File(ont_name));
        } catch (OWLOntologyCreationException ex) {
            System.err.println("Error can't load the ontology.");
            return;
        }
        
        System.out.println("Start another reasoner on\t" + ont_name);
        Set<OWLSubClassOfAxiom> ax_set = new HashSet<OWLSubClassOfAxiom>();
        long start = System.currentTimeMillis();
        try {
            //ax_set = more.classify();
        } catch(Exception e) {
            System.err.println("Cann't classify ontology by another reasoner");
            return;
        }
        long total = System.currentTimeMillis() - start;
        System.out.println("Total time:\t" + total);
        
        OWLOntology owl2 = null;
        try {
            HashSet<OWLAxiom> hs = new HashSet<OWLAxiom>();
            for(OWLSubClassOfAxiom ax: ax_set) {
                hs.add(ax);
            }
            owl2 = m.createOntology(hs);
        }
        catch (OWLOntologyCreationException e) {
            System.err.println("Cann't create an ontology by another reasoner");
            return;
        }

        System.out.println("Start my reasoner on ontology\t" + ont_name);
        start = System.currentTimeMillis();
        TReasonerFactory.TReasoner tReasoner = new TReasoner();
        tReasoner.loadOntology(ont_name, true, true, true, false, false, 300000);
        ax_set = tReasoner.classifyOntology(null, true);
        long my_total = System.currentTimeMillis() - start;
        System.out.println("Total time:\t" + my_total);
        
        OWLOntology owl1 = null;
        try {
            HashSet<OWLAxiom> hs1 = new HashSet<OWLAxiom>();
            for(OWLSubClassOfAxiom ax: ax_set) {
                hs1.add(ax);
            }
            owl1 = m.createOntology(hs1);
        } catch (OWLOntologyCreationException ex) {
            System.err.println("Cann't create an ontology by TReasoner");
            return;
        }

        AxiomVisitor av = new AxiomVisitor();
        Taxonomy t1 = new Taxonomy();
        for(OWLLogicalAxiom ax: owl1.getLogicalAxioms()) {
            av.subClass = false;
            av.eqsClass = false;
            ax.accept(av);
            if(av.subClass) t1.addSubClass(av.SUB, av.SUP);
            if(av.eqsClass) t1.addEqClass(av.SUB, av.SUP);
        }

        Taxonomy t2 = new Taxonomy();
        for(OWLLogicalAxiom ax: owl2.getLogicalAxioms()) {
            av.subClass = false;
            av.eqsClass = false;
            ax.accept(av);
            if(av.subClass) t2.addSubClass(av.SUB, av.SUP);
            if(av.eqsClass) t2.addEqClass(av.SUB, av.SUP);
        }
        b |= t2.compareTo(t1);

        if(b) {
            S.println(ont_name + "\t" + total + "\t" + my_total);
        }
    }
    
    public static void testManyFiles() {
        String pref = "C:\\Users\\Boris\\YandexDisk\\DL98OWL\\";
        pref = "D:\\ORE 2013\\ore2013-ontologies-offline\\owlxml\\all\\";
        ArrayList<String> totest_files = new ArrayList<String>();
        totest_files.clear();
        /*totest_files.add("kris151.tkb.owl");
        totest_files.add("kris301.tkb.owl");
        totest_files.add("kris451.tkb.owl");
        totest_files.add("kris601.tkb.owl");
        totest_files.add("kris751.tkb.owl");
        totest_files.add("kris901.tkb.owl");
        totest_files.add("kris1051.tkb.owl");
        totest_files.add("kris1201.tkb.owl");
        totest_files.add("kris1351.tkb.owl");
        totest_files.add("kris1501.tkb.owl");
        totest_files.add("kris2001.tkb.owl");
        totest_files.add("kris4001.tkb.owl");
        totest_files.add("kris6001.tkb.owl");
        totest_files.add("kris8001.tkb.owl");
        totest_files.add("kris10001.tkb.owl");
        totest_files.add("kris12001.tkb.owl");
        totest_files.add("kris14001.tkb.owl");
        totest_files.add("kris16001.tkb.owl");
        totest_files.add("kris18001.tkb.owl");
        totest_files.add("kris20001.tkb.owl");
        totest_files.add("kris25001.tkb.owl");
        totest_files.add("kris30001.tkb.owl");
        totest_files.add("kris35001.tkb.owl");
        totest_files.add("kris40001.tkb.owl");
        totest_files.add("kris45001.tkb.owl");
        totest_files.add("kris50001.tkb.owl");*/
        
        /*String all = 
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\00668.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-4-cardinalities-disjointness-relation-axioms.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\15a2b8ca-96c2-4551-b4ac-d422911c7dec_imensional.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\b1bc193b-f522-4114-b680-2525cf97c795_GeoSkills.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0f0a5409-74e0-4f07-9d10-876b2ba14320_Biological.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\6188dc1b-5fda-4e60-a061-ef397532bcd7_logy.0.1.1.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\00efc81a-ecfa-4739-88ad-59762f9ed0a6_mHydroBody.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\565a4206-e75d-4de4-a26d-bcbc0f9ba100_torDefault.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1dc94f07-41c3-4171-abb5-3c2955a8a750_eformation.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\d21900de-a556-4a51-8796-e7c474a3e998_siocore.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-2-cardinalities-disjointness-constant-classes-relation-axioms-value-classes.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\686d7366-a085-4694-9bc8-f4426c3a5d90_Shares.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\15a31bac-93d6-40e3-881d-78cbcc91f8c3_TOLOGYv102.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1a284d14-21c0-4da4-bf17-f9b5f9b4e8b3_a_20110129.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\uberon.uber-anatomy-ontology.210.owl.xml_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\bda18463-f8a3-450e-82db-485d33657af2_oceanIce.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\a9308b8f-fea9-4857-8b00-894e5841558a_extended.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\90d6d65e-d1d3-457b-bdef-8c5a2f8ee290_Atom.old.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\28e5c35f-4c37-486e-aba9-8467335a4e4b_SMOtop1007.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\2ed96c57-de69-4d4a-ad8c-1cddfdb6b368_information.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\82e8c9d6-263f-491d-822f-16e574411e14_erClosures.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\bba8cd93-0083-4431-9737-98d12d84c800_eDirection.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\21bf1dd2-e131-4a5b-86ee-3084782bd546_merged.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\5e16bb8d-698f-450e-a490-d6125510edaf_nAtmoFront.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\82a2b4c8-e8c8-4a2c-970a-9352ebb0591c_taontology.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\27af1ea1-393f-40f5-8a8c-7725fd531ded_p.qcr.fixed.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3ca859a4-cb7b-4e9f-8d78-f55d5db3b9b5_Coordinate.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\61d101eb-6c37-4694-968f-c1455ac78a0d_onto.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-1-cardinalities-disjointness-relation-axioms.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\fc63e30b-0fc8-42cb-a773-61d903caa0f4_propCount.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\19225851-bd88-4b53-99fe-b18e985a9dd3_n.obo.PREVIOUS_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\dbffa01f-6b93-4f69-a11f-83415674a184_ydrocarbon.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\034e892f-5c19-4cca-8858-4bb8a3b8a6d1_sio.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\9cd3187c-5e79-4804-9a90-4621b9c26783_onto.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\icps.international-classification-for-patient-safety.7.owl.xml_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\c5d1a38e-60bf-4925-b4fc-56585d6dda13_ompoundCFC.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-3-cardinalities-disjointness-constant-classes-value-nominals.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-1-cardinalities-triggers-constant-classes-value-nominals.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3377b615-2c2e-4427-8e02-b811584e1d24_leChemical.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\b545b71a-2659-40bf-9445-3363f1ba664e_iComponent.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\406e4956-d196-4bb2-9d72-ccd2a1be2faa_asili.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\4c042000-a214-4837-be08-f3d0275608db_PersonGroup.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\c091f25f-bc65-4bea-8454-17f0043e1279_n_ontology.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\39fe7348-562d-4121-8365-6ac23424cb7c_genpol.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\6e3e9d28-cd99-4925-a376-85f30998644a_qtaglink.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\6d034b79-7817-4711-b9f6-339bd1a95008_provdc_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0507d1c6-5536-41fd-b717-a51a3f5cbf4f_PD.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\a2920fe4-23bf-4c8f-b1b5-084212e6f3e3_ceGeometry.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\125d0ac0-16a1-4cfe-9a75-20e64e826aae_SMOtop849c.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\f2669fef-2edd-40b8-8a8d-207448a70329_sfb632.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ba124800-7f8a-47be-bafd-2992d356e4c5_chemProcess.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\9d1fa972-d103-4d6b-a7ec-193ce1190801_r_EEZ_v1_0.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\e15f5e3f-d55a-4f83-8b59-c3db90180451_CMOF.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ccc9c3b5-5cfb-4d44-9ac6-9116adb240fe_eolOceanic.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\118a4487-50c2-4791-a122-b6250d54b154_morphology.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\7eb4da3b-9764-4eda-a010-ab4b286bb868_eRoleTrust.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-3-km-relevant-cardinalities-disjointness.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\80c17aa3-04c4-4361-a99f-266af4284c60_omeWeather.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ccddac71-490e-457e-80e1-ff9d2542b9c5_ontology.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1b8f465b-f3db-4537-9499-5a1e9226ea99_rDataModel.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\b29f8772-0725-4a20-b2c0-bb2384fe833a_onto.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\debdcdb3-95d8-425d-9ca2-f640741aed5f_bokqueries.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\00018.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\51ea88d6-9735-4fe4-84ca-cf425f04f8e2_es20120118.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\6fd2fe50-6a87-4463-ae9c-45cc14347b80_oTransport.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\000625f4-66a1-4f5a-8513-24d547cdb5cb_nvirImpact.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0a4a3755-762e-4ba5-a193-35c2851364bc_dsolutions.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\6226c875-2343-443d-aa84-cdea1480d80e_nGeolFault.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-2-km-relevant-cardinalities-constant-classes-relation-axioms-value-nominals.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\00094.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\f2faf58c-9bb8-41e0-acc1-f7a6833be1cf_meGeologic.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-3-km-relevant-cardinalities-disjointness-relation-axioms.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ba0bf118-9ed2-4850-8fcf-ac4ab6ca6af7_rStandards.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1b2ba6f4-4828-42da-9165-be91b6529c56_uberon.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ddd751bd-f933-42b7-865d-e9aa08c3408b_ncertainty.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\90ffb1bd-1675-4313-9f33-b4041988aeb7_nAtmoCloud.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\8c8cb9d0-3cac-4fc6-9d73-f9df60a28913_thSolution.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3a3066b4-739d-484f-9d60-3060498eb2e5_rDataModel.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\56aa7fb9-6e6b-4b60-858e-8ec80ef3110d_bservation.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\48d3a122-e4e8-4f92-91ea-9950e122101b_wine_fred.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\dd195e23-4188-481a-bac0-4cc7c80291c6_onto.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\c06f618d-66a7-432e-890e-d2592d17bb7d_009UNAeasy.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\fa00daff-4134-4ab3-a650-2637ad75448d_ataService.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0e841093-7784-4345-a8eb-22b899d8db68_tension_02.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\f22829c6-cad7-4426-8cdc-068254131c37_physThermo.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\08700ca3-c320-431d-9539-6f017f2615f0_tigerlink.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\2c762e1a-6364-4ae3-8600-88b78825f388_aneurist1.0.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\002f75a4-16ee-4437-9123-c273f399590c_odSolutions.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\8e755b55-a363-4010-80e2-929283929a7a_topology.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\f0203f35-c445-4cb6-bf99-d6d64b372c5b_ndVolcanic.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\a61b1028-0965-40a4-b8f9-e0f503b35de1_JavaActor.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\92fa8dc5-f1e1-41a8-824c-1f3660dfc5f5_oReduction.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0129d06f-06c2-4e4b-94db-cc0c65fab9cc_COSMOtop853.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\990efa33-1c11-471c-bbad-78ff9e7266bc_pmbokref.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-4-cardinalities-triggers-constant-classes-relation-axioms-value-nominals.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\487e1166-fe3c-469d-9081-e3285bebdfb8_JC3IEDMBase.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\4fc1ef71-afb9-4112-91ba-5bd44b91f90a_iffusivity.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\8cdb7cb1-4df2-4980-9f6f-7187453ec2df_sttslink.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\964325ce-18c4-4277-a100-6fe02e9a0d14_esmeta_1.2.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\19c1897e-ed48-430e-bf23-81b6d94e6236_onto.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\4baf98c2-3c91-4c13-bb50-e9b38090dcfc_mIndicator.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\5b850a43-95b6-4a41-8e17-97f2aae70514_iComponent.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ca495d9f-6fd0-455d-be1b-17538744f700_dinterests.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\441fdaf0-b954-41e4-afb7-138a76151175_eanFeature.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\68ed0453-17f1-4e0d-838f-d370fa65437f_oda2.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0f628045-efd4-448f-ab65-b438cd3d4389_core.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\b104bd58-3b00-495d-83d2-be4c495872a8_stribution.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\2bb7e2ff-03bf-4f6e-96b3-b741a0a32494_aircraft.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0f88403b-27b1-4c15-8862-c7e85ce0ec55_ExtendedDnS.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1cbab943-e664-4e18-9df4-dc9dc389c81c_Design.v11.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\5ae31e8c-92fa-481b-b324-78b7c7779954_rMathGraph.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\fc3b7caf-04e4-4f13-9fe0-b8a8dd3a7241_itical.rdf.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\f3f99738-aa89-4a6b-b560-3fd2ff0de3f6_us1gov_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\5ab3806b-35d4-460a-b1eb-5f87cc9ba2ef_figure8.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\652ba424-0679-4917-a091-ed2edb9b08a1_emistrycomplex_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-2-km-relevant-cardinalities-relation-axioms.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\1b9bc6c9-d156-4a1c-a821-014fcf3ae7c5_20090907.rdf_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\5ccf6e47-f205-4cfe-bb45-8f47b5267eb5_oTransport.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\70c07f2d-cdc2-4cbc-b617-35e0c20ce3a3_Aplicacion.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3cb4ae2a-79a3-4de2-a8c6-99fe006aa38c_oWindScale.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3fe8df9e-8a9f-453c-9104-db2a524b0fa4_algraphcomplex_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\3306dfe2-1e10-4dbf-842b-0bec08bba814_Support2.0.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\60bac925-236d-46d4-8aaa-a3b96f2aec1d_hOperation.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-2-triggers-constant-classes-relation-axioms-value-nominals.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\864aab46-1878-428c-8eb9-98b6517f9f75_phenRecycle.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\efeaf0c9-8c52-4caf-b305-d33db5b6c383_photograph.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0bf9fa83-bef0-4ea9-a308-7706780329f0_d_Approach.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\93e9ef2c-19db-4568-baf6-b2764b59d0be_GRO_v0.3.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0d05f77e-c3ae-4d88-a19d-9caf558f5156_reprTime.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0abccd8e-2b19-4af2-a0b8-0548d21854b2_dataset.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\genomic-cds_rules.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0ecd9763-2a0d-48b2-a6ca-9533c8168e02_MyReview.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\2a0bf7a9-a99e-46e6-a715-2583ec9294c4_phenHydro.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\747c721b-14b2-43de-94a1-770e0e712354_matrPlant.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\0c701b3b-4333-447e-9366-5738d5b677e0_AmigoS.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\ef64d0f0-cfcb-4835-9131-6293800b96b5_irculation.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\13bd22c9-4d27-4c42-9afb-9bcf8f638546_IPR.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\cff0d4ea-7bd0-48ef-9218-09552fc3bc09_confious.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\kb-owl-syntax-unraveled-depth-3-cardinalities-triggers.ofn_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\8a63015d-a9bf-439e-929e-40e870c870e4_e_Function.owl_functional.owl\n" +
        "C:\\Users\\Boris\\Downloads\\dataset\\files\\7b762ac7-dc12-43f2-bd28-a77c49257f98_trSediment.owl_functional.owl";
        */
        totest_files.add("14ce0800-2ef5-43f5-8dea-8ca1adf38add_1501.owl");
        totest_files.add("5c9600cb-47b0-4230-97ea-045e20434f33_101001.owl");
        totest_files.add("87800dc9-355e-4d61-b761-dbe9d7db4526_111201.owl");
        totest_files.add("8b2083bf-9266-4d19-b4df-e65e8a1f0959_1_5_TQ.owl");
        totest_files.add("853e0872-cc76-472f-acdd-d8407d489447_e_fred.owl");
        totest_files.add("c692093c-46b4-4728-b062-42a1cdb13f0c_pizza.owl");
        totest_files.add("c4f69db5-3f46-479a-a92e-678f107cdb30_wineCR.rdf");
        totest_files.add("ed702658-c8e0-48bb-af79-34c625fb8039_tology.owl");
        totest_files.add("52cf3ab5-1662-4296-835e-b22ac92339e7_.2_DUL.owl");
        totest_files.add("7c7b7968-05c3-43d8-ac76-beb39e2d66f1_dedDnS.owl");
        totest_files.add("89c4379a-a7e8-4bd0-88f0-60e75bacf14c_ic-1.2.owl");
        totest_files.add("6785a156-f582-4a75-823d-65102e344a45_tology.owl");
        totest_files.add("f0f63db4-b873-48bc-b951-cdfb9bbccafe_061208.owl");
        totest_files.add("7e5fed45-20f4-4df4-92b2-e7d978332992_IEDM.owl");
        totest_files.add("00019.owl");
        totest_files.add("ontology-of-physics-for-biology.1141.owl.xml");
        totest_files.add("02f29fb7-d033-4818-a7ce-9bb744618d81_1141.owl");
        totest_files.add("9cae0d3f-2386-48c9-aa25-f598a33c7c05_aeo-2.owl");
        totest_files.add("c327bfe4-80d8-4b3c-8055-5aed58ac42db_otamsP.owl");
        
        /*pref = "D:\\ORE 2014\\files\\";
        String[] str = all.split("\n");
        for(int i = 0; i < str.length; i++) {
            int x = str[i].lastIndexOf("\\");
            str[i] = str[i].substring(x + 1);
            totest_files.add(str[i]);
        }*/

        String out_file = "C:\\Users\\Boris\\Downloads\\exp.txt";
        PrintWriter S = null;
        if(out_file != null)
            try {
                S = new PrintWriter(new File(out_file));
                S.println("Ontology name" + "\t" + "JFact time" + "\t" + "MORe time" + "\t" + "TrOWL time" + "\t" + "HermiT time" + "\t" + "TReasoner time without" + "\t" + "TReasoner time");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't write to file " + out_file);
        }
        for(int i = 0; i < totest_files.size(); i++) {
            try {
                //compareTwoReasonerOnOntology(files[i], S);
                compareTimeOfTwoReasoners(pref + totest_files.get(i), S);
            } catch (InstantiationException ex) {
                Logger.getLogger(OntologyComparer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(OntologyComparer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        S.close();
        
        /*String file_path = "C:\\Users\\Boris\\Downloads\\dataset\\dl\\classification\\clust.txt";
        try {
            Scanner S = new Scanner(new File(file_path));
            while(S.hasNextLine()) {
                String fp = S.nextLine();
                //compareTwoReasonerOnOntology("D:\\ORE 2013\\ore2013-ontologies-offline\\owlxml\\all\\" + fp);
                compareTwoReasonerOnOntology(fp);
            }
            S.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Can't load file\t" + file_path);
        }*/
    }
    
    /**
     * Main method of the program.
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        //compareTwoReasonerOnOntology("D:\\ORE 2013\\ore2013-ontologies-offline\\owlxml\\dl\\71134246-639b-4ed8-aff8-2261cb41cd4e_tp3.owl");
        testManyFiles();
    }
}
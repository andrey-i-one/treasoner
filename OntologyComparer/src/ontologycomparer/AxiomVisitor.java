package ontologycomparer;

import java.util.Set;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.SWRLRule;

/**
 *
 * @author Andrey Grigoryev
 */
class AxiomVisitor implements OWLAxiomVisitor 
{
    public boolean subClass = false;
    public boolean eqsClass = false;
    public String SUB = "";
    public String SUP = "";
    
    @Override
    public void visit(OWLDeclarationAxiom owlda) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSubClassOfAxiom owlsc) {
        subClass = true;
        if(!owlsc.getSubClass().isClassExpressionLiteral())
        {
            System.out.println("ERROR: not class expression literal");
        }
        if(!owlsc.getSuperClass().isClassExpressionLiteral())
        {
            System.out.println("ERROR: not class expression literal");
        }
        
        SUB = owlsc.getSubClass().toString();
        SUP = owlsc.getSuperClass().toString();
    }

    @Override
    public void visit(OWLNegativeObjectPropertyAssertionAxiom owlnp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLAsymmetricObjectPropertyAxiom owlp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLReflexiveObjectPropertyAxiom owlrp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDisjointClassesAxiom owldca) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDataPropertyDomainAxiom owldpd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLObjectPropertyDomainAxiom owlpd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLEquivalentObjectPropertiesAxiom owlp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLNegativeDataPropertyAssertionAxiom owlndp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDifferentIndividualsAxiom owldia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDisjointDataPropertiesAxiom owldp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDisjointObjectPropertiesAxiom owldp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLObjectPropertyRangeAxiom owlpr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLObjectPropertyAssertionAxiom owlp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLFunctionalObjectPropertyAxiom owlfp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSubObjectPropertyOfAxiom owlsp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDisjointUnionAxiom owldua) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSymmetricObjectPropertyAxiom owlsp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDataPropertyRangeAxiom owldpr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLFunctionalDataPropertyAxiom owlfdp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLEquivalentDataPropertiesAxiom owldp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLClassAssertionAxiom owlcaa) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLEquivalentClassesAxiom owleca) {
        eqsClass = true;
        Set<OWLSubClassOfAxiom> s = owleca.asOWLSubClassOfAxioms();
        for(OWLSubClassOfAxiom owlsc: s)
        {
            if(!owlsc.getSubClass().isClassExpressionLiteral())
            {
                System.out.println("ERROR: not class expression literal");
            }
            if(!owlsc.getSuperClass().isClassExpressionLiteral())
            {
                System.out.println("ERROR: not class expression literal");
            }

            SUB = owlsc.getSubClass().toString();
            SUP = owlsc.getSuperClass().toString();
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDataPropertyAssertionAxiom owldp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLTransitiveObjectPropertyAxiom owltp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLIrreflexiveObjectPropertyAxiom owlp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSubDataPropertyOfAxiom owlsdp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLInverseFunctionalObjectPropertyAxiom owlfp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSameIndividualAxiom owlsia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSubPropertyChainOfAxiom owlspc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLInverseObjectPropertiesAxiom owlp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLHasKeyAxiom owlhka) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLDatatypeDefinitionAxiom owldda) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(SWRLRule swrlr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLAnnotationAssertionAxiom owlaaa) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLSubAnnotationPropertyOfAxiom owlsp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLAnnotationPropertyDomainAxiom owlpd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visit(OWLAnnotationPropertyRangeAxiom owlpr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

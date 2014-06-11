package fr.stratonit.kstore.launcher;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntModel ontModel = ModelFactory.createOntologyModel();
	    InputStream in = FileManager.get().open(inputFileName);
	    if (in == null) {
	        throw new IllegalArgumentException( "File: " + inputFileName + " not found");
	    }
	    ontModel.read(in, "");


	    Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
	    reasoner = reasoner.bindSchema(ontModel);
	    // Obtain standard OWL-DL spec and attach the Pellet reasoner
	    OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
	    ontModelSpec.setReasoner(reasoner);
	    // Create ontology model with reasoner support
	    OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);

	    // MarriedPerson has no asserted instances
	    // However, if an inference engine is used, two of the three
	    // individuals in the example presented here will be
	    // recognized as MarriedPersons
	            //ns is the uri
	    OntClass marPerson = model.getOntClass(ns + "OWLClass_00000003866036241880"); // this is the uri for MarriedPerson class
	    ExtendedIterator married = marPerson.listInstances();
	    while(married.hasNext()) {
	        OntResource mp = (OntResource)married.next();
	        System.out.println(mp.getURI());
	    } // this code returns 2 individuals with the help of reasoner
	}

}

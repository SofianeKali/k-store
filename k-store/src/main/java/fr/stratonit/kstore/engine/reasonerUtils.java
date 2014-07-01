package fr.stratonit.kstore.engine;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.vocabulary.OWL;

/**
 * @author Zahir Kali <zkali@straton-it.fr>
 * 
 */
public class reasonerUtils {
	/** le reasoner **/
	private static Reasoner OWLReasoner;
	
	/**
	 * 
	 */
	private reasonerUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static final Reasoner getOWLReasoner() {
		return OWLReasoner;
	}

	public static Model newReasoner(Model schema, Model data){
		if(OWLReasoner == null){
			OWLReasoner = ReasonerRegistry.getOWLReasoner();
		}
		OWLReasoner.bindSchema(schema);
		return ModelFactory.createInfModel(OWLReasoner, data);
	}

}

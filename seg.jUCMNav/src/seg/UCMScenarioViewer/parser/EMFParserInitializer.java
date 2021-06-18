package seg.UCMScenarioViewer.parser;

import java.io.File;
import java.io.IOException;

import seg.jUCMNav.editors.resourceManagement.UcmScenariosModelManager;

import seg.UCMScenarioViewer.model.ScenarioGroup;
import seg.UCMScenarioViewer.parser.emf.ScenarioGroupParser;

/**
 * Load model from EMF serialization
 * @author jkealey
 *
 */
public class EMFParserInitializer {

	public static ScenarioGroup parseMscModel(File file){
		ScenarioGroup scenG = null;
		try {			
			UcmScenariosModelManager mgr = new UcmScenariosModelManager();
			mgr.load(file);
			ScenarioGroupParser GroupParse = new ScenarioGroupParser(mgr.getModel());
			scenG = GroupParse.parseScenarioGroup();
		} catch (IOException e) {
            e.printStackTrace();
		}
		return scenG;
	}
}

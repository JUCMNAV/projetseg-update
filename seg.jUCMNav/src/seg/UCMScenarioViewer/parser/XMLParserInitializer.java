/*
 * Created on 08.03.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.UCMScenarioViewer.parser;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.kxml.io.ParseException;

import seg.UCMScenarioViewer.model.ScenarioGroup;
import seg.UCMScenarioViewer.utils.Helper;
import seg.UCMScenarioViewer.parser.xml.ScenarioGroupParser;

/**
 * @author Tolik
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XMLParserInitializer {

	public static ScenarioGroup parseXmlModel(File file) throws IOException{
		ScenarioGroup scenG = null;
		try {
			FileReader fileRead = new FileReader(file);
			ScenarioGroupParser scenGParser = new ScenarioGroupParser(fileRead);
			scenG = scenGParser.parseScenarioGroup();
			if (scenG.getViewChildren().isEmpty())
				throw new IOException(Helper.ERR_MSG_PARSING);
		} catch (ParseException e) {
			throw new IOException(Helper.ERR_MSG_PARSING);
		}
		return scenG;
	}

}

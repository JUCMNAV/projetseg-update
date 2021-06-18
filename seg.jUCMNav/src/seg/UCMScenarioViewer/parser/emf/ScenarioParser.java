package seg.UCMScenarioViewer.parser.emf;

import java.util.HashMap;
import java.util.Iterator;

import ucmscenarios.Instance;
import ucmscenarios.ScenarioDef;

import seg.UCMScenarioViewer.model.LifeLine;
import seg.UCMScenarioViewer.model.Scenario;
import seg.UCMScenarioViewer.model.Sequence;

public class ScenarioParser {

	private Scenario scenario;

	private ScenarioDef src;

	public ScenarioParser(Scenario scenario, ScenarioDef parser) {
		this.scenario = scenario;
		this.src = parser;
	}

	public Scenario parseScenario() {

		HashMap lifeLines = new HashMap();
		for (Iterator iter = src.getInstances().iterator(); iter.hasNext();) {
			Instance instance = (Instance) iter.next();

			LifeLine line = new LifeLine(instance.getId(), instance.getName());
			scenario.addChild(line);
			lifeLines.put(instance, line);

		}
		

		SequenceParser sequenceParse = new SequenceParser(src);
		Sequence seq = sequenceParse.parseSequence(lifeLines);
		scenario.addChild(seq);

		return scenario;

	}
}
/*
 * Created on 05.02.2005
 *
 */
package seg.UCMScenarioViewer.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.UCMScenarioViewer.editpart.viewer.ActionEditPart;
import seg.UCMScenarioViewer.editpart.viewer.ConditionEditPart;
import seg.UCMScenarioViewer.editpart.viewer.LifeLineEditPart;
import seg.UCMScenarioViewer.editpart.viewer.MessageEditPart;
import seg.UCMScenarioViewer.editpart.viewer.ParallelSequenceEditPart;
import seg.UCMScenarioViewer.editpart.viewer.ResetTimerEditPart;
import seg.UCMScenarioViewer.editpart.viewer.ScenarioEditPart;
import seg.UCMScenarioViewer.editpart.viewer.ScenarioGroupEditPart;
import seg.UCMScenarioViewer.editpart.viewer.SetTimerEditPart;
import seg.UCMScenarioViewer.editpart.viewer.StartEndEditPart;
import seg.UCMScenarioViewer.editpart.viewer.TimeoutEditPart;
import seg.UCMScenarioViewer.model.Action;
import seg.UCMScenarioViewer.model.Condition;
import seg.UCMScenarioViewer.model.LifeLine;
import seg.UCMScenarioViewer.model.Message;
import seg.UCMScenarioViewer.model.ParallelSequence;
import seg.UCMScenarioViewer.model.ResetTimer;
import seg.UCMScenarioViewer.model.Scenario;
import seg.UCMScenarioViewer.model.ScenarioGroup;
import seg.UCMScenarioViewer.model.SetTimer;
import seg.UCMScenarioViewer.model.StartEndMessage;
import seg.UCMScenarioViewer.model.TimeOut;

/**
 *  Maps model elements into edit parts for main viewer. Used by the {@link seg.UCMScenarioViewer.UCMScenarioViewer} object.
 * 
 * @author Sasha
 * @see seg.UCMScenarioViewer.UCMScenarioViewer
 *
 */
public class GraphicalPartFactory implements EditPartFactory {

	/**
	 * Default constructor.
	 */
	public GraphicalPartFactory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {

		if (model instanceof ScenarioGroup)
			return new ScenarioGroupEditPart(model);
		if (model instanceof Scenario)
			return new ScenarioEditPart(model);
		if (model instanceof LifeLine)
			return new LifeLineEditPart(model);
		if (model instanceof Message)
			return new MessageEditPart(model);
		if (model instanceof ParallelSequence)
			return new ParallelSequenceEditPart(model);
		if (model instanceof ResetTimer)
			return new ResetTimerEditPart(model);
		if (model instanceof TimeOut) 
			return new TimeoutEditPart(model);
		if (model instanceof Action)
			return new ActionEditPart(model);
		if (model instanceof Condition)
			return new ConditionEditPart(model);
		if (model instanceof SetTimer)
			return new SetTimerEditPart(model);
		if (model instanceof StartEndMessage)
			return new StartEndEditPart(model);
		return null;
	}

}

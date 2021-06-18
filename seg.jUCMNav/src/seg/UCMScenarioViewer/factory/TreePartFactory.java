/*
 * Created on 04.02.2005
 *
 */
package seg.UCMScenarioViewer.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.UCMScenarioViewer.editpart.tree.LifeLineTreeEditPart;
import seg.UCMScenarioViewer.editpart.tree.ModelElementTreeEditPart;
import seg.UCMScenarioViewer.editpart.tree.ScenarioTreeEditPart;
import seg.UCMScenarioViewer.model.LifeLine;
import seg.UCMScenarioViewer.model.Scenario;

/**
 * Maps model elements into edit parts for tree viewer. Used by the tree viewer object.
 * 
 * @author Sasha
 *
 */
public class TreePartFactory implements EditPartFactory {

	/**
	 * Default constructor.
	 */
	public TreePartFactory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Scenario)
			return new ScenarioTreeEditPart(model);
		else if (model instanceof LifeLine)
			return new LifeLineTreeEditPart(model);
		return new ModelElementTreeEditPart(model);
	}

}

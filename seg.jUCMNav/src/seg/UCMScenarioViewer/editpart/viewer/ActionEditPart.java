/*
 * Created on 24.02.2005
 *
 */
package seg.UCMScenarioViewer.editpart.viewer;

import org.eclipse.draw2d.IFigure;

import seg.UCMScenarioViewer.figures.ActionFigure;

/**
 * @author Sasha
 *
 */
public class ActionEditPart extends AbstractModelElementEditPart {

	/**
	 * Default constructor.
	 */
	public ActionEditPart() {
		super();
	}

	/**
	 * @param model
	 */
	public ActionEditPart(Object model) {
		super(model);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return new ActionFigure(getModelElement().getName());
	}

}

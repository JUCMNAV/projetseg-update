/*
 * Created on 03.03.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.UCMScenarioViewer.editpart.viewer;

import org.eclipse.draw2d.IFigure;

import seg.UCMScenarioViewer.figures.ParallelSequenceFigure;
import seg.UCMScenarioViewer.model.ParallelSequence;

/**
 * @author Sasha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParallelSequenceEditPart extends AbstractModelElementEditPart {

	/**
	 * 
	 */
	public ParallelSequenceEditPart() {
		super();
	}

	/**
	 * @param model
	 */
	public ParallelSequenceEditPart(Object model) {
		super(model);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return new ParallelSequenceFigure();
	}
	
	

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		ParallelSequence par = (ParallelSequence) getModelElement();
		((ParallelSequenceFigure)getFigure()).setParallelSequenceFigure(par.getName(), 
				par.getLabelWidth(), par.getSplitPoints());
	}
}

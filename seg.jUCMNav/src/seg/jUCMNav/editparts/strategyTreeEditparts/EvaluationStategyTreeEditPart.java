/**
 * 
 */
package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.EvaluationStrategyComponentEditPolicy;

/**
 * TreeEditPart for Strategy in the strategies view
 * 
 * @author Jean-Fran�ois Roy
 * 
 */
public class EvaluationStategyTreeEditPart extends StrategyUrnModelElementTreeEditPart {

	private boolean selected;

	/**
	 * @param model
	 */
	public EvaluationStategyTreeEditPart(EvaluationStrategy model) {
		super(model);
		selected = false;
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new EvaluationStrategyComponentEditPolicy());
	}

	public EvaluationStrategy getEvaluationStrategy() {
		return (EvaluationStrategy) getModel();
	}

	/**
	 * Returns the icon
	 */
	protected Image getImage() {
		if (super.getImage() == null) {
			setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")).createImage()); //$NON-NLS-1$
		}
		return super.getImage();
	}

	// If selected, set the element in bold.
	public void setSelected(boolean selected) {
		// bug 411
		if (widget == null)
			return;
		this.selected = selected;
		if (selected) {
			((TreeItem) widget).setBackground(new Color(null, 200, 200, 200));
		} else {
			((TreeItem) widget).setBackground(new Color(null, 255, 255, 255));
		}
		// refreshVisuals();
	}
}

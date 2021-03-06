/*
 * Created on 13.02.2005
 *
 */
package seg.UCMScenarioViewer.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;

/**
 * @author Sasha
 *
 */
public class DiagramFigure extends Figure {

	private Label nameLabel;
	private String name;

	/**
	 * Default constructor.
	 */
	public DiagramFigure() {
		super();
		nameLabel = new Label();
	}
	
	public DiagramFigure(String name) {
		super();
		nameLabel = new Label();
		nameLabel.setText(name);
		nameLabel.setLabelAlignment(PositionConstants.CENTER);
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure, org.eclipse.draw2d.Graphics, org.eclipse.draw2d.geometry.Insets)
	 */
	public void paint(Graphics graphics) {
		graphics.fillString(name, 10, 10);
	}

}

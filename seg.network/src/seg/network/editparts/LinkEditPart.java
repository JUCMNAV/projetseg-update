/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.network.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import seg.network.editpolicy.LinkEditPolicy;
import seg.network.model.network.Link;

/**
 * @author Etienne Tremblay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LinkEditPart extends AbstractConnectionEditPart {
	
	public LinkEditPart(Link link) {
		super();
		setModel(link);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy( EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy() );
//		installEditPolicy( EditPolicy.CONNECTION_BENDPOINTS_ROLE, new LinkSelectionHandlesEditPolicy() );
		installEditPolicy( EditPolicy.CONNECTION_ROLE, new LinkEditPolicy());
	}
	
	private Link getLink() {
		return (Link)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super.createFigure();
		connection.setTargetDecoration(new PolygonDecoration()); // arrow at target endpoint
//		connection.setLineStyle(2);  // line drawing style
		return connection;
	}
}
